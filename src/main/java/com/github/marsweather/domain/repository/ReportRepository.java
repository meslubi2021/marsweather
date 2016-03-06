/*-----------------------------------------------------------------------*\
 * DAO (Data Access Object) クラス
 *  役割: Data Access Object （Dao） はデータベースアクセスのためのインタフェースです。
 *  note: Doma 2, 「Daoインタフェース」, http://doma.readthedocs.org/ja/stable/dao/
 *-----------------------------------------------------------------------*/
package com.github.marsweather.domain.repository;

import com.github.marsweather.domain.entity.DReport;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// note: doma-spring-boot, "Create a DAO interface", https://github.com/domaframework/doma-spring-boot#create-a-dao-interface
@ConfigAutowireable
// note: Doma 2, 「Dao定義」, http://doma.readthedocs.org/ja/stable/dao/#id2
@Dao
public interface ReportRepository {

    /*-----------------------------------------------------------------------*\
     * Create (Insert)
     *-----------------------------------------------------------------------*/
    // note: Doma 2, 「挿入」, http://doma.readthedocs.org/ja/stable/query/insert/
    @Insert
    // note: Spring, "Using @Transactional", http://docs.spring.io/autorepo/docs/spring/4.2.x/spring-framework-reference/html/transaction.html#transaction-declarative-annotations
    @Transactional
    int insert(DReport reservation);


    /*-----------------------------------------------------------------------*\
     * Read (Select)
     *-----------------------------------------------------------------------*/
    // note: Doma 2, 「検索」, http://doma.readthedocs.org/ja/stable/query/select/
    @Select
    List<DReport> selectAll();

    @Select
    Optional<DReport> selectByTerrestrialDate(Date terrestrialDate);


    /*-----------------------------------------------------------------------*\
     * Update
     *-----------------------------------------------------------------------*/
    // note: Doma 2, 「更新」, http://doma.readthedocs.org/ja/stable/query/update/
    @Update
    @Transactional
    int update(DReport reservation);


    /*-----------------------------------------------------------------------*\
     * Delete
     *-----------------------------------------------------------------------*/
    // note: Doma 2, 「削除」, http://doma.readthedocs.org/ja/stable/query/delete/
    @Delete
    @Transactional
    int delete(DReport reservation);

}
