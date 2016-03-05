package jp.co.cam.net;

import jp.co.cam.net.dao.ReportDao;
import jp.co.cam.net.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    ReportDao reportDao;

    @Bean
    CommandLineRunner runner() {

        final SimpleDateFormat strToDate = new SimpleDateFormat("yyyy/MM/dd");

        return (args) -> Arrays.asList("2016/03/01", "2016/03/02").forEach(date -> {

            Report report = new Report();
            try {
                report.terrestrialDate = strToDate.parse(date);
                reportDao.delete(report);
                reportDao.insert(report);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        });
    }

}
