--------------------------------------------------------
--  Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_NAME_UK" UNIQUE ("NAME") ENABLE;
  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PLACE" MODIFY ("ID" NOT NULL ENABLE);
