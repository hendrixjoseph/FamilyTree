--------------------------------------------------------
--  Ref Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_FK" FOREIGN KEY ("FATHER_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
