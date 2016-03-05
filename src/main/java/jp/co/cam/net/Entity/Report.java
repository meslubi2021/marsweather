package jp.co.cam.net.entity;


import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import java.math.BigDecimal;
import java.util.Date;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Report {

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

}

