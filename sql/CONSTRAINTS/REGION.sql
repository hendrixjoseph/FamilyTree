--------------------------------------------------------
--  Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_OF_PK" PRIMARY KEY ("PLACE_ID") ENABLE;
  ALTER TABLE "REGION" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "REGION" MODIFY ("ID" NOT NULL ENABLE);
