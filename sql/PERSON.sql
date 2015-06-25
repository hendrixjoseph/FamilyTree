--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "JOE"."PERSON" 
   (	"ID" NUMBER DEFAULT NULL, 
	"FATHER_ID" NUMBER, 
	"MOTHER_ID" NUMBER, 
	"NAME" VARCHAR2(100 BYTE), 
	"PLACE_OF_BIRTH" VARCHAR2(100 BYTE), 
	"DATE_OF_BIRTH" DATE, 
	"PLACE_OF_DEATH" VARCHAR2(100 BYTE), 
	"DATE_OF_DEATH" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAMILY_TREE" ;
