--------------------------------------------------------
--  Ref Constraints for Table BURIAL
--------------------------------------------------------

  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
