@ECHO OFF

FOR /D %%D IN (*) DO RD/S/Q %%D

DEL master.sql

PAUSE