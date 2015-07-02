--------------------------------------------------------
--  Constraints for Table GENDERS
--------------------------------------------------------

  ALTER TABLE "JOE"."GENDERS" ADD CONSTRAINT "GENDERS_PK" PRIMARY KEY ("ABBR") ENABLE;
  ALTER TABLE "JOE"."GENDERS" MODIFY ("FULL_WORD" NOT NULL ENABLE);
  ALTER TABLE "JOE"."GENDERS" MODIFY ("ABBR" NOT NULL ENABLE);
