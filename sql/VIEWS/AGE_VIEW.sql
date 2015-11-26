--------------------------------------------------------
--  DDL for View AGE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_VIEW" ("PERSON_ID", "AGE", "BIRTH_YEAR", "DEATH_YEAR") AS 
  SELECT B.PERSON_ID, 
      (D.YEAR-B.YEAR) AS AGE, 
       B.YEAR AS BIRTH_YEAR, 
       D.YEAR AS DEATH_YEAR       
FROM EVENT B,EVENT D
WHERE B.PERSON_ID=D.PERSON_ID
AND B.TYPE='birth'
AND D.TYPE='death'
AND B.YEAR IS NOT NULL
AND D.YEAR IS NOT NULL;
