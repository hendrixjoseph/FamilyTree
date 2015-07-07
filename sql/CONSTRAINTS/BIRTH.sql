--------------------------------------------------------
--  Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BIRTH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
