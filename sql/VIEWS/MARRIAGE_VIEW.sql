--------------------------------------------------------
--  DDL for View MARRIAGE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "MARRIAGE_VIEW" ("HUSBAND_ID", "HUSBAND_NAME", "WIFE_ID", "WIFE_NAME", "PLACE", "DATE") AS 
  SELECT 
    HUSBAND AS HUSBAND_ID,
    H.NAME AS HUSBAND_NAME,
    WIFE AS WIFE_ID,
    W.NAME AS WIFE_NAME,
    PLACE.NAME AS PLACE,
    "DATE"
FROM 
    MARRIAGE M,
    PERSON H,
    PERSON W,
    PLACE
WHERE
    M.HUSBAND = H.ID
AND M.WIFE = W.ID
AND M.PLACE = PLACE.ID (+);
