--------------------------------------------------------
--  Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "JOE"."PLACE" ADD CONSTRAINT "PLACE_NAME_UK" UNIQUE ("NAME") ENABLE;
  ALTER TABLE "JOE"."PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "JOE"."PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "JOE"."PLACE" MODIFY ("ID" NOT NULL ENABLE);
