--------------------------------------------------------
--  File created - Monday-September-21-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type CUSTOM_DATE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "CUSTOM_DATE" AS OBJECT 
( 
    DATE_OF DATE,
    ABOUT NUMBER,
    YEAR_KNOWN NUMBER,
    MONTH_KNOWN NUMBER,
    DAY_KNOWN NUMBER,
    MEMBER FUNCTION IS_ABOUT RETURN BOOLEAN,
    MEMBER FUNCTION IS_YEAR_KNOWN RETURN BOOLEAN,
    MEMBER FUNCTION IS_MONTH_KNOWN RETURN BOOLEAN,
    MEMBER FUNCTION IS_DAY_KNOWN RETURN BOOLEAN
);
/
CREATE OR REPLACE TYPE BODY "CUSTOM_DATE" AS 
   MEMBER FUNCTION IS_ABOUT RETURN BOOLEAN IS
   BEGIN
      RETURN ABOUT = 1;
   END IS_ABOUT;
   
   MEMBER FUNCTION IS_YEAR_KNOWN RETURN BOOLEAN IS
   BEGIN
      RETURN YEAR_KNOWN = 1;
   END IS_YEAR_KNOWN;
   
   MEMBER FUNCTION IS_MONTH_KNOWN RETURN BOOLEAN IS
   BEGIN
      RETURN MONTH_KNOWN = 1;
   END IS_MONTH_KNOWN;
   
   MEMBER FUNCTION IS_DAY_KNOWN RETURN BOOLEAN IS
   BEGIN
      RETURN DAY_KNOWN = 1;
   END IS_DAY_KNOWN;
END;

/
--------------------------------------------------------
--  DDL for Sequence PLACE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PLACE_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 504 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQUENCE_PERSON
--------------------------------------------------------

   CREATE SEQUENCE  "SEQUENCE_PERSON"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 11150 CACHE 20 NOORDER  NOCYCLE ;
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
	"FULL_WORD" VARCHAR2(20)
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
--  DDL for Table SETTINGS
--------------------------------------------------------

  CREATE TABLE "SETTINGS" 
   (	"THEME" VARCHAR2(20), 
	"DEFAULT_PERSON" NUMBER, 
	"DEFAULT_PERSON_TYPE" NUMBER, 
	"VIEW_WELCOME_PAGE" NUMBER
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
	"ANNIVERARY" DATE, 
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
--  DDL for Table MARRIAGE
--------------------------------------------------------

  CREATE TABLE "MARRIAGE" 
   (	"HUSBAND" NUMBER, 
	"WIFE" NUMBER, 
	"PLACE" NUMBER, 
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
	"NAME" VARCHAR2(100)
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

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "NAME", "CHILD_ID", "CHILD_NAME") AS 
  SELECT DISTINCT
    P.ID,
    P.NAME,
    CHILD.ID CHILD_ID,
    CHILD.NAME CHILD_NAME
FROM 
    PERSON P,
    PERSON CHILD,
    FATHER_OF,
    MOTHER_OF
WHERE
    (P.ID = FATHER_OF.FATHER_ID AND CHILD.ID = FATHER_OF.CHILD_ID) OR
    (P.ID = MOTHER_OF.MOTHER_ID AND CHILD.ID = MOTHER_OF.CHILD_ID);
--------------------------------------------------------
--  DDL for View LAST_PERSON_INSERTED_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "LAST_PERSON_INSERTED_VIEW" ("ID", "FATHER_ID", "FATHER_NAME", "MOTHER_ID", "MOTHER_NAME", "NAME", "GENDER", "PLACE_OF_BIRTH", "DATE_OF_BIRTH", "PLACE_OF_DEATH", "DATE_OF_DEATH") AS 
  SELECT 
    "ID","FATHER_ID","FATHER_NAME","MOTHER_ID","MOTHER_NAME","NAME","GENDER","PLACE_OF_BIRTH","DATE_OF_BIRTH","PLACE_OF_DEATH","DATE_OF_DEATH"
FROM 
  PERSON_VIEW
WHERE
  ID = (SELECT MAX(ID) FROM PERSON_VIEW);
--------------------------------------------------------
--  DDL for View MARRIAGE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "MARRIAGE_VIEW" ("HUSBAND_ID", "HUSBAND_NAME", "WIFE_ID", "WIFE_NAME", "PLACE", "ANNIVERSARY") AS 
  SELECT 
    HUSBAND AS HUSBAND_ID,
    H.NAME AS HUSBAND_NAME,
    WIFE AS WIFE_ID,
    W.NAME AS WIFE_NAME,
    PLACE.NAME AS PLACE,
    ANNIVERSARY
FROM 
    MARRIAGE M,
    PERSON H,
    PERSON W,
    PLACE
WHERE
    M.HUSBAND = H.ID
AND M.WIFE = W.ID
AND M.PLACE = PLACE.ID (+);
--------------------------------------------------------
--  DDL for View PERSON_INFO_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PERSON_INFO_VIEW" ("PERSON_ID", "TYPE", "DESCRIPTION") AS 
  SELECT 
    PERSON_ID,
    PERSON_INFO_TYPE.TYPE AS TYPE,
    DESCRIPTION
FROM 
    PERSON_INFO,
    PERSON_INFO_TYPE
WHERE
    PERSON_INFO.TYPE = PERSON_INFO_TYPE.ID;
--------------------------------------------------------
--  DDL for View PERSON_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PERSON_VIEW" ("ID", "FATHER_ID", "FATHER_NAME", "MOTHER_ID", "MOTHER_NAME", "NAME", "GENDER", "PLACE_OF_BIRTH", "DATE_OF_BIRTH", "PLACE_OF_DEATH", "DATE_OF_DEATH") AS 
  SELECT
    P.ID,
    DAD.ID AS FATHER_ID,
    DAD.NAME AS FATHER_NAME,
    MOM.ID AS MOTHER_ID,
    MOM.NAME AS MOTHER_NAME,
    P.NAME,
    GENDER.FULL_WORD AS GENDER,
    B_PLACE.NAME AS PLACE_OF_BIRTH,
    BIRTH.anniversary AS DATE_OF_BIRTH,
    D_PLACE.NAME AS PLACE_OF_DEATH,
    DEATH.anniversary AS DATE_OF_DEATH
FROM
    PERSON P,
    PERSON DAD,
    PERSON MOM,
    MOTHER_OF,
    FATHER_OF,
    BIRTH,
    DEATH,
    PLACE B_PLACE,
    PLACE D_PLACE,
    GENDER
WHERE   P.GENDER = GENDER.ABBR
AND     FATHER_OF.FATHER_ID = DAD.ID (+)
AND     MOTHER_OF.MOTHER_ID = MOM.ID (+)
AND     P.ID = FATHER_OF.CHILD_ID (+)
AND     P.ID = MOTHER_OF.CHILD_ID (+)
AND     P.ID = BIRTH.PERSON_ID (+)
AND     P.ID = DEATH.PERSON_ID (+)
AND     BIRTH.PLACE_ID = B_PLACE.ID (+)
AND     DEATH.PLACE_ID = D_PLACE.ID (+);
--------------------------------------------------------
--  DDL for View SETTINGS_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SETTINGS_VIEW" ("THEME", "DEFAULT_PERSON", "DEFAULT_PERSON_TYPE", "VIEW_WELCOME_PAGE") AS 
  SELECT 
    THEME,
    DEFAULT_PERSON,
    DEFAULT_PERSON_TYPE.DEFAULT_PERSON_TYPE AS DEFAULT_PERSON_TYPE,
    BOOLEAN.VALUE AS VIEW_WELCOME_PAGE
FROM 
    SETTINGS,
    DEFAULT_PERSON_TYPE,
    BOOLEAN
WHERE
    DEFAULT_PERSON_TYPE.ID = SETTINGS.DEFAULT_PERSON_TYPE
AND BOOLEAN.ID = SETTINGS.VIEW_WELCOME_PAGE;
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
REM INSERTING into GENDER
SET DEFINE OFF;
Insert into GENDER (ABBR,FULL_WORD) values ('M','Male');
Insert into GENDER (ABBR,FULL_WORD) values ('F','Female');
Insert into GENDER (ABBR,FULL_WORD) values ('O','Other');
Insert into GENDER (ABBR,FULL_WORD) values ('U','Unknown');
REM INSERTING into DEFAULT_PERSON_TYPE
SET DEFINE OFF;
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (1,'Same person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (2,'Last viewed person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (3,'Last edited person');
REM INSERTING into PERSON_INFO_TYPE
SET DEFINE OFF;
REM INSERTING into SETTINGS
SET DEFINE OFF;
Insert into SETTINGS (THEME,DEFAULT_PERSON,DEFAULT_PERSON_TYPE,VIEW_WELCOME_PAGE) values (null,null,1,1);
REM INSERTING into CHILDREN_VIEW
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index DEFAULT_PERSON_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DEFAULT_PERSON_TYPE_PK" ON "DEFAULT_PERSON_TYPE" ("ID") 
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
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PLACE_FK" FOREIGN KEY ("PLACE")
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
--  Ref Constraints for Table SETTINGS
--------------------------------------------------------

  ALTER TABLE "SETTINGS" ADD CONSTRAINT "DEFAULT_PERSON_FK" FOREIGN KEY ("DEFAULT_PERSON")
	  REFERENCES "PERSON" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "SETTINGS" ADD CONSTRAINT "DEFAULT_PERSON_TYPE_FK" FOREIGN KEY ("DEFAULT_PERSON")
	  REFERENCES "DEFAULT_PERSON_TYPE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "SETTINGS" ADD CONSTRAINT "VIEW_WELCOME_PAGE_FK" FOREIGN KEY ("VIEW_WELCOME_PAGE")
	  REFERENCES "BOOLEAN" ("ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger MARRIAGE_VIEW_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" 
INSTEAD OF INSERT ON MARRIAGE_VIEW 
DECLARE
  PLACE_ID NUMBER;
  DUMMY NUMBER;
BEGIN 
  SELECT HUSBAND INTO DUMMY FROM MARRIAGE WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
EXCEPTION WHEN NO_DATA_FOUND THEN
  -- Insert the couple
  INSERT INTO MARRIAGE (HUSBAND, WIFE) VALUES (:new.HUSBAND_ID, :new.WIFE_ID); 
  
  -- Insert (well, now update) the date
  IF :new.ANNIVERSARY IS NOT NULL THEN
      UPDATE MARRIAGE SET ANNIVERSARY=:new.ANNIVERSARY WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
  END IF;
  
  -- Finally, the place
  IF :new.PLACE IS NOT NULL THEN
      INSERT_PLACE_PROCEDURE(:new.PLACE, PLACE_ID);
      UPDATE MARRIAGE SET PLACE=PLACE_ID WHERE HUSBAND=:new.HUSBAND_ID AND WIFE=:new.WIFE_ID;
  END IF;
END;
/
ALTER TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PERSON_SEQ_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PERSON_SEQ_TRIGGER" BEFORE INSERT ON PERSON 
FOR EACH ROW 
 WHEN (new."ID" IS NULL) DECLARE
  newId NUMBER;
BEGIN
  SELECT SEQUENCE_PERSON.nextval INTO newId FROM DUAL;
  :new."ID" := newId;
END;
/
ALTER TRIGGER "PERSON_SEQ_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PLACE_SEQ_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PLACE_SEQ_TRIGGER" 
BEFORE INSERT ON PLACE 
FOR EACH ROW 
 WHEN (new."ID" IS NULL) DECLARE
  newId NUMBER;
BEGIN
  SELECT PLACE_SEQUENCE.nextval INTO newId FROM DUAL;
  :new."ID" := newId;
END;
/
ALTER TRIGGER "PLACE_SEQ_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure INSERT_OR_UPDATE_BIRTH
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "INSERT_OR_UPDATE_BIRTH" 
(
  P_ID IN NUMBER 
, PLACE_OF_BIRTH IN VARCHAR2 
, DATE_OF_BIRTH IN DATE 
) AS 
  PLACE_OF_BIRTH_ID NUMBER;
  DUMMY NUMBER;
BEGIN
   -- First, map place
  IF PLACE_OF_BIRTH IS NOT NULL THEN
    BEGIN
      INSERT_PLACE_PROCEDURE(PLACE_OF_BIRTH, PLACE_OF_BIRTH_ID);
      SELECT PERSON_ID INTO DUMMY FROM BIRTH WHERE PERSON_ID=P_ID;
      UPDATE BIRTH SET PLACE_ID=PLACE_OF_BIRTH_ID WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO BIRTH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_BIRTH_ID);
    END;
  END IF;
  
  -- Now, insert date
  IF DATE_OF_BIRTH IS NOT NULL THEN
    BEGIN
      SELECT PERSON_ID INTO DUMMY FROM BIRTH WHERE PERSON_ID=P_ID;
      UPDATE BIRTH SET "DATE"=DATE_OF_BIRTH WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO BIRTH (PERSON_ID, "DATE") VALUES (P_ID, DATE_OF_BIRTH);
    END;
  END IF;
END INSERT_OR_UPDATE_BIRTH;

/
--------------------------------------------------------
--  DDL for Procedure INSERT_OR_UPDATE_DEATH
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "INSERT_OR_UPDATE_DEATH" 
(
  P_ID IN NUMBER 
, PLACE_OF_DEATH IN VARCHAR2 
, DATE_OF_DEATH IN DATE 
) AS 
  PLACE_OF_DEATH_ID NUMBER;
  DUMMY NUMBER;
BEGIN
   -- First, map place
  IF PLACE_OF_DEATH IS NOT NULL THEN
    BEGIN
      INSERT_PLACE_PROCEDURE(PLACE_OF_DEATH, PLACE_OF_DEATH_ID);
      SELECT PERSON_ID INTO DUMMY FROM DEATH WHERE PERSON_ID=P_ID;
      UPDATE DEATH SET PLACE_ID=PLACE_OF_DEATH_ID WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO DEATH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_DEATH_ID);
    END;
  END IF;
  
  -- Now, insert date
  IF DATE_OF_DEATH IS NOT NULL THEN
    BEGIN
      SELECT PERSON_ID INTO DUMMY FROM DEATH WHERE PERSON_ID=P_ID;
      UPDATE DEATH SET "DATE"=DATE_OF_DEATH WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO DEATH (PERSON_ID, "DATE") VALUES (P_ID, DATE_OF_DEATH);
    END;
  END IF;
END INSERT_OR_UPDATE_DEATH;

/
--------------------------------------------------------
--  DDL for Procedure INSERT_PLACE_PROCEDURE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "INSERT_PLACE_PROCEDURE" 
(
  PLACE_NAME IN VARCHAR2 
, PLACE_ID OUT NUMBER 
) AS 
BEGIN
  BEGIN
      SELECT ID INTO PLACE_ID FROM PLACE WHERE PLACE_NAME=PLACE.NAME;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO PLACE (NAME) VALUES (PLACE_NAME);
      SELECT ID INTO PLACE_ID FROM PLACE WHERE PLACE_NAME=PLACE.NAME;
    END;
END INSERT_PLACE_PROCEDURE;

/
