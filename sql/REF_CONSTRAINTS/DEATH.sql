--------------------------------------------------------
--  Ref Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "JOE"."DEATH" ADD CONSTRAINT "DEATH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "JOE"."PERSON" ("ID") ENABLE;
  ALTER TABLE "JOE"."DEATH" ADD CONSTRAINT "DEATH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "JOE"."PLACE" ("ID") ENABLE;
