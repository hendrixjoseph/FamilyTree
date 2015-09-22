--------------------------------------------------------
--  DDL for View SPOUSE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SPOUSE_VIEW" ("ID", "NAME", "SPOUSE_ID", "SPOUSE_NAME") AS 
  SELECT DISTINCT P.ID,
  P.NAME,
  SPOUSE.ID SPOUSE_ID,
  SPOUSE.NAME SPOUSE_NAME
FROM PERSON P,
  PERSON SPOUSE,
  MARRIAGE
WHERE (P.ID    = MARRIAGE.HUSBAND OR P.ID = MARRIAGE.WIFE)
AND (SPOUSE.ID = MARRIAGE.WIFE OR SPOUSE.ID = MARRIAGE.HUSBAND)
AND (P.ID <> SPOUSE.ID)
UNION SELECT DISTINCT
    P.ID,
    P.NAME,
    SPOUSE.ID SPOUSE_ID,
    SPOUSE.NAME SPOUSE_NAME
FROM 
    PERSON P,
    PERSON SPOUSE,
    FATHER_OF,
    MOTHER_OF
WHERE
    (P.ID = FATHER_OF.FATHER_ID OR P.ID = MOTHER_OF.MOTHER_ID) AND
    (SPOUSE.ID = MOTHER_OF.MOTHER_ID OR SPOUSE.ID = FATHER_OF.FATHER_ID) AND
    P.ID <> SPOUSE.ID AND
    FATHER_OF.CHILD_ID = MOTHER_OF.CHILD_ID;
