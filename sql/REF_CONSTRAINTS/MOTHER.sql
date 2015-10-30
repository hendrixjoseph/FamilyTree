--------------------------------------------------------
--  Ref Constraints for Table MOTHER
--------------------------------------------------------

  ALTER TABLE "MOTHER" ADD CONSTRAINT "MCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MOTHER" ADD CONSTRAINT "MOTHER_FK" FOREIGN KEY ("ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
