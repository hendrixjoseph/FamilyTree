@ECHO OFF

FOR /D %%D IN (*) DO RD/S/Q %%D

DEL export.sql

PAUSE