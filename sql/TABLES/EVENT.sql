--------------------------------------------------------
--  DDL for Table EVENT
--------------------------------------------------------

  CREATE TABLE "EVENT" 
   (	"PERSON_ID" NUMBER, 
	"TYPE" VARCHAR2(20), 
	"PLACE_ID" NUMBER, 
	"MONTH" VARCHAR2(20) DEFAULT NULL, 
	"DAY" NUMBER DEFAULT NULL, 
	"YEAR" NUMBER DEFAULT NULL, 
	"ABOUT" NUMBER DEFAULT 0
   ) ;
