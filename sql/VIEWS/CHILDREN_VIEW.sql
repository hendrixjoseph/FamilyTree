--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "CHILD_ID") AS 
  SELECT
    ID,
    CHILD_ID
FROM
    FATHER
UNION
SELECT
    ID,
    CHILD_ID
FROM
    MOTHER;
