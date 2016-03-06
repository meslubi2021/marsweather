/*-----------------------------------------------------------------------*\
 * エンティティ クラス
 *  役割: エンティティはデータベースのテーブルやクエリの結果セットに対応します。
 *  note: Doma 2, 「エンティティクラス > エンティティ定義」, http://doma.readthedocs.org/ja/stable/entity/
 *-----------------------------------------------------------------------*/
package com.github.marsweather.domain.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.seasar.doma.jdbc.entity.NamingType;

import java.math.BigDecimal;
import java.util.Date;

// note: Doma 2, 「エンティティ定義」, http://doma.readthedocs.org/ja/stable/entity/#id3
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
// note: Doma 2, 「エンティティ定義 > テーブル」, http://doma.readthedocs.org/ja/stable/entity/#id7
@Table(name = "d_report")
public class DReport {

    // note: Doma 2, 「フィールド定義 > バージョン」, http://doma.readthedocs.org/ja/stable/entity/#id14
    @Version
    public int version;

    // note: Doma 2, 「フィールド定義 > 識別子」, http://doma.readthedocs.org/ja/stable/entity/#id10
    @Id
    public Date terrestrialDate;

    public BigDecimal sol;

    public BigDecimal ls;

    public BigDecimal minTemp;

    public BigDecimal minTempFahrenheit;

    public BigDecimal maxTemp;

    public BigDecimal maxTempFahrenheit;

    public BigDecimal pressure;

    public String pressureString;

    public BigDecimal absHumidity;

    public BigDecimal windSpeed;

    public String windDirection;

    public String atmoOpacity;

    public String season;

    public Date sunrise;

    public Date sunset;

    public Date addDttm;

    public Date updDttm;

}

