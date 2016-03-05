package jp.co.cam.net.controller;


import jp.co.cam.net.dao.ReportDao;
import jp.co.cam.net.entity.Report;
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

@Controller
@RequestMapping("/mars")
public class MarsApiViewController {

    @Autowired
    ReportDao reportDao;

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        final List<Report> reports = reportDao.selectAll();

        setReports.accept(model, reports);

        return getMars.get();

    }

    @RequestMapping(method = RequestMethod.GET, params = "date")
    public String mars(Model model, @RequestParam Date date) {

        final List<Report> reports = reportDao.selectByTerrestrialDate(date);

        setReports.accept(model, reports);

        return getMars.get();

    }

    private final static BiConsumer<Model, List<Report>> setReports =
            (model, reports) -> model.addAttribute("reports", reports);

    private final static Supplier<String> getMars = () -> "mars";

}
