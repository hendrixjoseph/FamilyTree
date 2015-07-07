--------------------------------------------------------
--  Constraints for Table MOTHER_OF
--------------------------------------------------------

  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MOTHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER_OF" MODIFY ("MOTHER_ID" NOT NULL ENABLE);
