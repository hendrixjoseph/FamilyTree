--------------------------------------------------------
--  Constraints for Table REGION_OF
--------------------------------------------------------

  ALTER TABLE "REGION_OF" ADD CONSTRAINT "REGION_OF_PK" PRIMARY KEY ("PLACE_ID") ENABLE;
  ALTER TABLE "REGION_OF" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "REGION_OF" MODIFY ("REGION_ID" NOT NULL ENABLE);
