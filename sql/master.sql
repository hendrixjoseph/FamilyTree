--------------------------------------------------------
--  File created - Wednesday-July-22-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence PLACE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "PLACE_SEQUENCE"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 181 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQUENCE_PERSON
--------------------------------------------------------

   CREATE SEQUENCE  "SEQUENCE_PERSON"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6481 CACHE 20 NOORDER  NOCYCLE ;
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
--  DDL for Table DEFAULT_PERSON_TYPE
--------------------------------------------------------

  CREATE TABLE "DEFAULT_PERSON_TYPE" 
   (	"ID" NUMBER, 
	"DEFAULT_PERSON_TYPE" VARCHAR2(20)
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
--  DDL for Table PERSON_INFO_TYPE
--------------------------------------------------------

  CREATE TABLE "PERSON_INFO_TYPE" 
   (	"ID" NUMBER, 
	"TYPE" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table PLACE
--------------------------------------------------------

  CREATE TABLE "PLACE" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(100)
   ) ;
--------------------------------------------------------
--  DDL for Table SETTINGS
--------------------------------------------------------

  CREATE TABLE "SETTINGS" 
   (	"THEME" VARCHAR2(20), 
	"DEFAULT_PERSON" NUMBER, 
	"DEFAULT_PERSON_TYPE" NUMBER
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

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "NAME", "SPOUSE_ID", "SPOUSE", "CHILD_ID", "CHILD") AS 
  SELECT 
    P.ID,
	  P.NAME,
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
--  DDL for View DEFAULT_PERSON_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "DEFAULT_PERSON_VIEW" ("ID", "FATHER_ID", "FATHER_NAME", "MOTHER_ID", "MOTHER_NAME", "NAME", "GENDER", "PLACE_OF_BIRTH", "DATE_OF_BIRTH", "PLACE_OF_DEATH", "DATE_OF_DEATH") AS 
  SELECT 
    "ID","FATHER_ID","FATHER_NAME","MOTHER_ID","MOTHER_NAME","NAME","GENDER","PLACE_OF_BIRTH","DATE_OF_BIRTH","PLACE_OF_DEATH","DATE_OF_DEATH"
FROM 
  PERSON_VIEW
WHERE
  ID = (CASE 
          WHEN (SELECT DEFAULT_PERSON FROM SETTINGS) IS NOT NULL
          THEN (SELECT DEFAULT_PERSON FROM SETTINGS)
          ELSE (SELECT MIN(ID) FROM PERSON)
       END);
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
--------------------------------------------------------
--  DDL for View SETTINGS_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "SETTINGS_VIEW" ("THEME", "DEFAULT_PERSON", "DEFAULT_PERSON_TYPE") AS 
  SELECT 
    THEME,
    DEFAULT_PERSON,
    DEFAULT_PERSON_TYPE.DEFAULT_PERSON_TYPE AS DEFAULT_PERSON_TYPE
FROM 
    SETTINGS,
    DEFAULT_PERSON_TYPE
WHERE
    DEFAULT_PERSON_TYPE.ID = SETTINGS.DEFAULT_PERSON_TYPE;
REM INSERTING into BIRTH
SET DEFINE OFF;
Insert into BIRTH (PERSON_ID,PLACE_ID,"DATE") values (1,1,to_date('04-MAR-1827','DD-MON-YYYY'));
Insert into BIRTH (PERSON_ID,PLACE_ID,"DATE") values (2,2,to_date('23-MAY-1795','DD-MON-YYYY'));
REM INSERTING into DEATH
SET DEFINE OFF;
Insert into DEATH (PERSON_ID,PLACE_ID,"DATE") values (1,3,to_date('29-JAN-1900','DD-MON-YYYY'));
Insert into DEATH (PERSON_ID,PLACE_ID,"DATE") values (2,1,to_date('12-DEC-1881','DD-MON-YYYY'));
REM INSERTING into DEFAULT_PERSON_TYPE
SET DEFINE OFF;
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (1,'Same person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (2,'Last viewed person');
Insert into DEFAULT_PERSON_TYPE (ID,DEFAULT_PERSON_TYPE) values (3,'Last edited person');
REM INSERTING into FATHER_OF
SET DEFINE OFF;
Insert into FATHER_OF (FATHER_ID,CHILD_ID) values (2,1);
REM INSERTING into GENDER
SET DEFINE OFF;
Insert into GENDER (ABBR,FULL_WORD) values ('M','Male');
Insert into GENDER (ABBR,FULL_WORD) values ('F','Female');
Insert into GENDER (ABBR,FULL_WORD) values ('O','Other');
Insert into GENDER (ABBR,FULL_WORD) values ('U','Unknown');
REM INSERTING into MARRIAGE
SET DEFINE OFF;
Insert into MARRIAGE (HUSBAND,WIFE,PLACE,ANNIVERSARY) values (2,3,42,to_date('20-JUL-2015','DD-MON-YYYY'));
REM INSERTING into MOTHER_OF
SET DEFINE OFF;
Insert into MOTHER_OF (MOTHER_ID,CHILD_ID) values (3,1);
REM INSERTING into PERSON
SET DEFINE OFF;
Insert into PERSON (ID,NAME,GENDER) values (1,'William Z Thoroman','M');
Insert into PERSON (ID,NAME,GENDER) values (2,'Samuel Thoroman','M');
Insert into PERSON (ID,NAME,GENDER) values (3,'Cynthiann McDonald Reynolds','F');
REM INSERTING into PERSON_INFO
SET DEFINE OFF;
REM INSERTING into PERSON_INFO_TYPE
SET DEFINE OFF;
REM INSERTING into PLACE
SET DEFINE OFF;
Insert into PLACE (ID,NAME) values (1,'Ohio');
Insert into PLACE (ID,NAME) values (2,'Washington, Pennsylvania');
Insert into PLACE (ID,NAME) values (3,'Jacksonville, Ohio');
Insert into PLACE (ID,NAME) values (42,'Alaska');
Insert into PLACE (ID,NAME) values (43,'Ann Arbor, Michigan');
Insert into PLACE (ID,NAME) values (44,'Dayton, Ohio');
Insert into PLACE (ID,NAME) values (45,'Roane County, Tennessee');
Insert into PLACE (ID,NAME) values (46,'Miami County, Ohio');
Insert into PLACE (ID,NAME) values (47,'Tennessee');
Insert into PLACE (ID,NAME) values (48,'Pottsville, Pennsylvania');
Insert into PLACE (ID,NAME) values (49,'Washington,Pennsylvania');
Insert into PLACE (ID,NAME) values (50,'Pennsylvania');
Insert into PLACE (ID,NAME) values (51,'Sandusky,Ohio');
Insert into PLACE (ID,NAME) values (52,'Greene,Pennsylvania');
Insert into PLACE (ID,NAME) values (53,'Westmoreland,Pennsylvania');
Insert into PLACE (ID,NAME) values (54,'Fayette,Pennsylvania');
Insert into PLACE (ID,NAME) values (55,'Adams,Ohio');
Insert into PLACE (ID,NAME) values (56,'Adams, Ohio');
Insert into PLACE (ID,NAME) values (57,'Brown, Ohio');
Insert into PLACE (ID,NAME) values (58,'Sprigg Twp,Adams,Ohio');
Insert into PLACE (ID,NAME) values (59,'Bentonville,Adams,Ohio');
Insert into PLACE (ID,NAME) values (60,'Kentucky');
Insert into PLACE (ID,NAME) values (61,'Indiana');
Insert into PLACE (ID,NAME) values (62,'Vigo,Vigo,Indiana');
Insert into PLACE (ID,NAME) values (63,'Mason,Kentucky');
Insert into PLACE (ID,NAME) values (64,'Missouri');
Insert into PLACE (ID,NAME) values (65,'Virginia');
Insert into PLACE (ID,NAME) values (66,'Morton,Putnam,Indiana');
Insert into PLACE (ID,NAME) values (67,'Jacksonville,Adams,Ohio');
Insert into PLACE (ID,NAME) values (68,'Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (69,'Scioto,Ohio');
Insert into PLACE (ID,NAME) values (70,'Vanceburg,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (71,'Smythe,Virginia');
Insert into PLACE (ID,NAME) values (72,'Boyd,Kentucky');
Insert into PLACE (ID,NAME) values (73,'Burtonsville,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (74,'Winchester,Clark,Kentucky');
Insert into PLACE (ID,NAME) values (75,'Lexington,Fayette,Kentucky');
Insert into PLACE (ID,NAME) values (76,'College Corner,Indiana');
Insert into PLACE (ID,NAME) values (77,'Cincinnati,Ohio');
Insert into PLACE (ID,NAME) values (78,'Deleware');
Insert into PLACE (ID,NAME) values (79,'Tiffin Twp,Adams,Ohio');
Insert into PLACE (ID,NAME) values (81,'Hospice, Dayton, OH');
Insert into PLACE (ID,NAME) values (82,'Mexico City, Mexico');
Insert into PLACE (ID,NAME) values (83,'Laura, Miami, OH 45337');
Insert into PLACE (ID,NAME) values (84,'New Lebanon, Montgomery, OH 45345');
Insert into PLACE (ID,NAME) values (85,'West Virginia');
Insert into PLACE (ID,NAME) values (86,'Dayton, Montgomery, OH 45403');
Insert into PLACE (ID,NAME) values (87,'Dayton, Montgomery, OH 45409');
Insert into PLACE (ID,NAME) values (88,'Xenia, Greene County, Ohio 45385');
Insert into PLACE (ID,NAME) values (89,'Miami Valley Hospital, Dayton, Ohio');
Insert into PLACE (ID,NAME) values (90,'Montgomery County, Ohio');
Insert into PLACE (ID,NAME) values (91,'Perry County, Ohio');
Insert into PLACE (ID,NAME) values (92,'Troy, Ohio');
Insert into PLACE (ID,NAME) values (93,'Dayton, Montgomery, OH 45407');
Insert into PLACE (ID,NAME) values (94,'Arizona');
Insert into PLACE (ID,NAME) values (95,'25 Aug 1881');
Insert into PLACE (ID,NAME) values (96,'Dayton, Montgomery, OH 45429');
Insert into PLACE (ID,NAME) values (97,'Dayton, Montgomery, OH 45410');
Insert into PLACE (ID,NAME) values (98,'Randolph,Indiana');
Insert into PLACE (ID,NAME) values (99,'Xenia, Ohio');
Insert into PLACE (ID,NAME) values (100,'Bradenton,Florida');
Insert into PLACE (ID,NAME) values (101,'California');
Insert into PLACE (ID,NAME) values (102,'Summit Hill,Ross,Ohio');
Insert into PLACE (ID,NAME) values (103,'Cedarville,Ohio');
Insert into PLACE (ID,NAME) values (104,'Chillicothe,Ohio');
Insert into PLACE (ID,NAME) values (105,'Huntington Twp,Ross,Ohio');
Insert into PLACE (ID,NAME) values (106,'Xenia,Greene,Ohio');
Insert into PLACE (ID,NAME) values (107,'Coventry,Somerset,Maryland');
Insert into PLACE (ID,NAME) values (108,'Des Moines,Polk,Iowa');
Insert into PLACE (ID,NAME) values (109,'Bedford,Pennsylvania');
Insert into PLACE (ID,NAME) values (110,'Sterling,Whiteside,Illinois');
Insert into PLACE (ID,NAME) values (111,'Brown,Ohio');
Insert into PLACE (ID,NAME) values (112,'Madison,Iowa');
Insert into PLACE (ID,NAME) values (113,'Wisconsin');
Insert into PLACE (ID,NAME) values (114,'Mount Carmel,Illinois');
Insert into PLACE (ID,NAME) values (115,'Almena,Norton,Kansas');
Insert into PLACE (ID,NAME) values (116,'Paxton,Ford,Illinois');
Insert into PLACE (ID,NAME) values (117,'Parlee,Indiana');
Insert into PLACE (ID,NAME) values (118,'Clinton Falls,Putnam,Indiana');
Insert into PLACE (ID,NAME) values (119,'Dallas,Missouri');
Insert into PLACE (ID,NAME) values (120,'Montgomery,Kentucky');
Insert into PLACE (ID,NAME) values (121,'Antlers,,Oklahoma');
Insert into PLACE (ID,NAME) values (122,'Indianapolis,Indiana');
Insert into PLACE (ID,NAME) values (123,'Putnam,Indiana');
Insert into PLACE (ID,NAME) values (124,'Saline City,Clay,Indiana');
Insert into PLACE (ID,NAME) values (125,'Cortez,Montezuma,Colorado');
Insert into PLACE (ID,NAME) values (126,'Kansas City,Missouri');
Insert into PLACE (ID,NAME) values (127,'Jancsenville,Adams,Ohio');
Insert into PLACE (ID,NAME) values (128,'Chicago,Illinois');
Insert into PLACE (ID,NAME) values (129,'Versailles,Illinois');
Insert into PLACE (ID,NAME) values (130,'Chicago,Cook,Illinois');
Insert into PLACE (ID,NAME) values (131,'Edwardsville,Kansas');
Insert into PLACE (ID,NAME) values (132,'Edwardsville,Wyandotte,Kansas');
Insert into PLACE (ID,NAME) values (133,'Springfield,Ohio');
Insert into PLACE (ID,NAME) values (134,'Bunnell,Florida');
Insert into PLACE (ID,NAME) values (135,'West Portsmouth,Ohio');
Insert into PLACE (ID,NAME) values (136,'Bracken,Kentucky');
Insert into PLACE (ID,NAME) values (137,'Maysville,Mason,Kentucky');
Insert into PLACE (ID,NAME) values (138,'Saltlick,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (139,'Esculapia Sprgs,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (140,'Portsmouth,Scioto,Ohio');
Insert into PLACE (ID,NAME) values (141,'Smyth,Virginia');
Insert into PLACE (ID,NAME) values (142,'Columbus,,Ohio');
Insert into PLACE (ID,NAME) values (143,'Columbus,Franklin,Ohio');
Insert into PLACE (ID,NAME) values (144,'Dover,Kent,Delaware');
Insert into PLACE (ID,NAME) values (145,'Levy,Florida');
Insert into PLACE (ID,NAME) values (146,'Springfield,Clark,Ohio');
Insert into PLACE (ID,NAME) values (147,'Blum,Ohio');
Insert into PLACE (ID,NAME) values (148,'Portsmouth,Ohio');
Insert into PLACE (ID,NAME) values (149,'Lawrence,Ohio');
Insert into PLACE (ID,NAME) values (150,'Stricklett,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (151,'Newbern,Pulaski,Virginia');
Insert into PLACE (ID,NAME) values (152,'Chattanooga,Tennessee');
Insert into PLACE (ID,NAME) values (153,'Flemingsburg,Fleming,Kentucky');
Insert into PLACE (ID,NAME) values (154,'Cincinnati,Hamilton,Ohio');
Insert into PLACE (ID,NAME) values (155,'Elizabethtown,Hardin,Kentucky');
Insert into PLACE (ID,NAME) values (156,'Flemingsburg,Kentucky');
Insert into PLACE (ID,NAME) values (157,'Williamsburg,Ohio');
Insert into PLACE (ID,NAME) values (158,'Nashtown,Lewis,Kentucky');
Insert into PLACE (ID,NAME) values (159,'Paris, Kentucky');
Insert into PLACE (ID,NAME) values (160,'Ludlow,Kentucky');
Insert into PLACE (ID,NAME) values (161,'Covington,Kentucky');
Insert into PLACE (ID,NAME) values (162,'Baltimore, Maryland');
Insert into PLACE (ID,NAME) values (163,'Centerville, Appanoose, Iowa');
Insert into PLACE (ID,NAME) values (164,'Knox, Indiana');
Insert into PLACE (ID,NAME) values (165,'Fristoe Bridge,Adams,Ohio');
Insert into PLACE (ID,NAME) values (166,'Cooks Mill,Cole,Illinois');
Insert into PLACE (ID,NAME) values (167,'Lancaster, Pennsylvania');
Insert into PLACE (ID,NAME) values (168,'Treber Inn');
REM INSERTING into SETTINGS
SET DEFINE OFF;
Insert into SETTINGS (THEME,DEFAULT_PERSON,DEFAULT_PERSON_TYPE) values (null,null,1);
REM INSERTING into SOURCE
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
--  DDL for Index SOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SOURCE_PK" ON "SOURCE" ("ID") 
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
--  DDL for Index GENDERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GENDERS_PK" ON "GENDER" ("ABBR") 
  ;
--------------------------------------------------------
--  DDL for Index PERSON_INFO_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PERSON_INFO_TYPE_PK" ON "PERSON_INFO_TYPE" ("ID") 
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
--------------------------------------------------------
--  DDL for Trigger MARRIAGE_VIEW_INSERT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MARRIAGE_VIEW_INSERT_TRIGGER" 
INSTEAD OF INSERT ON MARRIAGE_VIEW 
DECLARE
  PLACE_ID NUMBER;
BEGIN
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
  IF :new.GENDER IS NOT NULL THEN
    SELECT ABBR INTO GENDER_ABBR FROM GENDER WHERE GENDER.FULL_WORD=:new.GENDER;
  ELSE
    SELECT ABBR INTO GENDER_ABBR FROM GENDER WHERE GENDER.FULL_WORD='Unknown';
  END IF;
  
  INSERT INTO PERSON (NAME, GENDER) VALUES (:new.NAME, GENDER_ABBR);
  
  SELECT ID INTO P_ID FROM LAST_PERSON_INSERTED_VIEW; 
  
  -- Map FATHER_ID to FATHER_OF table
  IF :new.FATHER_ID IS NOT NULL THEN
    INSERT INTO FATHER_OF (FATHER_ID, CHILD_ID) VALUES (:new.FATHER_ID, P_ID);
  END IF;
  
  -- Map MOTHER_ID to MOTHER_OF table
  IF :new.MOTHER_ID IS NOT NULL THEN
    INSERT INTO MOTHER_OF (MOTHER_ID, CHILD_ID) VALUES (:new.MOTHER_ID, P_ID);
  END IF;
  
  -- Insert Birth
  INSERT_OR_UPDATE_BIRTH(P_ID, :new.PLACE_OF_BIRTH, :new.DATE_OF_BIRTH);
  
  -- Insert Death
  INSERT_OR_UPDATE_DEATH(P_ID, :new.PLACE_OF_DEATH, :new.DATE_OF_DEATH);
  
END;
/
ALTER TRIGGER "PERSON_VIEW_INSERT_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PERSON_VIEW_UPDATE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PERSON_VIEW_UPDATE_TRIGGER" 
INSTEAD OF UPDATE ON PERSON_VIEW
DECLARE
  GENDER_ABBR CHAR(1);
  DUMMY NUMBER;
  PLACE_OF_BIRTH_ID NUMBER;
  PLACE_OF_DEATH_ID NUMBER;
BEGIN
  IF :new.ID IS NOT NULL THEN
  
    -- Update NAME and GENDER, which are required in the tables anyway
    -- (NOT NULL constraints)
    IF :new.NAME IS NOT NULL THEN
      UPDATE PERSON SET NAME=:new.NAME WHERE ID=:new.ID;
    END IF;
    
    IF :new.GENDER IS NOT NULL THEN
      SELECT ABBR INTO GENDER_ABBR FROM GENDER WHERE GENDER.FULL_WORD=:new.GENDER;
      UPDATE PERSON SET GENDER=GENDER_ABBR WHERE ID=:new.ID;
    END IF;
    
    -- Insert / update parents
    IF :new.FATHER_ID IS NOT NULL THEN
       BEGIN
          SELECT FATHER_ID INTO DUMMY FROM FATHER_OF WHERE CHILD_ID=:new.ID;
          UPDATE FATHER_OF SET FATHER_ID=:new.FATHER_ID WHERE CHILD_ID=:new.ID;
       EXCEPTION WHEN NO_DATA_FOUND THEN
          INSERT INTO FATHER_OF (FATHER_ID, CHILD_ID) VALUES (:new.FATHER_ID, :new.ID);
       END;
    END IF;
    
    IF :new.MOTHER_ID IS NOT NULL THEN
       BEGIN
          SELECT MOTHER_ID INTO DUMMY FROM MOTHER_OF WHERE CHILD_ID=:new.ID;
          UPDATE MOTHER_OF SET MOTHER_ID=:new.MOTHER_ID WHERE CHILD_ID=:new.ID;
       EXCEPTION WHEN NO_DATA_FOUND THEN
          INSERT INTO MOTHER_OF (MOTHER_ID, CHILD_ID) VALUES (:new.MOTHER_ID, :new.ID);
       END;
    END IF;
    
    -- Update Birth
    INSERT_OR_UPDATE_BIRTH(:new.ID, :new.PLACE_OF_BIRTH, :new.DATE_OF_BIRTH);
    
    -- Update Death
    INSERT_OR_UPDATE_DEATH(:new.ID, :new.PLACE_OF_DEATH, :new.DATE_OF_DEATH);
    
  END IF;
END;
/
ALTER TRIGGER "PERSON_VIEW_UPDATE_TRIGGER" ENABLE;
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
