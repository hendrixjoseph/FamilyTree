--------------------------------------------------------
--  Ref Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_FK" FOREIGN KEY ("ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
