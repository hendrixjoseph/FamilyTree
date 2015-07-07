--------------------------------------------------------
--  Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "DEATH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
