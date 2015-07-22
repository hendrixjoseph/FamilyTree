--------------------------------------------------------
--  Ref Constraints for Table PERSON_INFO
--------------------------------------------------------

  ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "PERSON_FK1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "TYPE_FK1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON_INFO_TYPE" ("ID") ON DELETE CASCADE ENABLE;
