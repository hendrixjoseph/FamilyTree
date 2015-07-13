--------------------------------------------------------
--  DDL for Trigger PERSON_VIEW_DELETE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PERSON_VIEW_DELETE_TRIGGER" 
INSTEAD OF DELETE ON PERSON_VIEW 
BEGIN
  DELETE FROM PERSON WHERE ID=:old.ID;
END;
/
ALTER TRIGGER "PERSON_VIEW_DELETE_TRIGGER" ENABLE;
