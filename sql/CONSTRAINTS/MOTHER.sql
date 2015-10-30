--------------------------------------------------------
--  Constraints for Table MOTHER
--------------------------------------------------------

  ALTER TABLE "MOTHER" ADD CONSTRAINT "MOTHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER" MODIFY ("ID" NOT NULL ENABLE);
