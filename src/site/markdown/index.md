# FamilyTree

FamilyTree is a simple web application using JavaServerFaces, Java EE, and a SQL database to load, display, edit, and examine a family tree. It was initially created as a project for CS7700 Advanced Database Systems at Wright State, and is currently being used for CS7720 Data Mining at the same school.

## Contents of Repository

### Configuration files

#### NetBeans Configuration Files

This repository contains two NetBeans configuration files, namely [`nb-configuration.xml`](https://github.com/hendrixjoseph/FamilyTree/blob/master/nb-configuration.xml) and [`nbactions.xml`](https://github.com/hendrixjoseph/FamilyTree/blob/master/nbactions.xml). 

#### IntelliJ Configuration Files

In addition to NetBeans configuration, it contains an IntelliJ configuration file [`familyTree.iml`](https://github.com/hendrixjoseph/FamilyTree/blob/master/familyTree.iml) and configuration directory [`.idea/`](https://github.com/hendrixjoseph/FamilyTree/blob/master/.idea/).

#### Maven Configuration Files

Also, it contains the standard Maven [`pom.xml`](pom.xml) and an extra [`ojdbc7.pom`](https://github.com/hendrixjoseph/FamilyTree/blob/master/ojdbc7.pom) to install Oracle's JDBC driver into the local repository.

#### Git Configuration File

Finally, it contains [`.gitignore`](https://github.com/hendrixjoseph/FamilyTree/blob/master/.gitignore) so I don't accidently commit and push things I don't want onto GitHub.

### Source code

The following types of files can be found in this project:

* `*.java`
* `*.xhtml`
* `*.sql`

The following types of files are not currently found in this project, but some may be added in the future:

* `*.css`
* `*.js`

### SQL

All the necessary SQL statements to create the database (should be) is in [sql/master.sql](https://github.com/hendrixjoseph/FamilyTree/blob/master/sql/master.sql). All the subdirectories in [sql/](https://github.com/hendrixjoseph/FamilyTree/blob/master/sql/) contain pretty much the same material as master.sql, they are only in different directories for easier human viewing.

### Directory structure

If any directory structure of this project does not match the standard Maven configuration, the aim is to eventually change this project to match a standard Maven project.

## Setup

Once this project is downloaded, there is one step required before it can be built by Maven.

1. Download and install `ojdbc7.jar`

   This can be found at http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html. If that's a dead link, try [Googling `ojdbc7.jar`](https://www.google.com/search?q=ojdbc7.jar).

   Install the jar file with one of the following ways:

   a. `mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true`<br />
   b. `mvn install:install-file -Dfile=ojdbc7.jar -DpomFile=ojdbc7.pom`<br />
   c. I've included the equivalent of option b as a Maven action in NetBeans. In NetBeans, under the Projects view, right-click your project, go to "Custom", and select "install ojdbc7".
   
In order to run this project, a Java EE server and a SQL database need to be installed beforehand. I use [GlassFish 4.1](https://glassfish.java.net/) and [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/overview/index.html). You will also need to setup a JDBC connection pool in your Java EE server. [I found this website helpful for setting it up in GlassFish.](https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/) The settings I used (other than username and password) are:

 | |
--- | --- 
Pool name:  | FamilyTreePool
Resource type: | java.sql.Driver
Database Driver Vendor:  | Oracle
Initial and Minimum Pool Size: | Zero
URL: | jdbc:oracle:thin:@localhost:1521:XE

## Tools

In addition to the [dependencies](http://hendrixjoseph.github.io/FamilyTree/dependencies.html) listed in the [pom.xml](pom.xml), I am using the following:

### Server and Database

* [GlassFish 4.1](https://glassfish.java.net/)
* [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/overview/index.html)
 
### Development Tools

#### Desktop Tools

* [IntelliJ IDEA 14.1.5](https://www.jetbrains.com/idea) Ultimate Edition [Student License](https://www.jetbrains.com/student)
* [NetBeans IDE 8.0.2](https://netbeans.org)
* [Oracle SQL Developer 4.1.0.19](http://www.oracle.com/technetwork/developer-tools/sql-developer/overview/index-097090.html)
* [Notepad++ v6.7.8.2](https://notepad-plus-plus.org)
* [TortoiseGit 1.8.15.0](https://tortoisegit.org)
* [Maven 3.3.3](https://maven.apache.org)

#### NetBeans Plugins

* [NetBeans License Changer Plugin](http://plugins.netbeans.org/plugin/17960/license-changer)

#### Android Tools

* [ForkHub for GitHub](https://play.google.com/store/apps/details?id=jp.forkhub) ([on GitHub](https://github.com/jonan/ForkHub))
* [SGit](https://play.google.com/store/apps/details?id=me.sheimi.sgit) ([on GitHub](https://github.com/sheimi/SGit))
* [Quoda](http://www.getquoda.com/)

Tested in [Firefox 39.0](https://www.mozilla.org/en-US/)

## License

This project is for personal academic purposes. I don't care what you do with it, as long as I get credit. Therefore I am using the [the MIT License](https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md).

----------------------

 |
--- |
[Joseph Hendrix](https://people.wright.edu/hendrix.11) |
Created for CS7700 Advanced Database Systems |
Version 2.0 is for CS7720 Data Mining |
[Wright State University](http://www.wright.edu) |
[College of Engineering & Computer Science](https://engineering-computer-science.wright.edu) |
[Department of Computer Science & Engineering](https://engineering-computer-science.wright.edu/computer-science-and-engineering) |
