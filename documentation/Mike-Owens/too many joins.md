From: Hendrix, Joseph D (TS)<br />
Sent: Thursday, July 02, 2015 9:37 AM<br />
To: Owens, Michael (TS)<br />
Subject: RE: too many joins?

http://stackoverflow.com/questions/4164653/whats-the-purpose-of-sql-keyword-as

“I see old DBA people tend to write statements without AS, but most of the new tutorials use it.”

From: Owens, Michael (TS)<br />
Sent: Thursday, July 02, 2015 9:32 AM<br />
To: Hendrix, Joseph D (TS)<br />
Subject: RE: too many joins?

```sql
SELECT
P.ID,
P.FATHER_ID,
F.NAME AS FATHER,
P.MOTHER_ID,
M.NAME AS MOTHER,
P.NAME,
GENDERS.FULL_WORD AS GENDER,
B.NAME AS PLACE_OF_BIRTH,
P.DATE_OF_BIRTH,
D.NAME AS PLACE_OF_DEATH,
P.DATE_OF_DEATH
FROM
PERSON P,
PERSON F,
PERSON M,
PLACE D,
PLACE B,
GENDERS
Where P.GENDER = GENDERS.ABBR(+)
And   P.FATHER_ID = F."ID" (+)
And   P.MOTHER_ID = M."ID" (+)
And   P.PLACE_OF_DEATH = D.ID(+)
And   P.PLACE_OF_BIRTH = B.ID(+);
```

From: Hendrix, Joseph D (TS)<br />
Sent: Thursday, July 02, 2015 9:18 AM<br />
To: Owens, Michael (TS)<br />
Subject: too many joins?

Mike,

Is it possible to have too many joins? For instance:

```sql
CREATE OR REPLACE VIEW "JOE"."PERSON_VIEW" ("ID", "FATHER_ID", "FATHER", "MOTHER_ID", "MOTHER", "NAME", "GENDER", "PLACE_OF_BIRTH", "DATE_OF_BIRTH", "PLACE_OF_DEATH", "DATE_OF_DEATH") AS
SELECT
P."ID",
P.FATHER_ID,
F."NAME" AS FATHER,
P.MOTHER_ID,
M."NAME" AS MOTHER,
P."NAME",
GENDERS.FULL_WORD AS GENDER,
B.NAME AS PLACE_OF_BIRTH,
P.DATE_OF_BIRTH,
D.NAME AS PLACE_OF_DEATH,
P.DATE_OF_DEATH
FROM
PERSON P
JOIN
GENDERS
ON
P.GENDER = GENDERS.ABBR
LEFT OUTER JOIN
PERSON F
ON
P.FATHER_ID = F."ID"
LEFT OUTER JOIN
PERSON M
ON
P.MOTHER_ID = M."ID"
LEFT OUTER JOIN
PLACE D
ON
P.PLACE_OF_DEATH = D.ID
LEFT OUTER JOIN
PLACE B
ON
P.PLACE_OF_BIRTH = B.ID;
```
