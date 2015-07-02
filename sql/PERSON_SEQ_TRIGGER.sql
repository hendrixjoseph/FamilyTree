--------------------------------------------------------
--  DDL for Trigger PERSON_SEQ_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "JOE"."PERSON_SEQ_TRIGGER" BEFORE INSERT ON PERSON 
FOR EACH ROW 
 WHEN (new."ID" IS NULL) DECLARE
  newId NUMBER;
BEGIN
  SELECT SEQUENCE_PERSON.nextval INTO newId FROM DUAL;
  :new."ID" := newId;
END;
/
ALTER TRIGGER "JOE"."PERSON_SEQ_TRIGGER" ENABLE;
