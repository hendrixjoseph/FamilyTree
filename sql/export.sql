--------------------------------------------------------
--  File created - Thursday-November-26-2015   
--------------------------------------------------------
DROP TABLE "EVENT" cascade constraints;
DROP TABLE "FATHER" cascade constraints;
DROP TABLE "GENDER" cascade constraints;
DROP TABLE "MARRIAGE" cascade constraints;
DROP TABLE "MOTHER" cascade constraints;
DROP TABLE "PERSON" cascade constraints;
DROP TABLE "PLACE" cascade constraints;
DROP TABLE "REGION" cascade constraints;
DROP SEQUENCE "PERSON_SEQUENCE";
DROP SEQUENCE "PLACE_SEQUENCE";
DROP VIEW "AGE_TO_BIRTH_YEAR_VIEW";
DROP VIEW "AGE_TO_DEATH_YEAR_VIEW";
DROP VIEW "AGE_VIEW";
DROP VIEW "CHILDREN_VIEW";
DROP VIEW "FIRST_NAME_COUNT_VIEW";
DROP VIEW "LAST_NAME_COUNT_VIEW";
DROP VIEW "PER_DECADE_CLEAN_VIEW";
DROP VIEW "PER_DECADE_COMBINED_VIEW";
DROP VIEW "PER_DECADE_VIEW";
DROP VIEW "PER_MONTH_VIEW";
DROP VIEW "REGION_VIEW";
DROP VIEW "REGION_VIEW_TEST";
DROP VIEW "SPOUSE_VIEW";
DROP VIEW "SPOUSE_VIEW_TEST";
--------------------------------------------------------
--  DDL for Sequence PERSON_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PERSON_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 1641 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PLACE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PLACE_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999999 INCREMENT BY 1 START WITH 261 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table EVENT
--------------------------------------------------------

  CREATE TABLE "EVENT" 
   (	"PERSON_ID" NUMBER, 
	"TYPE" VARCHAR2(20), 
	"PLACE_ID" NUMBER, 
	"MONTH" NUMBER DEFAULT NULL, 
	"DAY" NUMBER DEFAULT NULL, 
	"YEAR" NUMBER DEFAULT NULL, 
	"ABOUT" NUMBER DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table FATHER
--------------------------------------------------------

  CREATE TABLE "FATHER" 
   (	"ID" NUMBER, 
	"CHILD_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table GENDER
--------------------------------------------------------

  CREATE TABLE "GENDER" 
   (	"ABBR" CHAR(1), 
	"FULL_WORD" VARCHAR2(10)
   ) ;
--------------------------------------------------------
--  DDL for Table MARRIAGE
--------------------------------------------------------

  CREATE TABLE "MARRIAGE" 
   (	"HUSBAND" NUMBER, 
	"WIFE" NUMBER, 
	"PLACE_ID" NUMBER, 
	"MONTH" NUMBER DEFAULT NULL, 
	"DAY" NUMBER DEFAULT NULL, 
	"YEAR" NUMBER DEFAULT NULL, 
	"ABOUT" NUMBER DEFAULT 0
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
--  DDL for View AGE_TO_BIRTH_YEAR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_TO_BIRTH_YEAR_VIEW" ("AVG_AGE", "MEDIAN_AGE", "BIRTH_YEAR") AS 
  SELECT AVG(AGE) AVG_AGE, MEDIAN(AGE) MEDIAN_AGE, BIRTH_YEAR
FROM AGE_VIEW
GROUP BY BIRTH_YEAR
ORDER BY BIRTH_YEAR;
--------------------------------------------------------
--  DDL for View AGE_TO_DEATH_YEAR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_TO_DEATH_YEAR_VIEW" ("AVG_AGE", "MEDIAN_AGE", "DEATH_YEAR") AS 
  SELECT AVG( AGE ) AVG_AGE,
    MEDIAN( AGE ) MEDIAN_AGE,
    DEATH_YEAR
  FROM AGE_VIEW
  GROUP BY DEATH_YEAR
  ORDER BY DEATH_YEAR;
--------------------------------------------------------
--  DDL for View AGE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "AGE_VIEW" ("PERSON_ID", "AGE", "BIRTH_YEAR", "DEATH_YEAR") AS 
  SELECT B.PERSON_ID, 
      (D.YEAR-B.YEAR) AS AGE, 
       B.YEAR AS BIRTH_YEAR, 
       D.YEAR AS DEATH_YEAR       
FROM EVENT B,EVENT D
WHERE B.PERSON_ID=D.PERSON_ID
AND B.TYPE='birth'
AND D.TYPE='death'
AND B.YEAR IS NOT NULL
AND D.YEAR IS NOT NULL;
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
--  DDL for View FIRST_NAME_COUNT_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "FIRST_NAME_COUNT_VIEW" ("COUNT", "FIRST_NAME") AS 
  SELECT COUNT(FIRST_NAME) COUNT, FIRST_NAME
FROM (SELECT REGEXP_SUBSTR(NAME,'^\w+') AS FIRST_NAME FROM PERSON)
GROUP BY FIRST_NAME
ORDER BY COUNT DESC;
--------------------------------------------------------
--  DDL for View LAST_NAME_COUNT_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "LAST_NAME_COUNT_VIEW" ("COUNT", "LAST_NAME") AS 
  SELECT COUNT(LAST_NAME) COUNT, LAST_NAME
FROM (SELECT REGEXP_SUBSTR(NAME,'\w+$') AS LAST_NAME FROM PERSON)
GROUP BY LAST_NAME
ORDER BY COUNT DESC;
--------------------------------------------------------
--  DDL for View PER_DECADE_CLEAN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_CLEAN_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
FROM
  (
    SELECT COUNT( * ) COUNT,
      ( D.YEAR - AVERAGE - MOD( D.YEAR - AVERAGE,10 ) ) DECADE
    FROM EVENT D, (SELECT AVG(AGE) AVERAGE FROM AGE_VIEW)
    WHERE D.TYPE = 'death'
      AND D.YEAR IS NOT NULL
      AND( D.PERSON_ID NOT IN
      (
        SELECT PERSON_ID FROM EVENT WHERE TYPE = 'birth'
      )
      OR EXISTS
      (
        SELECT * FROM EVENT B WHERE B.TYPE = 'birth' AND B.YEAR IS NULL AND B.PERSON_ID=D.PERSON_ID
      ) )
    GROUP BY( D.YEAR - AVERAGE - MOD( D.YEAR - AVERAGE,10 ) )
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
      ( B.YEAR + AVERAGE - MOD( B.YEAR + AVERAGE ,10 ) ) DECADE
    FROM EVENT B, (SELECT AVG(AGE) AVERAGE FROM AGE_VIEW)
    WHERE B.TYPE = 'birth'
      AND B.YEAR IS NOT NULL
      AND( B.PERSON_ID NOT IN
      (
        SELECT PERSON_ID FROM EVENT WHERE TYPE = 'death'
      )
      OR EXISTS
      (
        SELECT * FROM EVENT D WHERE D.TYPE = 'death' AND D.YEAR IS NULL AND D.PERSON_ID=B.PERSON_ID
      ) )
    GROUP BY( B.YEAR + AVERAGE - MOD( B.YEAR + AVERAGE ,10 ) )
  )
  DEATH
ON BIRTH.DECADE = DEATH.DECADE
ORDER BY DECADE;
--------------------------------------------------------
--  DDL for View PER_DECADE_COMBINED_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_COMBINED_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT SUM( BIRTHS ) BIRTHS,
  SUM( DEATHS ) DEATHS,
  DECADE
FROM
  (
    SELECT * FROM PER_DECADE_VIEW
    UNION
    SELECT * FROM PER_DECADE_CLEAN_VIEW
  )
GROUP BY DECADE
ORDER BY DECADE;
--------------------------------------------------------
--  DDL for View PER_DECADE_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_DECADE_VIEW" ("BIRTHS", "DEATHS", "DECADE") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
FROM
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE EVENT.TYPE = 'birth'
      AND EVENT.YEAR IS NOT NULL
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
      ( YEAR - MOD( YEAR,10 ) ) DECADE
    FROM EVENT
    WHERE EVENT.TYPE = 'death'
      AND EVENT.YEAR IS NOT NULL
    GROUP BY( YEAR - MOD( YEAR,10 ) )
  )
  DEATH
ON BIRTH.DECADE = DEATH.DECADE
ORDER BY DECADE;
--------------------------------------------------------
--  DDL for View PER_MONTH_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "PER_MONTH_VIEW" ("BIRTHS", "DEATHS", "MONTH") AS 
  SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
  NVL( DEATH.COUNT,0 ) DEATHS,
  NVL( BIRTH.MONTH,DEATH.MONTH ) MONTH
FROM
  (
    SELECT COUNT( * ) COUNT,
    MONTH
    FROM EVENT
    WHERE TYPE = 'birth'
      AND MONTH IS NOT NULL
    GROUP BY MONTH
  )
  BIRTH
FULL OUTER JOIN
  (
    SELECT COUNT( * ) COUNT,
    MONTH
    FROM EVENT
    WHERE TYPE = 'death'
      AND MONTH IS NOT NULL
    GROUP BY MONTH
  )
  DEATH
ON BIRTH.MONTH = DEATH.MONTH
ORDER BY MONTH;
--------------------------------------------------------
--  DDL for View REGION_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW" ("ID", "PLACE_ID") AS 
  SELECT UNIQUE R1.ID,R3.PLACE_ID
FROM REGION R1, REGION R2, REGION R3
WHERE (R1.PLACE_ID = R2.ID
  AND R2.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.ID)
  OR (R1.PLACE_ID = R3.PLACE_ID)
UNION
SELECT UNIQUE R1.ID,R4.PLACE_ID
FROM REGION R1, REGION R2, REGION R3, REGION R4
WHERE (R1.PLACE_ID = R2.ID
  AND R2.PLACE_ID = R3.ID
  AND R3.PLACE_ID = R4.ID);
--------------------------------------------------------
--  DDL for View REGION_VIEW_TEST
--------------------------------------------------------

  CREATE OR REPLACE VIEW "REGION_VIEW_TEST" ("RID", "REGION", "R_TYPE", "PID", "PLACE", "TYPE") AS 
  SELECT P1.ID RID, P1.NAME REGION, P1.TYPE R_TYPE,
       P2.ID PID, P2.NAME PLACE, P2.TYPE TYPE
FROM PLACE P1, PLACE P2, REGION_VIEW
WHERE P1.ID = REGION_VIEW.ID AND P2.ID = REGION_VIEW.PLACE_ID
ORDER BY PLACE, REGION;
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
--  DDL for Index EVENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EVENT_PK" ON "EVENT" ("PERSON_ID", "TYPE") 
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
--  Constraints for Table PLACE
--------------------------------------------------------

  ALTER TABLE "PLACE" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PLACE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOTHER
--------------------------------------------------------

  ALTER TABLE "MOTHER" ADD CONSTRAINT "MOTHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "MOTHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
  ALTER TABLE "MOTHER" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_OF_PK" PRIMARY KEY ("PLACE_ID") ENABLE;
  ALTER TABLE "REGION" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "REGION" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EVENT
--------------------------------------------------------

  ALTER TABLE "EVENT" ADD CONSTRAINT "EVENT_PK" PRIMARY KEY ("PERSON_ID", "TYPE") ENABLE;
  ALTER TABLE "EVENT" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "EVENT" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "EVENT" ADD CONSTRAINT "EVENT_CHK" CHECK (DAY > 0 AND DAY <= 31 AND (ABOUT = 0 OR ABOUT = 1)) ENABLE;
  ALTER TABLE "EVENT" MODIFY ("PERSON_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table GENDER
--------------------------------------------------------

  ALTER TABLE "GENDER" ADD CONSTRAINT "GENDER_PK" PRIMARY KEY ("FULL_WORD") ENABLE;
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
--  Constraints for Table FATHER
--------------------------------------------------------

  ALTER TABLE "FATHER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "FATHER" ADD CONSTRAINT "FATHER_PK" PRIMARY KEY ("CHILD_ID") ENABLE;
  ALTER TABLE "FATHER" MODIFY ("CHILD_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MARRIAGE
--------------------------------------------------------

  ALTER TABLE "MARRIAGE" MODIFY ("ABOUT" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_CHK" CHECK (DAY > 0 AND DAY <=31 AND (ABOUT = 0 OR ABOUT = 1)) ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PK" PRIMARY KEY ("HUSBAND", "WIFE") ENABLE;
  ALTER TABLE "MARRIAGE" MODIFY ("WIFE" NOT NULL ENABLE);
  ALTER TABLE "MARRIAGE" MODIFY ("HUSBAND" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table EVENT
--------------------------------------------------------

  ALTER TABLE "EVENT" ADD CONSTRAINT "EVENT_PERSON_FK" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "EVENT" ADD CONSTRAINT "EVENT_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
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

  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_HUSBAND_FK" FOREIGN KEY ("HUSBAND")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "MARRIAGE" ADD CONSTRAINT "MARRIAGE_WIFE_FK" FOREIGN KEY ("WIFE")
	  REFERENCES "PERSON" ("ID") ON DELETE CASCADE ENABLE;
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
--  Ref Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "PLACE_FK" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "REGION" ADD CONSTRAINT "REGION_FK" FOREIGN KEY ("ID")
	  REFERENCES "PLACE" ("ID") ON DELETE CASCADE ENABLE;
