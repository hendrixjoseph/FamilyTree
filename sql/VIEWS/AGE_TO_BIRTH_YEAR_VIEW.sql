--------------------------------------------------------
--  DDL for View AGE_TO_BIRTH_YEAR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_TO_BIRTH_YEAR_VIEW" ("AGE", "YEAR") AS 
  SELECT AGE_VIEW.AGE, YEAR
FROM PERSON,
  AGE_VIEW,
  EVENT
WHERE PERSON.ID = AGE_VIEW.PERSON_ID
  AND AGE_VIEW.PERSON_ID = EVENT.PERSON_ID
  AND EVENT.TYPE = 'birth';
