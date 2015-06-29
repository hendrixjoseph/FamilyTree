--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "JOE"."CHILDREN_VIEW" ("ID", "NAME", "SPOUSE", "CHILD") AS 
  SELECT 
    P."ID",
    P."NAME",
    SPOUSE."NAME" AS "SPOUSE",
    CHILD."NAME" AS CHILD
FROM 
    PERSON CHILD
INNER JOIN 
    PERSON P
ON
    CHILD.FATHER_ID = P."ID" OR CHILD.MOTHER_ID = P."ID"
LEFT OUTER JOIN
    PERSON SPOUSE
ON
    (CHILD.FATHER_ID = SPOUSE."ID" OR CHILD.MOTHER_ID = SPOUSE."ID") AND
    P."ID" <> SPOUSE."ID"
ORDER BY
    P."NAME", SPOUSE."NAME";
