--------------------------------------------------------
--  Ref Constraints for Table MOTHER_OF
--------------------------------------------------------

  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MOTHER_FK" FOREIGN KEY ("MOTHER_ID")
	  REFERENCES "PERSON" ("ID") ENABLE;
