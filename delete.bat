@ECHO OFF

FOR /D %%D IN (*) DO (

	IF NOT %%D==.git (
		IF NOT %%D==_layouts (
			RD/S/Q %%D
		)
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