SELECT
  version,
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
  sunset,
  add_dttm,
  upd_dttm
FROM
  d_report
ORDER BY
  terrestrial_date DESC
