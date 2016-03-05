package jp.co.cam.net.dao;

import jp.co.cam.net.entity.Report;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ConfigAutowireable
@Dao
public interface ReportDao {

    @Select
    List<Report> selectAll();

    @Select
    List<Report> selectByTerrestrialDate(Date terrestrialDate);

    @Insert
    @Transactional
    int insert(Report reservation);

    @Delete
    @Transactional
    int delete(Report reservation);

}
