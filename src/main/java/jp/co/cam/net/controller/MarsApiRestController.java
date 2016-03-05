package jp.co.cam.net.controller;


import jp.co.cam.net.dao.ReportDao;
import jp.co.cam.net.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MarsApiRestController {

    @Autowired
    ReportDao reportDao;

    @RequestMapping(path = "/")
    List<Report> all() {
        return reportDao.selectAll();
    }

    @RequestMapping(path = "/", params = "date")
    List<Report> date(@RequestParam Date date) {
        return reportDao.selectByTerrestrialDate(date);
    }

}
