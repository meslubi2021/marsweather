SELECT
  terrestrial_date,
  sol,
  ls,
  min_temp,
  min_temp_fahrenheit
  max_temp,
  max_temp_fahrenheit,
  pressure,
  pressure_string,
  abs_humidity,
  wind_speed,
  wind_direction,
  atmo_opacity,
  season,
  sunrise,
  sunset
FROM
  report
WHERE
-- Doma 2, 「式言語」, http://doma.readthedocs.org/ja/stable/expression/
/*%if terrestrialDate != null */
  terrestrial_date = /* terrestrialDate */'2016-03-05'
/*%end*/
ORDER BY
  terrestrial_date DESC
