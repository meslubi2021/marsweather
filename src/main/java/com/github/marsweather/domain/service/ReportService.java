/*-----------------------------------------------------------------------*\
 * サービス クラス
 *  役割: DAO クラスのトランザクションを管理する。
 *-----------------------------------------------------------------------*/

package com.github.marsweather.domain.service;


import com.github.marsweather.domain.entity.DReport;
import com.github.marsweather.domain.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ReportService {

    @Autowired
    ReportRepository reportRepository;


    public int saveReport(DReport report) {

        return reportRepository.insert(report);

    }

    public List<DReport> getReports() {

        return reportRepository.selectAll();

    }

    public List<DReport> getReportsByTerrestrialDate(final Date terrestrialDate) {

        Optional<DReport> report = reportRepository.selectByTerrestrialDate(terrestrialDate);

        if (report.isPresent()) {

            final List<DReport> reports = Arrays.asList(report.get());
            return reports;

        } else {

            return new ArrayList<>();

        }

    }

    public int modifyReport(DReport report) {

        return reportRepository.update(report);

    }


    public int removeReport(DReport report) {

        return reportRepository.delete(report);

    }

}

