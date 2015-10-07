@ECHO OFF

FOR /D %%D IN (*) DO (

	IF NOT %%D==.git ( 
		RD/S/Q %%D
	)
)

FOR %%F IN (*) DO (

	IF NOT %%F==.gitignore (
		IF NOT %%F==delete.bat (
			DEL %%F
		)
	)
)

PAUSE