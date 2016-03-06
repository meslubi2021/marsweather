/*-----------------------------------------------------------------------*\
 * REST コントローラ クラス
 *  役割: URL とアクションを紐付けます。
 *-----------------------------------------------------------------------*/
package com.github.marsweather.controller;


import com.github.marsweather.domain.entity.DReport;
import com.github.marsweather.domain.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

// http://localhost:8080/rest にルーティングする
// note: Spring Boot, "The @RestController and @RequestMapping annotations", http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-first-application-annotations
@RestController
@RequestMapping(path = "/rest")
public class MarsApiRestController {

    // note: Spring Boot, "Spring Beans and dependency injection", http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-spring-beans-and-dependency-injection
    @Autowired
    ReportService reportService;

    // http://localhost:8080/rest にルーティングする
    @RequestMapping(method = RequestMethod.GET)
    List<DReport> all() {

        return reportService.getReports();

    }

    // http://localhost:8080/view?date=yyyy/MM/dd にルーティングする
    @RequestMapping(method = RequestMethod.GET, params = "date")
    List<DReport> terrestrialDate(@RequestParam Date date) {

        return reportService.getReportsByTerrestrialDate(date);

    }

}
