--------------------------------------------------------
--  DDL for View PER_MONTH_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_MONTH_VIEW" ("BIRTHS", "DEATHS", "MONTH") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.MONTH,DEATH.MONTH ) MONTH
FROM
  (
    SELECT COUNT( * ) COUNT,
    MONTH
    FROM EVENT
    WHERE TYPE = 'birth'
      AND MONTH IS NOT NULL
    GROUP BY MONTH
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
    MONTH
    FROM EVENT
    WHERE TYPE = 'death'
      AND MONTH IS NOT NULL
    GROUP BY MONTH
  )
  DEATH
ON BIRTH.MONTH = DEATH.MONTH
ORDER BY MONTH;
