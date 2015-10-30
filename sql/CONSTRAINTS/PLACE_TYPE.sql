--------------------------------------------------------
--  Constraints for Table PLACE_TYPE
--------------------------------------------------------

  ALTER TABLE "PLACE_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" ADD CONSTRAINT "PLACE_TYPE_PK" PRIMARY KEY ("TYPE") ENABLE;
