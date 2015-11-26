--------------------------------------------------------
--  DDL for View FIRST_NAME_COUNT_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "FIRST_NAME_COUNT_VIEW" ("COUNT", "FIRST_NAME") AS 
  SELECT COUNT(FIRST_NAME) COUNT, FIRST_NAME
FROM (SELECT REGEXP_SUBSTR(NAME,'^\w+') AS FIRST_NAME FROM PERSON)
GROUP BY FIRST_NAME
ORDER BY COUNT DESC;
