--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "JOE"."PERSON" 
   (	"ID" NUMBER DEFAULT NULL, 
	"FATHER_ID" NUMBER, 
	"MOTHER_ID" NUMBER, 
	"NAME" VARCHAR2(100), 
	"PLACE_OF_BIRTH" NUMBER, 
	"DATE_OF_BIRTH" DATE, 
	"PLACE_OF_DEATH" NUMBER, 
	"DATE_OF_DEATH" DATE, 
	"GENDER" CHAR(1)
   ) ;
