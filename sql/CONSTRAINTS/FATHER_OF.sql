--------------------------------------------------------
--  Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" MODIFY ("FATHER_ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
