--------------------------------------------------------
--  DDL for View LAST_NAME_COUNT_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "LAST_NAME_COUNT_VIEW" ("COUNT", "LAST_NAME") AS 
  SELECT COUNT(LAST_NAME) COUNT, LAST_NAME
FROM (SELECT REGEXP_SUBSTR(NAME,'\w+$') AS LAST_NAME FROM PERSON)
GROUP BY LAST_NAME
ORDER BY COUNT DESC;
