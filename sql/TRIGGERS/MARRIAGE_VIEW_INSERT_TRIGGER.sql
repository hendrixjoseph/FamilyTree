--------------------------------------------------------
--  DDL for Trigger MARRIAGE_VIEW_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" 
INSTEAD OF INSERT ON MARRIAGE_VIEW 
DECLARE
  PLACE_ID NUMBER;
BEGIN
  -- Insert the couple
  INSERT INTO MARRIAGE (HUSBAND, WIFE) VALUES (:new.HUSBAND_ID, :new.WIFE_ID);
  
  -- Insert (well, now update) the date
  IF :new.ANNIVERSARY IS NOT NULL THEN
      UPDATE MARRIAGE SET ANNIVERSARY=:new.ANNIVERSARY WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
  END IF;
  
  -- Finally, the place
  IF :new.PLACE IS NOT NULL THEN
      INSERT_PLACE_PROCEDURE(:new.PLACE, PLACE_ID);
      UPDATE MARRIAGE SET PLACE=PLACE_ID WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
  END IF;
END;
/
ALTER TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" ENABLE;
