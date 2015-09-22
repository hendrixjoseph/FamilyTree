--------------------------------------------------------
--  DDL for View CHILDREN_VIEW
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CHILDREN_VIEW" ("ID", "NAME", "CHILD_ID", "CHILD_NAME") AS 
  SELECT DISTINCT
    P.ID,
    P.NAME,
    CHILD.ID CHILD_ID,
    CHILD.NAME CHILD_NAME
FROM 
    PERSON P,
    PERSON CHILD,
    FATHER_OF,
    MOTHER_OF
WHERE
    (P.ID = FATHER_OF.FATHER_ID AND CHILD.ID = FATHER_OF.CHILD_ID) OR
    (P.ID = MOTHER_OF.MOTHER_ID AND CHILD.ID = MOTHER_OF.CHILD_ID);
