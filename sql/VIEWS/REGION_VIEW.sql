--------------------------------------------------------
--  DDL for View REGION_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW" ("ID", "PLACE_ID") AS 
  SELECT UNIQUE R1.ID,R3.PLACE_ID
FROM REGION R1, REGION R2, REGION R3
WHERE (R1.PLACE_ID = R2.ID
  AND R2.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.PLACE_ID);
