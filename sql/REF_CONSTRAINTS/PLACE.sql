--------------------------------------------------------
--  Ref Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_TYPE_FK" FOREIGN KEY ("TYPE")
	  REFERENCES "PLACE_TYPE" ("ID") ENABLE;
