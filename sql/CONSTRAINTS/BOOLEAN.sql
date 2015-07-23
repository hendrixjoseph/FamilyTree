--------------------------------------------------------
--  Constraints for Table BOOLEAN
--------------------------------------------------------

  ALTER TABLE "BOOLEAN" MODIFY ("VALUE" NOT NULL ENABLE);
  ALTER TABLE "BOOLEAN" ADD CONSTRAINT "BOOLEAN_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "BOOLEAN" ADD CONSTRAINT "BOOLEAN_CHK1" CHECK ("ID"=0 OR "ID"=1) ENABLE;
  ALTER TABLE "BOOLEAN" MODIFY ("ID" NOT NULL ENABLE);
