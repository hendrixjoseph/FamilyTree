--------------------------------------------------------
--  DDL for View AGE_TO_DEATH_YEAR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_TO_DEATH_YEAR_VIEW" ("AVG_AGE", "MEDIAN_AGE", "DEATH_YEAR") AS 
  SELECT AVG( AGE ) AVG_AGE,
    MEDIAN( AGE ) MEDIAN_AGE,
    DEATH_YEAR
  FROM AGE_VIEW
  GROUP BY DEATH_YEAR
  ORDER BY DEATH_YEAR;
