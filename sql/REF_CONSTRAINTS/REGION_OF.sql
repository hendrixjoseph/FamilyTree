--------------------------------------------------------
--  Ref Constraints for Table REGION_OF
--------------------------------------------------------

  ALTER TABLE "REGION_OF" ADD CONSTRAINT "PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
  ALTER TABLE "REGION_OF" ADD CONSTRAINT "REGION_FK" FOREIGN KEY ("REGION_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
