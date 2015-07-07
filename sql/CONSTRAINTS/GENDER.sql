--------------------------------------------------------
--  Constraints for Table GENDER
--------------------------------------------------------

  ALTER TABLE "GENDER" ADD CONSTRAINT "GENDERS_PK" PRIMARY KEY ("ABBR") ENABLE;
  ALTER TABLE "GENDER" MODIFY ("FULL_WORD" NOT NULL ENABLE);
  ALTER TABLE "GENDER" MODIFY ("ABBR" NOT NULL ENABLE);
