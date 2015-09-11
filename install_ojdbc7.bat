@echo off

set "mvn=C:\Program Files\NetBeans 8.0.2\java\apache-maven-3.3.3\bin\mvn.bat"

call "%mvn%" install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true

pause