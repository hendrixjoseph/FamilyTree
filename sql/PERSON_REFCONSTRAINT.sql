--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "JOE"."PERSON" ADD CONSTRAINT "PERSON_FATHER_FK" FOREIGN KEY ("FATHER_ID")
	  REFERENCES "JOE"."PERSON" ("ID") ENABLE;
  ALTER TABLE "JOE"."PERSON" ADD CONSTRAINT "PERSON_MOTHER_FK" FOREIGN KEY ("MOTHER_ID")
	  REFERENCES "JOE"."PERSON" ("ID") ENABLE;
