--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "JOE"."PERSON" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAMILY_TREE"  ENABLE;
  ALTER TABLE "JOE"."PERSON" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "JOE"."PERSON" MODIFY ("ID" NOT NULL ENABLE);
