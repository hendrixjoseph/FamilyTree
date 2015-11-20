--------------------------------------------------------
--  DDL for View REGION_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW_TEST" ("RID", "REGION", "R_TYPE", "PID", "PLACE", "TYPE") AS 
  SELECT P1.ID RID, P1.NAME REGION, P1.TYPE R_TYPE,
       P2.ID PID, P2.NAME PLACE, P2.TYPE TYPE
FROM PLACE P1, PLACE P2, REGION_VIEW
WHERE P1.ID = REGION_VIEW.ID AND P2.ID = REGION_VIEW.PLACE_ID
ORDER BY PLACE, REGION;
