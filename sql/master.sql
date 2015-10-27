--------------------------------------------------------
--  File created - Monday-October-26-2015   
--------------------------------------------------------
DROP TABLE "BOOLEAN" cascade constraints;
DROP TABLE "DEFAULT_PERSON_TYPE" cascade constraints;
DROP TABLE "PERSON_INFO_TYPE" cascade constraints;
DROP TABLE "PLACE_TYPE" cascade constraints;
DROP TABLE "BIRTH" cascade constraints;
DROP TABLE "BURIAL" cascade constraints;
DROP TABLE "DEATH" cascade constraints;
DROP TABLE "FATHER_OF" cascade constraints;
DROP TABLE "GENDER" cascade constraints;
DROP TABLE "MARRIAGE" cascade constraints;
DROP TABLE "MOTHER_OF" cascade constraints;
DROP TABLE "PERSON" cascade constraints;
DROP TABLE "PERSON_INFO" cascade constraints;
DROP TABLE "PLACE" cascade constraints;
DROP TABLE "REGION_OF" cascade constraints;
DROP TABLE "SETTINGS" cascade constraints;
DROP TABLE "SOURCE" cascade constraints;
DROP SEQUENCE "PERSON_SEQUENCE";
DROP SEQUENCE "PLACE_SEQUENCE";
DROP VIEW "CHILDREN_VIEW";
DROP VIEW "REGION_VIEW";
DROP VIEW "REGION_VIEW_TEST";
DROP VIEW "SPOUSE_VIEW";
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
--  DDL for Table DEFAULT_PERSON_TYPE
--------------------------------------------------------

  CREATE TABLE "DEFAULT_PERSON_TYPE" 
   (	"ID" NUMBER, 
	"DEFAULT_PERSON_TYPE" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table PERSON_INFO_TYPE
--------------------------------------------------------

  CREATE TABLE "PERSON_INFO_TYPE" 
   (	"ID" NUMBER, 
	"TYPE" VARCHAR2(20)
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
	"ANNIVERSARY" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table BURIAL
--------------------------------------------------------

  CREATE TABLE "BURIAL" 
   (	"PERSON_ID" NUMBER, 
	"ANNIVERSARY" DATE, 
	"PLACE_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table DEATH
--------------------------------------------------------

  CREATE TABLE "DEATH" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table FATHER_OF
--------------------------------------------------------

  CREATE TABLE "FATHER_OF" 
   (	"FATHER_ID" NUMBER, 
	"CHILD_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table GENDER
--------------------------------------------------------

  CREATE TABLE "GENDER" 
   (	"ABBR" CHAR(1), 
	"FULL_WORD" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table MARRIAGE
--------------------------------------------------------

  CREATE TABLE "MARRIAGE" 
   (	"HUSBAND" NUMBER, 
	"WIFE" NUMBER, 
	"PLACE_ID" NUMBER, 
	"ANNIVERSARY" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table MOTHER_OF
--------------------------------------------------------

  CREATE TABLE "MOTHER_OF" 
   (	"MOTHER_ID" NUMBER, 
	"CHILD_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "PERSON" 
   (	"ID" NUMBER DEFAULT NULL, 
	"NAME" VARCHAR2(100), 
	"GENDER" CHAR(1)
   ) ;
--------------------------------------------------------
--  DDL for Table PERSON_INFO
--------------------------------------------------------

  CREATE TABLE "PERSON_INFO" 
   (	"PERSON_ID" NUMBER, 
	"TYPE" NUMBER, 
	"DESCRIPTION" BLOB
   ) ;
--------------------------------------------------------
--  DDL for Table PLACE
--------------------------------------------------------

  CREATE TABLE "PLACE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(100), 
	"TYPE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table REGION_OF
--------------------------------------------------------

  CREATE TABLE "REGION_OF" 
   (	"REGION_ID" NUMBER, 
	"PLACE_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table SETTINGS
--------------------------------------------------------

  CREATE TABLE "SETTINGS" 
   (	"THEME" VARCHAR2(20), 
	"DEFAULT_PERSON" NUMBER, 
	"DEFAULT_PERSON_TYPE" NUMBER, 
	"VIEW_WELCOME_PAGE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table SOURCE
--------------------------------------------------------

  CREATE TABLE "SOURCE" 
   (	"ID" NUMBER, 
	"CITATION" VARCHAR2(50), 
	"TITLE" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "CHILD_ID") AS 
  SELECT
    FATHER_ID ID,
    CHILD_ID
FROM
    FATHER_OF
UNION
SELECT
    MOTHER_ID ID,
    CHILD_ID
FROM
    MOTHER_OF;
--------------------------------------------------------
--  DDL for View REGION_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW" ("REGION_ID", "PLACE_ID") AS 
  SELECT R1.REGION_ID,R3.PLACE_ID
FROM REGION_OF R1, REGION_OF R2, REGION_OF R3
WHERE R1.PLACE_ID = R2.REGION_ID
  AND R2.PLACE_ID = R3.REGION_ID
UNION 
SELECT R1.REGION_ID,R2.PLACE_ID
FROM REGION_OF R1, REGION_OF R2
WHERE R1.PLACE_ID = R2.REGION_ID
UNION  
SELECT "REGION_ID","PLACE_ID" FROM REGION_OF;
--------------------------------------------------------
--  DDL for View REGION_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW_TEST" ("RID", "REGION", "PID", "PLACE") AS 
  SELECT P1.ID RID, P1.NAME REGION, P2.ID PID, P2.NAME PLACE
FROM PLACE P1, PLACE P2, REGION_VIEW
WHERE P1.ID = REGION_VIEW.REGION_ID AND P2.ID = REGION_VIEW.PLACE_ID;
--------------------------------------------------------
--  DDL for View SPOUSE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SPOUSE_VIEW" ("ID", "NAME", "SPOUSE_ID", "SPOUSE_NAME") AS 
  SELECT DISTINCT P.ID,
  P.NAME,
  SPOUSE.ID SPOUSE_ID,
  SPOUSE.NAME SPOUSE_NAME
FROM PERSON P,
  PERSON SPOUSE,
  MARRIAGE
WHERE (P.ID    = MARRIAGE.HUSBAND OR P.ID = MARRIAGE.WIFE)
AND (SPOUSE.ID = MARRIAGE.WIFE OR SPOUSE.ID = MARRIAGE.HUSBAND)
AND (P.ID <> SPOUSE.ID)
UNION SELECT DISTINCT
    P.ID,
    P.NAME,
    SPOUSE.ID SPOUSE_ID,
    SPOUSE.NAME SPOUSE_NAME
FROM 
    PERSON P,
    PERSON SPOUSE,
    FATHER_OF,
    MOTHER_OF
WHERE
    (P.ID = FATHER_OF.FATHER_ID OR P.ID = MOTHER_OF.MOTHER_ID) AND
    (SPOUSE.ID = MOTHER_OF.MOTHER_ID OR SPOUSE.ID = FATHER_OF.FATHER_ID) AND
    P.ID <> SPOUSE.ID AND
    FATHER_OF.CHILD_ID = MOTHER_OF.CHILD_ID;
REM INSERTING into BOOLEAN
SET DEFINE OFF;
Insert into BOOLEAN (ID,VALUE) values (0,'false');
Insert into BOOLEAN (ID,VALUE) values (1,'true');
REM INSERTING into DEFAULT_PERSON_TYPE
SET DEFINE OFF;
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (1,'Same person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (2,'Last viewed person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (3,'Last edited person');
REM INSERTING into PERSON_INFO_TYPE
SET DEFINE OFF;
REM INSERTING into PLACE_TYPE
SET DEFINE OFF;
Insert into PLACE_TYPE (ID,TYPE) values (1,'country');
Insert into PLACE_TYPE (ID,TYPE) values (2,'state');
Insert into PLACE_TYPE (ID,TYPE) values (3,'county');
Insert into PLACE_TYPE (ID,TYPE) values (4,'city');
REM INSERTING into CHILDREN_VIEW
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index DEFAULT_PERSON_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DEFAULT_PERSON_TYPE_PK" ON "DEFAULT_PERSON_TYPE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index REGION_OF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "REGION_OF_PK" ON "REGION_OF" ("PLACE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PLACE_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PLACE_TYPE_PK" ON "PLACE_TYPE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index FATHER_OF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FATHER_OF_PK" ON "FATHER_OF" ("CHILD_ID") 
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
--  DDL for Index PERSON_INFO_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PERSON_INFO_TYPE_PK" ON "PERSON_INFO_TYPE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index BOOLEAN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BOOLEAN_PK" ON "BOOLEAN" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SOURCE_PK" ON "SOURCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index GENDERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GENDERS_PK" ON "GENDER" ("ABBR") 
  ;
--------------------------------------------------------
--  DDL for Index BURIAL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BURIAL_PK" ON "BURIAL" ("PERSON_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PLACE_NAME_UK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PLACE_NAME_UK" ON "PLACE" ("NAME") 
  ;
--------------------------------------------------------
--  DDL for Index PLACE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PLACE_PK" ON "PLACE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MOTHER_OF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOTHER_OF_PK" ON "MOTHER_OF" ("CHILD_ID") 
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
--  Constraints for Table BURIAL
--------------------------------------------------------

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
--  Constraints for Table PERSON_INFO_TYPE
--------------------------------------------------------

  ALTER TABLE "PERSON_INFO_TYPE" ADD CONSTRAINT "PERSON_INFO_TYPE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PERSON_INFO_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PERSON_INFO_TYPE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PLACE_TYPE
--------------------------------------------------------

  ALTER TABLE "PLACE_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PLACE_TYPE" ADD CONSTRAINT "PLACE_TYPE_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BIRTH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSON_INFO
--------------------------------------------------------

  ALTER TABLE "PERSON_INFO" MODIFY ("DESCRIPTION" NOT NULL ENABLE);
  ALTER TABLE "PERSON_INFO" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PERSON_INFO" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SETTINGS
--------------------------------------------------------

  ALTER TABLE "SETTINGS" MODIFY ("DEFAULT_PERSON_TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_NAME_UK" UNIQUE ("NAME") ENABLE;
  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PLACE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GENDER
--------------------------------------------------------

  ALTER TABLE "GENDER" ADD CONSTRAINT "GENDERS_PK" PRIMARY KEY ("ABBR") ENABLE;
  ALTER TABLE "GENDER" MODIFY ("FULL_WORD" NOT NULL ENABLE);
  ALTER TABLE "GENDER" MODIFY ("ABBR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("GENDER" NOT NULL ENABLE);
  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PERSON" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PERSON" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REGION_OF
--------------------------------------------------------

  ALTER TABLE "REGION_OF" ADD CONSTRAINT "REGION_OF_PK" PRIMARY KEY ("PLACE_ID") ENABLE;
  ALTER TABLE "REGION_OF" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "REGION_OF" MODIFY ("REGION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SOURCE
--------------------------------------------------------

  ALTER TABLE "SOURCE" ADD CONSTRAINT "SOURCE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "SOURCE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" MODIFY ("FATHER_ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "DEATH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEFAULT_PERSON_TYPE
--------------------------------------------------------

  ALTER TABLE "DEFAULT_PERSON_TYPE" ADD CONSTRAINT "DEFAULT_PERSON_TYPE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "DEFAULT_PERSON_TYPE" MODIFY ("DEFAULT_PERSON_TYPE" NOT NULL ENABLE);
  ALTER TABLE "DEFAULT_PERSON_TYPE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MARRIAGE
--------------------------------------------------------

  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PK" PRIMARY KEY ("HUSBAND", "WIFE") ENABLE;
  ALTER TABLE "MARRIAGE" MODIFY ("WIFE" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("HUSBAND" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOTHER_OF
--------------------------------------------------------

  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MOTHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER_OF" MODIFY ("MOTHER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BURIAL
--------------------------------------------------------

  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BURIAL" ADD CONSTRAINT "BURIAL_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_FK" FOREIGN KEY ("FATHER_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MARRIAGE
--------------------------------------------------------

  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_HUSBAND_FK" FOREIGN KEY ("HUSBAND")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_WIFE_FK" FOREIGN KEY ("WIFE")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MOTHER_OF
--------------------------------------------------------

  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MCHILD_FK" FOREIGN KEY ("CHILD_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MOTHER_FK" FOREIGN KEY ("MOTHER_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" ADD CONSTRAINT "PERSON_GENDER_FK" FOREIGN KEY ("GENDER")
	  REFERENCES "GENDER" ("ABBR") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSON_INFO
--------------------------------------------------------

  ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "PERSON_FK1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PERSON_INFO" ADD CONSTRAINT "TYPE_FK1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON_INFO_TYPE" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_TYPE_FK" FOREIGN KEY ("TYPE")
	  REFERENCES "PLACE_TYPE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REGION_OF
--------------------------------------------------------

  ALTER TABLE "REGION_OF" ADD CONSTRAINT "PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
  ALTER TABLE "REGION_OF" ADD CONSTRAINT "REGION_FK" FOREIGN KEY ("REGION_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SETTINGS
--------------------------------------------------------

  ALTER TABLE "SETTINGS" ADD CONSTRAINT "DEFAULT_PERSON_FK" FOREIGN KEY ("DEFAULT_PERSON")
	  REFERENCES "PERSON" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "SETTINGS" ADD CONSTRAINT "DEFAULT_PERSON_TYPE_FK" FOREIGN KEY ("DEFAULT_PERSON")
	  REFERENCES "DEFAULT_PERSON_TYPE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "SETTINGS" ADD CONSTRAINT "VIEW_WELCOME_PAGE_FK" FOREIGN KEY ("VIEW_WELCOME_PAGE")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger FATHER_OF_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "FATHER_OF_TRIGGER" 
BEFORE INSERT OR UPDATE OF FATHER_ID ON FATHER_OF 
  FOR EACH ROW
DECLARE
  GENDER_ABBR CHAR(1);
  WRONG_GENDER EXCEPTION;
BEGIN
  SELECT GENDER INTO GENDER_ABBR FROM PERSON WHERE PERSON.ID=:new.FATHER_ID;
  
  IF GENDER_ABBR <> 'M' THEN
    RAISE WRONG_GENDER;
  END IF;
END;
/
ALTER TRIGGER "FATHER_OF_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger MOTHER_OF_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MOTHER_OF_TRIGGER" 
BEFORE INSERT OR UPDATE OF MOTHER_ID ON MOTHER_OF 
  FOR EACH ROW
DECLARE
  GENDER_ABBR CHAR(1);
  WRONG_GENDER EXCEPTION;
BEGIN
  SELECT GENDER INTO GENDER_ABBR FROM PERSON WHERE PERSON.ID=:new.MOTHER_ID;
  
  IF GENDER_ABBR <> 'F' THEN
    RAISE WRONG_GENDER;
  END IF;
END;
/
ALTER TRIGGER "MOTHER_OF_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger REGION_OF_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "REGION_OF_TRIGGER" 
BEFORE INSERT OR UPDATE OF PLACE_ID,REGION_ID ON REGION_OF
FOR EACH ROW
DECLARE
  PLACE_LEVEL NUMBER;
  REGION_LEVEL NUMBER;
  REGION_EXCEPTION EXCEPTION;
BEGIN
  SELECT TYPE INTO PLACE_LEVEL FROM PLACE WHERE ID=:new.PLACE_ID;
  SELECT TYPE INTO REGION_LEVEL FROM PLACE WHERE ID=:new.REGION_ID;
  
  IF PLACE_LEVEL < REGION_LEVEL THEN
    RAISE REGION_EXCEPTION;
  END IF;
END;
/
ALTER TRIGGER "REGION_OF_TRIGGER" ENABLE;
