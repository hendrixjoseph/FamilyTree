--------------------------------------------------------
--  DDL for Trigger MARRIAGE_VIEW_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" 
INSTEAD OF INSERT ON MARRIAGE_VIEW 
BEGIN
  -- Insert the couple
  INSERT INTO MARRIAGE (HUSBAND, WIFE) VALUES (:new.HUSBAND_ID, :new.WIFE_ID);
  
  -- Insert (well, now update) the date
  IF :new."DATE" IS NOT NULL THEN
      UPDATE MARRIAGE SET "DATE"=:new."DATE" WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
  END IF;
END;
/
ALTER TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" ENABLE;
