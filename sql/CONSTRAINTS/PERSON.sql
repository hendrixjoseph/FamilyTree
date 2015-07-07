--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("GENDER" NOT NULL ENABLE);
  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PERSON" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PERSON" MODIFY ("ID" NOT NULL ENABLE);
