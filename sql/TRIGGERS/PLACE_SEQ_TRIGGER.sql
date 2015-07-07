--------------------------------------------------------
--  DDL for Trigger PLACE_SEQ_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PLACE_SEQ_TRIGGER" 
BEFORE INSERT ON PLACE 
FOR EACH ROW 
 WHEN (new."ID" IS NULL) DECLARE
  newId NUMBER;
BEGIN
  SELECT PLACE_SEQUENCE.nextval INTO newId FROM DUAL;
  :new."ID" := newId;
END;
/
ALTER TRIGGER "PLACE_SEQ_TRIGGER" ENABLE;
