--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "CHILD_ID") AS 
  SELECT
    FATHER_ID ID,
    CHILD_ID
FROM
    FATHER_OF
UNION
SELECT
    MOTHER_ID ID,
    CHILD_ID
FROM
    MOTHER_OF;
