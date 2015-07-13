--------------------------------------------------------
--  File created - Sunday-July-12-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence PLACE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PLACE_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQUENCE_PERSON
--------------------------------------------------------

   CREATE SEQUENCE  "SEQUENCE_PERSON"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table BIRTH
--------------------------------------------------------

  CREATE TABLE "BIRTH" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"DATE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table DEATH
--------------------------------------------------------

  CREATE TABLE "DEATH" 
   (	"PERSON_ID" NUMBER, 
	"PLACE_ID" NUMBER, 
	"DATE" DATE
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
--  DDL for Table PLACE
--------------------------------------------------------

  CREATE TABLE "PLACE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(100)
   ) ;
--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "SPOUSE_ID", "SPOUSE", "CHILD_ID", "CHILD") AS 
  SELECT 
    P.ID,
	--P.NAME,
    SPOUSE.ID AS SPOUSE_ID,
    SPOUSE.NAME AS SPOUSE,
    CHILD.ID AS CHILD_ID,
    CHILD.NAME AS CHILD
FROM 
    PERSON_VIEW CHILD
INNER JOIN 
    PERSON_VIEW P
ON
    CHILD.FATHER_ID = P.ID OR CHILD.MOTHER_ID = P.ID
LEFT OUTER JOIN
    PERSON_VIEW SPOUSE
ON
    (CHILD.FATHER_ID = SPOUSE.ID OR CHILD.MOTHER_ID = SPOUSE.ID) AND
    P.ID <> SPOUSE.ID
ORDER BY
    P.NAME, SPOUSE.NAME;
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
    BIRTH."DATE" AS DATE_OF_BIRTH,
    D_PLACE.NAME AS PLACE_OF_DEATH,
    DEATH."DATE" AS DATE_OF_DEATH
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
REM INSERTING into BIRTH
SET DEFINE OFF;
Insert into BIRTH (PERSON_ID,PLACE_ID,"DATE") values (1,1,to_date('04-MAR-1827','DD-MON-YYYY'));
Insert into BIRTH (PERSON_ID,PLACE_ID,"DATE") values (2,2,to_date('23-MAY-1795','DD-MON-YYYY'));
REM INSERTING into DEATH
SET DEFINE OFF;
Insert into DEATH (PERSON_ID,PLACE_ID,"DATE") values (1,3,to_date('29-JAN-1900','DD-MON-YYYY'));
Insert into DEATH (PERSON_ID,PLACE_ID,"DATE") values (2,1,to_date('12-DEC-1881','DD-MON-YYYY'));
REM INSERTING into FATHER_OF
SET DEFINE OFF;
Insert into FATHER_OF (FATHER_ID,CHILD_ID) values (2,1);
REM INSERTING into GENDER
SET DEFINE OFF;
Insert into GENDER (ABBR,FULL_WORD) values ('M','Male');
Insert into GENDER (ABBR,FULL_WORD) values ('F','Female');
Insert into GENDER (ABBR,FULL_WORD) values ('O','Other');
Insert into GENDER (ABBR,FULL_WORD) values ('U','Unknown');
REM INSERTING into MOTHER_OF
SET DEFINE OFF;
Insert into MOTHER_OF (MOTHER_ID,CHILD_ID) values (3,1);
REM INSERTING into PERSON
SET DEFINE OFF;
Insert into PERSON (ID,NAME,GENDER) values (1,'William Zenos Thoroman','M');
Insert into PERSON (ID,NAME,GENDER) values (2,'Samuel Thoroman','M');
Insert into PERSON (ID,NAME,GENDER) values (3,'Cynthiann McDonald Reynolds','F');
REM INSERTING into PLACE
SET DEFINE OFF;
Insert into PLACE (ID,NAME) values (1,'Ohio');
Insert into PLACE (ID,NAME) values (2,'Washington, Pennsylvania');
Insert into PLACE (ID,NAME) values (3,'Jacksonville, Ohio');
--------------------------------------------------------
--  DDL for Index FATHER_OF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FATHER_OF_PK" ON "FATHER_OF" ("CHILD_ID") 
  ;
--------------------------------------------------------
--  DDL for Index BIRTHS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIRTHS_PK" ON "BIRTH" ("PERSON_ID") 
  ;
--------------------------------------------------------
--  DDL for Index GENDERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GENDERS_PK" ON "GENDER" ("ABBR") 
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
--  Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" MODIFY ("FATHER_ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BIRTH
--------------------------------------------------------

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "BIRTH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PK" PRIMARY KEY ("PERSON_ID") ENABLE;
  ALTER TABLE "DEATH" MODIFY ("PERSON_ID" NOT NULL ENABLE);
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
--  Constraints for Table MOTHER_OF
--------------------------------------------------------

  ALTER TABLE "MOTHER_OF" ADD CONSTRAINT "MOTHER_OF_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER_OF" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER_OF" MODIFY ("MOTHER_ID" NOT NULL ENABLE);
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

  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BIRTH" ADD CONSTRAINT "BIRTH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEATH
--------------------------------------------------------

  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "DEATH" ADD CONSTRAINT "DEATH_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FATHER_OF
--------------------------------------------------------

  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FATHER_FK" FOREIGN KEY ("FATHER_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FATHER_OF" ADD CONSTRAINT "FCHILD_FK" FOREIGN KEY ("CHILD_ID")
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
--  DDL for Trigger PERSON_VIEW_DELETE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PERSON_VIEW_DELETE_TRIGGER" 
INSTEAD OF DELETE ON PERSON_VIEW 
BEGIN
  DELETE FROM PERSON WHERE ID=:old.ID;
END;
/
ALTER TRIGGER "PERSON_VIEW_DELETE_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PERSON_VIEW_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PERSON_VIEW_INSERT_TRIGGER" 
INSTEAD OF INSERT ON PERSON_VIEW 
DECLARE
  GENDER_ABBR CHAR(1);
  P_ID NUMBER;
  PLACE_OF_BIRTH_ID NUMBER;
  PLACE_OF_DEATH_ID NUMBER;
BEGIN
  -- Insert the person
  SELECT ABBR INTO GENDER_ABBR FROM JOE.GENDER WHERE GENDER.FULL_WORD=:new.GENDER;
  INSERT INTO PERSON (NAME, GENDER) VALUES (:new.NAME, GENDER_ABBR);
  
  SELECT MAX(ID) INTO P_ID FROM PERSON; 
  
  -- Map FATHER_ID to FATHER_OF table
  IF :new.FATHER_ID IS NOT NULL THEN
    INSERT INTO FATHER_OF (FATHER_ID, CHILD_ID) VALUES (:new.FATHER_ID, P_ID);
  END IF;
  
  -- Map MOTHER_ID to MOTHER_OF table
  IF :new.MOTHER_ID IS NOT NULL THEN
    INSERT INTO MOTHER_OF (MOTHER_ID, CHILD_ID) VALUES (:new.MOTHER_ID, P_ID);
  END IF;
  
  -- Insert Birth
  
  -- First, map place
  IF :new.PLACE_OF_BIRTH IS NOT NULL THEN
    BEGIN
      SELECT ID INTO PLACE_OF_BIRTH_ID FROM PLACE WHERE :new.PLACE_OF_BIRTH=PLACE.NAME;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO PLACE (NAME) VALUES (:new.PLACE_OF_BIRTH);
      SELECT ID INTO PLACE_OF_BIRTH_ID FROM PLACE WHERE :new.PLACE_OF_BIRTH=PLACE.NAME;
    END;
    INSERT INTO BIRTH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_BIRTH_ID);
  END IF;
  
  -- Now, insert date
  IF :new.DATE_OF_BIRTH IS NOT NULL THEN
    BEGIN
      -- This select does nothing except throw the exception if there is
      -- no birth record yet. Update won't throw it for some reason.
      SELECT PERSON_ID INTO P_ID FROM BIRTH WHERE PERSON_ID=P_ID;
      UPDATE BIRTH SET "DATE"=:new.DATE_OF_BIRTH WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO BIRTH (PERSON_ID, "DATE") VALUES (P_ID, :new.DATE_OF_BIRTH);
    END;
  END IF;
  
  -- Insert Death
  
  -- First, map place
  IF :new.PLACE_OF_DEATH IS NOT NULL THEN
    BEGIN
      SELECT ID INTO PLACE_OF_DEATH_ID FROM PLACE WHERE :new.PLACE_OF_DEATH=PLACE.NAME;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO PLACE (NAME) VALUES (:new.PLACE_OF_DEATH);
      SELECT ID INTO PLACE_OF_DEATH_ID FROM PLACE WHERE :new.PLACE_OF_DEATH=PLACE.NAME;
    END;
    INSERT INTO DEATH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_DEATH_ID);
  END IF;
  
  -- Now, insert date
  IF :new.DATE_OF_DEATH IS NOT NULL THEN
    BEGIN
      -- This select does nothing except throw the exception if there is
      -- no death record yet. Update won't throw it for some reason.
      SELECT PERSON_ID INTO P_ID FROM DEATH WHERE PERSON_ID=P_ID;
      UPDATE DEATH SET "DATE"=:new.DATE_OF_DEATH WHERE PERSON_ID=P_ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      INSERT INTO DEATH (PERSON_ID, "DATE") VALUES (P_ID, :new.DATE_OF_DEATH);
    END;
  END IF;
  
END;
/
ALTER TRIGGER "PERSON_VIEW_INSERT_TRIGGER" ENABLE;
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
