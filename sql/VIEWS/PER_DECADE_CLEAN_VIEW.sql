--------------------------------------------------------
--  DDL for View PER_DECADE_CLEAN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_CLEAN_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
FROM
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE TYPE = 'birth'
      AND YEAR IS NOT NULL
      AND( NOT PERSON_ID IN
      (
        SELECT PERSON_ID FROM EVENT WHERE TYPE = 'death'
      )
      OR EXISTS
      (
        SELECT * FROM EVENT WHERE TYPE = 'death' AND YEAR IS NULL
      ) )
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE TYPE = 'death'
      AND YEAR IS NOT NULL
      AND( NOT PERSON_ID IN
      (
        SELECT PERSON_ID FROM EVENT WHERE TYPE = 'birth'
      )
      OR EXISTS
      (
        SELECT * FROM EVENT WHERE TYPE = 'birth' AND YEAR IS NULL
      ) )
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  DEATH
ON BIRTH.DECADE = DEATH.DECADE
ORDER BY DECADE;
