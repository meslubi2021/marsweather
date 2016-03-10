/*-----------------------------------------------------------------------*\
 * アプリケーション クラス
 *  役割: アプリケーションのエントリー ポイント（開始点）。
 *-----------------------------------------------------------------------*/

package com.github.marsweather;

import com.github.marsweather.domain.entity.DReport;
import com.github.marsweather.domain.service.MaasApiService;
import com.github.marsweather.domain.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    // エントリー ポイント
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // DI (Dependency Injection: 依存性の注入)
    @Autowired
    ReportService reportService;

    @Autowired
    MaasApiService maasApiService;

    // 1か月分のデータを HTTP で取得し，データベースに選択 & 削除 & 挿入(Select & Delete & Insert)する
    @Bean
    CommandLineRunner runner() {

        return (args) -> {

            // 1か月分の火星の天気を取得する (Service -> Jersey HTTP Request MAAS API -> HTTP Response JSON -> Jackson -> Doma 2 Entity)
            final List<DReport> reportsApi = maasApiService.getArchive("2016-02-01", "2016-02-29");

            reportsApi.forEach(reportApi -> {
                // Select を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
                final List<DReport> reportsDb = reportService.getReportsByTerrestrialDate(reportApi.terrestrialDate);

                // Delete を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
                reportsDb.forEach(reportDb -> reportService.removeReport(reportDb));
            });

            // Insert を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
            reportsApi.forEach(report -> reportService.saveReport(report));

        };
    }

}
