/*-----------------------------------------------------------------------*\
 * schema.sql
 * 役割: Spring Boot 起動時に実行される SQL
 *       application.properties のデータベース接続情報を使用する
 *-----------------------------------------------------------------------*/


/*-----------------------------------------------------------------------*\
 * Table: d_report
 *-----------------------------------------------------------------------*/
CREATE TABLE IF NOT EXISTS public.d_report (
  version             INTEGER                                                   NOT NULL, -- Version
  terrestrial_date    DATE                                                      NOT NULL, -- Terrestrial Date (Earth Date)
  sol                 NUMERIC, -- Sol (Mars Date)
  ls                  NUMERIC, -- LS - (Mars Season)
  min_temp            NUMERIC, -- Min Temp (C)
  min_temp_fahrenheit NUMERIC, -- Min Temp (F)
  max_temp            NUMERIC, -- Max Temp (C)
  max_temp_fahrenheit NUMERIC, -- Max Temp (F)
  pressure            NUMERIC, -- Atmospheric Pressure
  pressure_string     CHARACTER VARYING(64), -- Atmospheric Pressure
  abs_humidity        NUMERIC, -- Humidity
  wind_speed          NUMERIC, -- Wind Speed
  wind_direction      CHARACTER VARYING(64), -- Wind Direction
  atmo_opacity        CHARACTER VARYING(64), -- Weather Status (Sunny, Cloudy, etc)
  season              CHARACTER VARYING(64), -- Season
  sunrise             TIMESTAMP WITHOUT TIME ZONE, -- Sunrise
  sunset              TIMESTAMP WITHOUT TIME ZONE, -- Sunset
  add_dttm            TIMESTAMP DEFAULT DATE_TRUNC('second', CURRENT_TIMESTAMP) NOT NULL, -- 登録日時
  upd_dttm            TIMESTAMP DEFAULT DATE_TRUNC('second', CURRENT_TIMESTAMP) NOT NULL, -- 更新日時
  CONSTRAINT pk_d_report PRIMARY KEY (terrestrial_date)
)
WITH (
OIDS =FALSE
);

/*-----------------------------------------------------------------------*\
 * Comment
 *-----------------------------------------------------------------------*/
COMMENT ON TABLE public.d_report IS 'MAAS API report';
COMMENT ON COLUMN public.d_report.version IS 'VERSION';
COMMENT ON COLUMN public.d_report.terrestrial_date IS 'Terrestrial Date (Earth Date)';
COMMENT ON COLUMN public.d_report.sol IS 'Sol (Mars Date)';
COMMENT ON COLUMN public.d_report.ls IS 'LS - (Mars Season)';
COMMENT ON COLUMN public.d_report.min_temp IS 'Min Temp (C)';
COMMENT ON COLUMN public.d_report.min_temp_fahrenheit IS 'Min Temp (F)';
COMMENT ON COLUMN public.d_report.max_temp IS 'Max Temp (C)';
COMMENT ON COLUMN public.d_report.max_temp_fahrenheit IS 'Max Temp (F)';
COMMENT ON COLUMN public.d_report.pressure IS 'Atmospheric Pressure';
COMMENT ON COLUMN public.d_report.pressure_string IS 'Atmospheric Pressure';
COMMENT ON COLUMN public.d_report.abs_humidity IS 'Humidity';
COMMENT ON COLUMN public.d_report.wind_speed IS 'Wind Speed';
COMMENT ON COLUMN public.d_report.wind_direction IS 'Wind Direction';
COMMENT ON COLUMN public.d_report.atmo_opacity IS 'Weather Status (Sunny, Cloudy, etc)';
COMMENT ON COLUMN public.d_report.season IS 'Season';
COMMENT ON COLUMN public.d_report.sunrise IS 'Sunrise';
COMMENT ON COLUMN public.d_report.sunset IS 'Sunset';
COMMENT ON COLUMN public.d_report.add_dttm IS '登録日時';
COMMENT ON COLUMN public.d_report.upd_dttm IS '更新日時';


/*-----------------------------------------------------------------------*\
 * Function: trg_d_report_ins_ts
 *-----------------------------------------------------------------------*/
CREATE OR REPLACE FUNCTION trg_d_report_ins_ts()
  RETURNS TRIGGER AS '
BEGIN

  NEW.version := 1;
  NEW.add_dttm := CURRENT_TIMESTAMP;
  NEW.upd_dttm := CURRENT_TIMESTAMP;

  RETURN NEW;
END;
' LANGUAGE plpgsql;


/*-----------------------------------------------------------------------*\
 * Function: trg_d_report_upd_ts
 *-----------------------------------------------------------------------*/
CREATE OR REPLACE FUNCTION trg_d_report_upd_ts()
  RETURNS TRIGGER AS '
BEGIN

  NEW.version := OLD.version + 1;
  NEW.add_dttm := CURRENT_TIMESTAMP;
  NEW.upd_dttm := CURRENT_TIMESTAMP;

  RETURN NEW;
END;
' LANGUAGE plpgsql;


/*-----------------------------------------------------------------------*\
 * Trigger: d_report_ins_ts
 *-----------------------------------------------------------------------*/
DROP TRIGGER IF EXISTS d_report_ins_ts ON d_report;

CREATE TRIGGER d_report_ins_ts BEFORE INSERT
ON d_report
FOR EACH ROW
EXECUTE PROCEDURE trg_d_report_ins_ts();

/*-----------------------------------------------------------------------*\
 * Trigger: d_report_upd_ts
 *-----------------------------------------------------------------------*/
DROP TRIGGER IF EXISTS d_report_upd_ts ON d_report;

CREATE TRIGGER d_report_upd_ts BEFORE UPDATE
ON d_report
FOR EACH ROW
EXECUTE PROCEDURE trg_d_report_upd_ts();
