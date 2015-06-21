--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "JOE"."PERSON" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "JOE"."PERSON" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "JOE"."PERSON" MODIFY ("ID" NOT NULL ENABLE);
