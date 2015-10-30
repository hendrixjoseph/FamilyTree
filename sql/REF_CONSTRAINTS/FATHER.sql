--------------------------------------------------------
--  Ref Constraints for Table FATHER
--------------------------------------------------------

  ALTER TABLE "FATHER" ADD CONSTRAINT "FATHER_FK" FOREIGN KEY ("ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FATHER" ADD CONSTRAINT "FCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
