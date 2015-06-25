--------------------------------------------------------
--  DDL for Trigger PERSON_SEQ_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "JOE"."PERSON_SEQ_TRIGGER" BEFORE INSERT ON PERSON 
FOR EACH ROW 
 WHEN (new."ID" IS NULL) DECLARE
  v_id NUMBER;
BEGIN
  -- Select a new value from the sequence into a local variable. As David
  -- commented, this step is optional. You can directly select into :new.qname_id
  SELECT SEQUENCE_PERSON.nextval INTO v_id FROM DUAL;

  -- :new references the record that you are about to insert into qname. Hence,
  -- you can overwrite the value of :new.qname_id (qname.qname_id) with the value
  -- obtained from your sequence, before inserting
  :new."ID" := v_id;
END;
/
ALTER TRIGGER "JOE"."PERSON_SEQ_TRIGGER" ENABLE;
