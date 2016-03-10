/*-----------------------------------------------------------------------*\
 * API サービス クラス
 *  役割: 外部 API を管理する。
 *-----------------------------------------------------------------------*/

package com.github.marsweather.domain.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.marsweather.domain.entity.DReport;
import groovy.lang.Tuple2;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.github.marsweather.domain.service.MaasApiConst.*;

@Service
@Transactional
public class MaasApiService {

    // http://marsweather.ingenology.com/v1/latest/?format=json
    public DReport getLatest() {

        // HTTP Request
        final URI uriLatest = buildUri(Method.LATEST);
        final Response response = getHttpRequest(uriLatest).get();

        // HTTP Response
        final HttpStatus status = HttpStatus.valueOf(response.getStatus());

        if (status == HttpStatus.OK) {

            // JSON -> Entity (deserialize)
            final MaasApiReportBean latest = response.readEntity(MaasApiReportBean.class);
            response.close();
            return latest.report;

        } else {

            response.close();
            throw new HttpClientErrorException(status);

        }

    }

    // http://marsweather.ingenology.com/v1/archive/?terrestrial_date_start=2012-10-01&terrestrial_date_end=2012-10-31&format=json
    public List<DReport> getArchive(final String dateStart, final String dateEnd) {

        // HTTP Query
        Tuple2<String, String> start = new Tuple2<>("terrestrial_date_start", dateStart);
        Tuple2<String, String> end = new Tuple2<>("terrestrial_date_end", dateEnd);

        // HTTP Request
        URI uriArchive = buildUri(Method.ARCHIVE, start, end);

        List<DReport> reports = new ArrayList<>();
        boolean hasNext = true;
        while (hasNext) {
            final MaasApiReportBean archive = getHttpRequest(uriArchive).get(MaasApiReportBean.class);
            reports.addAll(archive.results);

            final boolean getNext = !uriArchive.equals(archive.next);
            hasNext = (archive.next != null && getNext);
            uriArchive = archive.next;
        }

        return reports;

    }


    private static URI buildUri(final String method, final Tuple2<String, String>... queries) {

        WebTarget maasApi = ClientBuilder.newClient()
                .target(Base.URL)
                .path(Base.VERSION)
                .path(method)
                .queryParam(Format.key, Format.Json.VALUE);

        for (Tuple2<String, String> query : queries) {
            maasApi = maasApi.queryParam(query.getFirst(), query.getSecond());
        }

        return maasApi.getUri();

    }

    private static Invocation.Builder getHttpRequest(final URI uri) {

        // note: Jersey, "Client API", https://jersey.java.net/documentation/latest/client.html
        // note: Jersey, "Support for Common Media Type Representations", https://jersey.java.net/documentation/latest/media.html
        final WebTarget maasApi = ClientBuilder.newClient()
                .register(MaasApiObjectMapperProvider.class)
                .register(JacksonFeature.class)
                .target(uri);

        return maasApi.request(MediaType.APPLICATION_JSON_TYPE);

    }

}


/*----------------------------------------------------------------------------------------------------------------------
 * 内部クラス…
 *--------------------------------------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------*\
 * プロバイダ クラス
 *  役割: Jersey で使う Jackson の設定を記述します。
 *  note: Jersey, "Jackson (1.x and 2.x)", https://jersey.java.net/documentation/latest/media.html#json.jackson
 *-----------------------------------------------------------------------*/
// note: Jersey, "Support for Common Media Type Representations", https://jersey.java.net/documentation/latest/media.html
@Provider
class MaasApiObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public MaasApiObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();

        // note: Qiita, 「snake_case でプロパティを表現している JSON と camelCase で表記している POJO をマッピングしたい」, http://qiita.com/komiya_atsushi/items/6e5b8e6690b82bca8198
        result.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        return result;
    }
}


/*-----------------------------------------------------------------------*\
 * エンティティ クラス
 *  役割: JSON を Jackson で エンティティ化します。
 *  note: Jersey, "Jackson (1.x and 2.x)", https://jersey.java.net/documentation/latest/media.html#json.jackson
 *-----------------------------------------------------------------------*/
class MaasApiReportBean {

    /*-----------------------------------------------------------------------*\
     * Latest
     *-----------------------------------------------------------------------*/
    public DReport report;


    /*-----------------------------------------------------------------------*\
     * Archive
     *-----------------------------------------------------------------------*/
    public int count;

    public URI next;

    public URI previous;

    public List<DReport> results;

}


/*-----------------------------------------------------------------------*\
 * 定数 クラス
 *  役割: MAAS API の定数を定義する。
 *-----------------------------------------------------------------------*/
interface MaasApiConst {

    interface Base {
        String URL = "http://marsweather.ingenology.com";
        String VERSION = "v1";
    }

    interface Format {

        String key = "format";

        interface Json {
            String VALUE = "json";
        }
    }

    interface Method {
        String LATEST = "latest";
        String ARCHIVE = "archive";
    }

}