CREATE TABLE IF NOT EXISTS public.report (
  terrestrial_date    DATE NOT NULL, -- Terrestrial Date (Earth Date)
  sol                 NUMERIC, -- Sol (Mars Date)
  ls                  NUMERIC, -- LS - (Mars Season)
  min_temp            NUMERIC, -- Min Temp (C&F)
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
  CONSTRAINT pk_report PRIMARY KEY (terrestrial_date)
)
WITH (
OIDS =FALSE
);
COMMENT ON TABLE public.report IS 'MAAS API report';
COMMENT ON COLUMN public.report.terrestrial_date IS 'Terrestrial Date (Earth Date)';
COMMENT ON COLUMN public.report.sol IS 'Sol (Mars Date)';
COMMENT ON COLUMN public.report.ls IS 'LS - (Mars Season)';
COMMENT ON COLUMN public.report.min_temp IS 'Min Temp (C&F)';
COMMENT ON COLUMN public.report.min_temp_fahrenheit IS 'Min Temp (F)';
COMMENT ON COLUMN public.report.max_temp IS 'Max Temp (C)';
COMMENT ON COLUMN public.report.max_temp_fahrenheit IS 'Max Temp (F)';
COMMENT ON COLUMN public.report.pressure IS 'Atmospheric Pressure';
COMMENT ON COLUMN public.report.pressure_string IS 'Atmospheric Pressure';
COMMENT ON COLUMN public.report.abs_humidity IS 'Humidity';
COMMENT ON COLUMN public.report.wind_speed IS 'Wind Speed';
COMMENT ON COLUMN public.report.wind_direction IS 'Wind Direction';
COMMENT ON COLUMN public.report.atmo_opacity IS 'Weather Status (Sunny, Cloudy, etc)';
COMMENT ON COLUMN public.report.season IS 'Season';
COMMENT ON COLUMN public.report.sunrise IS 'Sunrise';
COMMENT ON COLUMN public.report.sunset IS 'Sunset';
