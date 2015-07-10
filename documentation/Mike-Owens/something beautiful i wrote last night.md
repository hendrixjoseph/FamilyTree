From: Hendrix, Joseph D (TS)<br />
Sent: Wednesday, July 08, 2015 4:04 PM<br />
To: Owens, Michael (TS)<br />
Subject: RE: something beautiful i wrote last night<br />

Legally, in the United States, you can be married to only one person at a time. However, you can legally have multiple spouses as long as a previous marriage was dissolved or divorced, or if the spouse has died. People don’t always follow the law, so they might be illegally married to multiple people at the same time. Also, historically, polygamy has been practiced by probably every culture in the world during some time. This probably hasn’t happened anytime recent in my family tree, however.

Currently in my database, a “spouse” is simply the other parent. It is entirely possible to have children through extramarital affairs with multiple partners.

From: Owens, Michael (TS)<br />
Sent: Wednesday, July 08, 2015 3:46 PM<br />
To: Hendrix, Joseph D (TS)<br />
Subject: RE: something beautiful i wrote last night<br />

Technically you can only be married to one person

From: Hendrix, Joseph D (TS) <br />
Sent: Wednesday, July 08, 2015 3:31 PM<br />
To: Owens, Michael (TS)<br />
Subject: RE: something beautiful i wrote last night

I might do the `FIRST_NAME, LAST_NAME` columns later. Haven’t decided yet.

The problem with `SPOUSE_ID` is that there might either be no (null) spouses, or multiple spouses.

`MOTHER_ID` and `FATHER_ID` might be null a lot of times.

From: Owens, Michael (TS)<br />
Sent: Wednesday, July 08, 2015 3:29 PM<br />
To: Hendrix, Joseph D (TS)<br />
Subject: RE: something beautiful i wrote last night

Why not have a person_table like this
```sql
PERSON_TABLE
PERSON_KEY
FIRST_NAME
LAST_NAME
GENDER
SPOUSE_ID
MOTHER_ID
FATHER_ID
```

From: Hendrix, Joseph D (TS)<br />
Sent: Wednesday, July 08, 2015 3:12 PM<br />
To: Owens, Michael (TS)<br />
Subject: RE: something beautiful i wrote last night

You can look at everything I’ve done with the database here -> https://github.com/hendrixjoseph/FamilyTree/tree/master/sql 

It’s just a file directory, so it should be easy to traverse.

The directories are as follows:

CONSTRAINTS	Contains constraints such as primary key and not null.<br />
DATA_TABLE	Contains the data in the tables in a csv file. Includes headers.<br />
DATA_VIEW	Contains the data represented in the views in a csv file. Includes headers.<br />
INDEXES	Index based on primary keys. Don’t really know what this is.<br />
REF_CONSTRAINTS	Foreign key constraints.<br />
SEQUENCES	Sequences.<br />
TABLES	CREATE TABLE sql command with columns.<br />
TRIGGERS	Beautiful, beautiful triggers.<br />
VIEWS	VIEWS sql command.

“master.sql” is everything above in one file, except that the table data is in INSERTs rather than csv.

From: Owens, Michael (TS) <br />
Sent: Wednesday, July 08, 2015 2:47 PM<br />
To: Hendrix, Joseph D (TS)<br />
Subject: RE: something beautiful i wrote last night

All many tables  do you have?<br />
How many tables are used in your example yesterday.

Please send me those tables used in your example:

With sample output of the headers and a few rows of data from each table.

From: Hendrix, Joseph D (TS)<br />
Sent: Wednesday, July 08, 2015 1:50 PM<br />
To: Owens, Michael (TS)<br />
Subject: something beautiful i wrote last night<br />

```sql
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
```
