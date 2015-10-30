--------------------------------------------------------
--  File created - Friday-October-30-2015   
--------------------------------------------------------
DROP TABLE "BOOLEAN" cascade constraints;
DROP TABLE "GENDER" cascade constraints;
DROP TABLE "PLACE_TYPE" cascade constraints;
DROP TABLE "BIRTH" cascade constraints;
DROP TABLE "BURIAL" cascade constraints;
DROP TABLE "DEATH" cascade constraints;
DROP TABLE "FATHER" cascade constraints;
DROP TABLE "MARRIAGE" cascade constraints;
DROP TABLE "MOTHER" cascade constraints;
DROP TABLE "PERSON" cascade constraints;
DROP TABLE "PLACE" cascade constraints;
DROP TABLE "REGION" cascade constraints;
DROP SEQUENCE "PERSON_SEQUENCE";
DROP SEQUENCE "PLACE_SEQUENCE";
DROP VIEW "REGION_VIEW_TEST";
DROP VIEW "CHILDREN_VIEW";
DROP VIEW "REGION_VIEW";
DROP VIEW "SPOUSE_VIEW";
DROP VIEW "SPOUSE_VIEW_TEST";
--------------------------------------------------------
--  DDL for Sequence PERSON_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PERSON_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PLACE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PLACE_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table BOOLEAN
--------------------------------------------------------

  CREATE TABLE "BOOLEAN" 
   (	"ID" NUMBER, 
	"VALUE" VARCHAR2(5)
   ) ;
--------------------------------------------------------
--  DDL for Table GENDER
--------------------------------------------------------

  CREATE TABLE "GENDER" 
   (	"ABBR" CHAR(1), 
	"FULL_WORD" VARCHAR2(10)
   ) ;
--------------------------------------------------------
--  DDL for Table PLACE_TYPE
--------------------------------------------------------

  CREATE TABLE "PLACE_TYPE" 
   (	"ID" NUMBER, 
	"TYPE" VARCHAR2(8)
   ) ;
--------------------------------------------------------
--  DDL for Table BIRTH
--------------------------------------------------------

  CREATE TABLE "BIRTH" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE, 
	"YEAR_KNOWN" NUMBER DEFAULT 1, 
	"DAY_KNOWN" NUMBER DEFAULT 1, 
	"MONTH_KNOWN" NUMBER DEFAULT 1, 
	"ABOUT" NUMBER DEFAULT 1
   ) ;
--------------------------------------------------------
--  DDL for Table BURIAL
--------------------------------------------------------

  CREATE TABLE "BURIAL" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE, 
	"YEAR_KNOWN" NUMBER DEFAULT 1, 
	"DAY_KNOWN" NUMBER DEFAULT 1, 
	"MONTH_KNOWN" NUMBER DEFAULT 1, 
	"ABOUT" NUMBER DEFAULT 1
   ) ;
--------------------------------------------------------
--  DDL for Table DEATH
--------------------------------------------------------

  CREATE TABLE "DEATH" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE, 
	"YEAR_KNOWN" NUMBER DEFAULT 1, 
	"DAY_KNOWN" NUMBER DEFAULT 1, 
	"MONTH_KNOWN" NUMBER DEFAULT 1, 
	"ABOUT" NUMBER DEFAULT 1
   ) ;
--------------------------------------------------------
--  DDL for Table FATHER
--------------------------------------------------------

  CREATE TABLE "FATHER" 
   (	"ID" NUMBER, 
	"CHILD_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table MARRIAGE
--------------------------------------------------------

  CREATE TABLE "MARRIAGE" 
   (	"HUSBAND" NUMBER, 
	"WIFE" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE, 
	"YEAR_KNOWN" NUMBER DEFAULT 1, 
	"DAY_KNOWN" NUMBER DEFAULT 1, 
	"MONTH_KNOWN" NUMBER DEFAULT 1, 
	"ABOUT" NUMBER DEFAULT 1
   ) ;
--------------------------------------------------------
--  DDL for Table MOTHER
--------------------------------------------------------

  CREATE TABLE "MOTHER" 
   (	"ID" NUMBER, 
	"CHILD_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "PERSON" 
   (	"ID" NUMBER DEFAULT NULL, 
	"NAME" VARCHAR2(100), 
	"GENDER" VARCHAR2(10)
   ) ;
--------------------------------------------------------
--  DDL for Table PLACE
--------------------------------------------------------

  CREATE TABLE "PLACE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(100), 
	"TYPE" VARCHAR2(8)
   ) ;
--------------------------------------------------------
--  DDL for Table REGION
--------------------------------------------------------

  CREATE TABLE "REGION" 
   (	"ID" NUMBER, 
	"PLACE_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for View REGION_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW_TEST" ("RID", "REGION", "PID", "PLACE") AS 
  SELECT P1.ID RID, P1.NAME REGION, P2.ID PID, P2.NAME PLACE
FROM PLACE P1, PLACE P2, REGION_VIEW
WHERE P1.ID = REGION_VIEW.ID AND P2.ID = REGION_VIEW.PLACE_ID;
--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "CHILD_ID") AS 
  SELECT
    ID,
    CHILD_ID
FROM
    FATHER
UNION
SELECT
    ID,
    CHILD_ID
FROM
    MOTHER;
--------------------------------------------------------
--  DDL for View REGION_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW" ("ID", "PLACE_ID") AS 
  SELECT UNIQUE R1.ID,R3.PLACE_ID
FROM REGION R1, REGION R2, REGION R3
WHERE (R1.PLACE_ID = R2.ID
  AND R2.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.PLACE_ID);
--------------------------------------------------------
--  DDL for View SPOUSE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SPOUSE_VIEW" ("ID", "SPOUSE_ID") AS 
  SELECT P.ID ID, SPOUSE.ID SPOUSE_ID
FROM MOTHER P, FATHER SPOUSE
WHERE P.CHILD_ID = SPOUSE.CHILD_ID
UNION
SELECT P.ID ID, SPOUSE.ID SPOUSE_ID
FROM FATHER P, MOTHER SPOUSE
WHERE P.CHILD_ID = SPOUSE.CHILD_ID
UNION
SELECT M.WIFE ID, M.HUSBAND SPOUSE_ID
FROM MARRIAGE M
UNION
SELECT M.HUSBAND ID, M.WIFE SPOUSE_ID
FROM MARRIAGE M;
--------------------------------------------------------
--  DDL for View SPOUSE_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SPOUSE_VIEW_TEST" ("NAME", "SPOUSE") AS 
  SELECT P.NAME, S.NAME SPOUSE
FROM PERSON P, PERSON S, SPOUSE_VIEW SV
WHERE P.ID = SV.ID AND S.ID = SV.SPOUSE_ID;
REM INSERTING into BOOLEAN
SET DEFINE OFF;
Insert into BOOLEAN (ID,VALUE) values (0,'false');
Insert into BOOLEAN (ID,VALUE) values (1,'true');
REM INSERTING into GENDER
SET DEFINE OFF;
Insert into GENDER (ABBR,FULL_WORD) values ('M','MALE');
Insert into GENDER (ABBR,FULL_WORD) values ('F','FEMALE');
Insert into GENDER (ABBR,FULL_WORD) values ('O','OTHER');
Insert into GENDER (ABBR,FULL_WORD) values ('U','UNKNOWN');
REM INSERTING into PLACE_TYPE
SET DEFINE OFF;
Insert into PLACE_TYPE (ID,TYPE) values (1,'country');
Insert into PLACE_TYPE (ID,TYPE) values (2,'state');
Insert into PLACE_TYPE (ID,TYPE) values (3,'county');
Insert into PLACE_TYPE (ID,TYPE) values (4,'city');
REM INSERTING into REGION_VIEW_TEST
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PLACE_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PLACE_TYPE_PK" ON "PLACE_TYPE" ("TYPE") 
  ;
--------------------------------------------------------
--  DDL for Index BOOLEAN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BOOLEAN_PK" ON "BOOLEAN" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index REGION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "REGION_PK" ON "REGION" ("PLACE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MARRIAGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MARRIAGE_PK" ON "MARRIAGE" ("HUSBAND", "WIFE") 
  ;
--------------------------------------------------------
--  DDL for Index BIRTHS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIRTHS_PK" ON "BIRTH" ("PERSON_ID") 
  ;
--------------------------------------------------------
--  DDL for Index BURIAL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BURIAL_PK" ON "BURIAL" ("PERSON_ID") 
  ;
--------------------------------------------------------
--  DDL for Index FATHER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FATHER_PK" ON "FATHER" ("CHILD_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PLACE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PLACE_PK" ON "PLACE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index GENDER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GENDER_PK" ON "GENDER" ("FULL_WORD") 
  ;
--------------------------------------------------------
--  DDL for Index DEATHS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DEATHS_PK" ON "DEATH" ("PERSON_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PERSON_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PERSON_PK" ON "PERSON" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MOTHER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOTHER_PK" ON "MOTHER" ("CHILD_ID") 
  ;
--------------------------------------------------------
--  Constraints for Table FATHER
--------------------------------------------------------

  ALTER TABLE "FATHER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER" ADD CONSTRAINT "FATHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_OF_PK" PRIMARY KEY ("PLACE_ID") ENABLE;
  ALTER TABLE "REGION" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "REGION" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BURIAL
--------------------------------------------------------

  ALTER TABLE "BURIAL" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "BURIAL" MODIFY ("MONTH_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BURIAL" MODIFY ("DAY_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BURIAL" MODIFY ("YEAR_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BURIAL" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BOOLEAN
--------------------------------------------------------

  ALTER TABLE "BOOLEAN" MODIFY ("VALUE" NOT NULL ENABLE);
  ALTER TABLE "BOOLEAN" ADD CONSTRAINT "BOOLEAN_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "BOOLEAN" ADD CONSTRAINT "BOOLEAN_CHK1" CHECK ("ID"=0 OR "ID"=1) ENABLE;
  ALTER TABLE "BOOLEAN" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PLACE_TYPE
--------------------------------------------------------

  ALTER TABLE "PLACE_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" ADD CONSTRAINT "PLACE_TYPE_PK" PRIMARY KEY ("TYPE") ENABLE;
--------------------------------------------------------
--  Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "BIRTH" MODIFY ("MONTH_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BIRTH" MODIFY ("DAY_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BIRTH" MODIFY ("YEAR_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BIRTH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOTHER
--------------------------------------------------------

  ALTER TABLE "MOTHER" ADD CONSTRAINT "MOTHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "DEATH" MODIFY ("MONTH_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "DEATH" MODIFY ("DAY_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "DEATH" MODIFY ("YEAR_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "DEATH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PLACE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GENDER
--------------------------------------------------------

  ALTER TABLE "GENDER" ADD CONSTRAINT "GENDER_PK" PRIMARY KEY ("FULL_WORD") ENABLE;
  ALTER TABLE "GENDER" MODIFY ("FULL_WORD" NOT NULL ENABLE);
  ALTER TABLE "GENDER" MODIFY ("ABBR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MARRIAGE
--------------------------------------------------------

  ALTER TABLE "MARRIAGE" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("MONTH_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("DAY_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("YEAR_KNOWN" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PK" PRIMARY KEY ("HUSBAND", "WIFE") ENABLE;
  ALTER TABLE "MARRIAGE" MODIFY ("WIFE" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("HUSBAND" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("GENDER" NOT NULL ENABLE);
  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PERSON" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PERSON" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_ABOUT_FK" FOREIGN KEY ("ABOUT")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_DAY_KNOWN_FK" FOREIGN KEY ("DAY_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_MONTH_KNOWN_FK" FOREIGN KEY ("MONTH_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_YEAR_KNOWN_FK" FOREIGN KEY ("YEAR_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BURIAL
--------------------------------------------------------

  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_ABOUT_FK" FOREIGN KEY ("ABOUT")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_DAY_KNOWN_FK" FOREIGN KEY ("DAY_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_MONTH_KNOWN_FK" FOREIGN KEY ("MONTH_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_YEAR_KNOWN_FK" FOREIGN KEY ("YEAR_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_ABOUT_FK" FOREIGN KEY ("ABOUT")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_DAY_KNOWN_FK" FOREIGN KEY ("DAY_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_MONTH_KNOWN_FK" FOREIGN KEY ("MONTH_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_YEAR_KNOWN_FK" FOREIGN KEY ("YEAR_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FATHER
--------------------------------------------------------

  ALTER TABLE "FATHER" ADD CONSTRAINT "FATHER_FK" FOREIGN KEY ("ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FATHER" ADD CONSTRAINT "FCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MARRIAGE
--------------------------------------------------------

  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_ABOUT_FK" FOREIGN KEY ("ABOUT")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_DAY_KNOWN_FK" FOREIGN KEY ("DAY_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_HUSBAND_FK" FOREIGN KEY ("HUSBAND")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_MONTH_KNOWN_FK" FOREIGN KEY ("MONTH_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_WIFE_FK" FOREIGN KEY ("WIFE")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_YEAR_KNOWN_FK" FOREIGN KEY ("YEAR_KNOWN")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MOTHER
--------------------------------------------------------

  ALTER TABLE "MOTHER" ADD CONSTRAINT "MCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MOTHER" ADD CONSTRAINT "MOTHER_FK" FOREIGN KEY ("ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_GENDER_FK" FOREIGN KEY ("GENDER")
	  REFERENCES "GENDER" ("FULL_WORD") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_TYPE_FK" FOREIGN KEY ("TYPE")
	  REFERENCES "PLACE_TYPE" ("TYPE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_FK" FOREIGN KEY ("ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
