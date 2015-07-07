--------------------------------------------------------
--  Ref Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
