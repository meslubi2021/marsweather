/*-----------------------------------------------------------------------*\
 * View コントローラ クラス
 *  役割: ルーティング (URL にアクションを紐付ける)
 *-----------------------------------------------------------------------*/
package com.github.marsweather.controller;


import com.github.marsweather.domain.entity.DReport;
import com.github.marsweather.domain.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

// http://localhost:8080/view にルーティングする
@Controller
@RequestMapping("/view")
public class MarsApiViewController {

    @Autowired
    ReportService reportService;

    // http://localhost:8080/view にルーティングする
    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        final List<DReport> reports = reportService.getReports();

        setReports.accept(model, reports);

        return getTemplatesUrl.get();

    }

    // http://localhost:8080/view?date=yyyy/MM/dd にルーティングする
    @RequestMapping(method = RequestMethod.GET, params = "date")
    public String terrestrialDate(Model model, @RequestParam Date date) {

        final List<DReport> reports = reportService.getReportsByTerrestrialDate(date);

        setReports.accept(model, reports);

        return getTemplatesUrl.get();

    }

    // resources/templates/view.html を表示する
    private final static Supplier<String> getTemplatesUrl = () -> "view";

    // view.html に渡すデータを設定する。reports で List<DReport> を操作できる
    private final static BiConsumer<Model, List<DReport>> setReports =
            (model, reports) -> model.addAttribute("reports", reports);


}
