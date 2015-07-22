--------------------------------------------------------
--  Constraints for Table PERSON_INFO_TYPE
--------------------------------------------------------

  ALTER TABLE "PERSON_INFO_TYPE" ADD CONSTRAINT "PERSON_INFO_TYPE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PERSON_INFO_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PERSON_INFO_TYPE" MODIFY ("ID" NOT NULL ENABLE);
