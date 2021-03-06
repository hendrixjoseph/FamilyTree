--------------------------------------------------------
--  DDL for View PER_DECADE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
FROM
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE EVENT.TYPE = 'birth'
      AND EVENT.YEAR IS NOT NULL
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE EVENT.TYPE = 'death'
      AND EVENT.YEAR IS NOT NULL
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  DEATH
ON BIRTH.DECADE = DEATH.DECADE
ORDER BY DECADE;
