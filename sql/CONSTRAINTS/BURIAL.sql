--------------------------------------------------------
--  Constraints for Table BURIAL
--------------------------------------------------------

  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BURIAL" MODIFY ("PERSON_ID" NOT NULL ENABLE);
