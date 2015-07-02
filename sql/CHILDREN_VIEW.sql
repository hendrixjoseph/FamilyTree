--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "JOE"."CHILDREN_VIEW" ("ID", "SPOUSE_ID", "SPOUSE", "CHILD_ID", "CHILD") AS 
  SELECT 
    P."ID",
    SPOUSE."ID" AS "SPOUSE_ID",
    SPOUSE."NAME" AS "SPOUSE",
    CHILD."ID" AS "CHILD_ID",
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
