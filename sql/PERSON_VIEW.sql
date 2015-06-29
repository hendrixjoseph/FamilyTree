--------------------------------------------------------
--  DDL for View PERSON_VIEW
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "JOE"."PERSON_VIEW" ("ID", "FATHER", "MOTHER", "NAME", "PLACE_OF_BIRTH", "DATE_OF_BIRTH", "PLACE_OF_DEATH", "DATE_OF_DEATH") AS 
  SELECT 
    P."ID",
    F."NAME" AS FATHER,
    M."NAME" AS MOTHER,
    P."NAME",
    P.PLACE_OF_BIRTH,
    P.DATE_OF_BIRTH,
    P.PLACE_OF_DEATH,
    P.DATE_OF_DEATH
FROM 
    PERSON P
LEFT OUTER JOIN 
    PERSON F
ON
    P.FATHER_ID = F."ID"
LEFT OUTER JOIN
    PERSON M
ON
    P.MOTHER_ID = M."ID";
