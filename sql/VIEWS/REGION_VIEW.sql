--------------------------------------------------------
--  DDL for View REGION_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW" ("REGION_ID", "PLACE_ID") AS 
  SELECT UNIQUE R1.REGION_ID,R3.PLACE_ID
FROM REGION_OF R1, REGION_OF R2, REGION_OF R3
WHERE (R1.PLACE_ID = R2.REGION_ID
  AND R2.PLACE_ID = R3.REGION_ID)
  OR (R1.PLACE_ID = R3.REGION_ID)
  OR (R1.PLACE_ID = R3.PLACE_ID);
