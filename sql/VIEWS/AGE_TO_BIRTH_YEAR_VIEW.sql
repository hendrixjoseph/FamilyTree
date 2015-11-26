--------------------------------------------------------
--  DDL for View AGE_TO_BIRTH_YEAR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_TO_BIRTH_YEAR_VIEW" ("AVG_AGE", "MEDIAN_AGE", "BIRTH_YEAR") AS 
  SELECT AVG(AGE) AVG_AGE, MEDIAN(AGE) MEDIAN_AGE, BIRTH_YEAR
FROM AGE_VIEW
GROUP BY BIRTH_YEAR
ORDER BY BIRTH_YEAR;
