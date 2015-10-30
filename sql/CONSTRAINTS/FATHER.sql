--------------------------------------------------------
--  Constraints for Table FATHER
--------------------------------------------------------

  ALTER TABLE "FATHER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER" ADD CONSTRAINT "FATHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
