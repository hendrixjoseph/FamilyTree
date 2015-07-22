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
