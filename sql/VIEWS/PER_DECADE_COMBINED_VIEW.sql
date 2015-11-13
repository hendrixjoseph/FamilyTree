--------------------------------------------------------
--  DDL for View PER_DECADE_COMBINED_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_COMBINED_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT SUM( BIRTHS ) BIRTHS,
  SUM( DEATHS ) DEATHS,
  DECADE
FROM
  (
    SELECT * FROM PER_DECADE_VIEW
    UNION
    SELECT * FROM PER_DECADE_CLEAN_VIEW
  )
GROUP BY DECADE
ORDER BY DECADE;
