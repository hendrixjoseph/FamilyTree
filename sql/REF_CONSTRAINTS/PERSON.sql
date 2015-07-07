--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_GENDER_FK" FOREIGN KEY ("GENDER")
	  REFERENCES "GENDER" ("ABBR") ENABLE;
