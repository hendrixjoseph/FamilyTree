REM INSERTING into JOE.PERSON
SET DEFINE OFF;
Insert into JOE.PERSON (ID,FATHER_ID,MOTHER_ID,NAME,PLACE_OF_BIRTH,DATE_OF_BIRTH,PLACE_OF_DEATH,DATE_OF_DEATH) values (1,2,3,'William Zenos Thoroman','Ohio',to_date('04-MAR-27','DD-MON-RR'),'Jacksonville, Ohio',to_date('29-JAN-00','DD-MON-RR'));
Insert into JOE.PERSON (ID,FATHER_ID,MOTHER_ID,NAME,PLACE_OF_BIRTH,DATE_OF_BIRTH,PLACE_OF_DEATH,DATE_OF_DEATH) values (2,null,null,'Samuel Thoroman','Washington, Pennsylvania',to_date('23-MAY-95','DD-MON-RR'),'Ohio',to_date('12-DEC-81','DD-MON-RR'));
Insert into JOE.PERSON (ID,FATHER_ID,MOTHER_ID,NAME,PLACE_OF_BIRTH,DATE_OF_BIRTH,PLACE_OF_DEATH,DATE_OF_DEATH) values (3,null,null,'Cynthiann McDonald Reynolds',null,null,null,null);
