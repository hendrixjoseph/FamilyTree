--------------------------------------------------------
--  DDL for View SPOUSE_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SPOUSE_VIEW_TEST" ("NAME", "SPOUSE") AS 
  SELECT P.NAME, S.NAME SPOUSE
FROM PERSON P, PERSON S, SPOUSE_VIEW SV
WHERE P.ID = SV.ID AND S.ID = SV.SPOUSE_ID;
