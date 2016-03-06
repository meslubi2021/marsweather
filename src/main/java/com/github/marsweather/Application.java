/*-----------------------------------------------------------------------*\
 * アプリケーション クラス
 *  役割: アプリケーションのエントリー ポイント（開始点）。
 *-----------------------------------------------------------------------*/

package com.github.marsweather;

import com.github.marsweather.domain.entity.DReport;
import com.github.marsweather.domain.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
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

    // 当日データをデータベースに選択 & 削除 & 挿入(Select & Delete & Insert)する
    @Bean
    CommandLineRunner runner() {

        return (args) -> {

            // 当日日付を取得する
            final Date today = new Date();

            // Select を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
            final List<DReport> reportToday = reportService.getReportsByTerrestrialDate(today);

            // Delete を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
            reportToday.forEach(report -> {
                if (report != null) {
                    reportService.removeReport(report);
                }
            });

            // Entity を作成する
            DReport reportTodayNew = new DReport();
            reportTodayNew.terrestrialDate = today;

            // Insert を実行する (Doma 2 Entity -> Service -> Repository (DAO) -> PostgreSQL)
            reportService.saveReport(reportTodayNew);

        };
    }

}
