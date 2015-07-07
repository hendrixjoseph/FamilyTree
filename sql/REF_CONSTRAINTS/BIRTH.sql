--------------------------------------------------------
--  Ref Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
