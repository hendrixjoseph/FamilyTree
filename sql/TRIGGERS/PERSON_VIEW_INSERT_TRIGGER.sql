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
      INSERT_PLACE_PROCEDURE(:new.PLACE_OF_BIRTH, PLACE_OF_BIRTH_ID);
      INSERT INTO BIRTH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_BIRTH_ID);
    END;
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
      INSERT_PLACE_PROCEDURE(:new.PLACE_OF_DEATH, PLACE_OF_DEATH_ID);
      INSERT INTO BIRTH (PERSON_ID, PLACE_ID) VALUES (P_ID, PLACE_OF_DEATH_ID);
    END;
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
