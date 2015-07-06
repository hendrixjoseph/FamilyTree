--------------------------------------------------------
--  Constraints for Table GENDER
--------------------------------------------------------

  ALTER TABLE "JOE"."GENDER" ADD CONSTRAINT "GENDERS_PK" PRIMARY KEY ("ABBR") ENABLE;
  ALTER TABLE "JOE"."GENDER" MODIFY ("FULL_WORD" NOT NULL ENABLE);
  ALTER TABLE "JOE"."GENDER" MODIFY ("ABBR" NOT NULL ENABLE);
