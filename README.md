<!-- 
  When copying contents to index.md, do the following:
  Remove table of contents
  Replace: ]\(([^:http:#]) with ](https://github.com/hendrixjoseph/FamilyTree/blob/master/\1
-->

![FamilyTree Icon](src/site/resources/images/Family-Tree-icon-128.png)

# FamilyTree

FamilyTree is a simple web application using JavaServerFaces, Java EE, and a SQL database to load, display, edit, and examine a family tree. It was initially created as a project for CS7700 Advanced Database Systems at Wright State, and is currently being used for CS7720 Data Mining at the same school.

## Readme Table of Contents

* [Contents of Repository](#user-content-contents-of-repository) 
 * [Configuration files](#user-content-configuration-files)
   * [NetBeans Configuration Files](#netBeans-configuration-files)
    * [IntelliJ Configuration Files](#intellij-configuration-files)
    * [Maven Configuration Files](#maven-configuration-files)
    * [Git Configuration File](#git-configuration-file)
 * [Source code](#user-content-source-code)
 * [SQL](#user-content-sql)
 * [Directory structure](#user-content-directory-structure)
* [Setup](#user-content-setup)
* [Tools](#user-content-tools)
 * [JavaScript Libraries](#user-content-javascript-libraries)
 * [Server and Database](#user-content-server-and-database)
 * [Development Tools](#user-content-development-tools)
   * [Desktop Tools](#user-content-desktop-tools)
    * [NetBeans Plugins](#user-content-netbeans-plugins)
    * [Android Tools](#user-content-android-tools)
* [Icon](#icon)
* [License](#user-content-license)

## Contents of Repository

### Configuration files

#### NetBeans Configuration Files

This repository contains two NetBeans configuration files, namely [`nb-configuration.xml`](nb-configuration.xml) and [`nbactions.xml`](nbactions.xml). 

#### IntelliJ Configuration Files

In addition to NetBeans configuration, it contains an IntelliJ configuration file [`familyTree.iml`](familyTree.iml) and configuration directory [`.idea/`](.idea/).

#### Maven Configuration Files

Also, it contains the standard Maven [`pom.xml`](pom.xml) and an extra [`ojdbc7.pom`](ojdbc7.pom) to install Oracle's JDBC driver into the local repository.

#### Git Configuration File

Finally, it contains [`.gitignore`](.gitignore) so I don't accidently commit and push things I don't want onto GitHub.

### Source code

The following types of files can be found in this project:

* [`*.java`](src/main/java)
* [`*.xhtml`](src/main/webapp)
* [`*.sql`](sql/)
* [`*.css`](src/main/webapp/WEB-INF/resource/css)

The following file type currently exists in the project, however, the files themselves are not written by me:

* [`*.js`](src/main/webapp/WEB-INF/resource/js)

### SQL

All the necessary SQL statements to create the database (should be) is in [`sql/export.sql`](sql/export.sql). All the subdirectories in [`sql/`](sql/) contain pretty much the same material as `export.sql`, they are only in different directories for easier human viewing.

### Directory structure

If any directory structure of this project does not match the standard Maven configuration, the aim is to eventually change this project to match a standard Maven project.

## Setup

Once this project is downloaded, there is one step required before it can be built by Maven.

1. Download and install `ojdbc7.jar`

   This can be found at http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html. If that's a dead link, try [Googling `ojdbc7.jar`](https://www.google.com/search?q=ojdbc7.jar).

   Install the jar file with one of the following ways:

   **a.** `mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true`<br />
   **b.** `mvn install:install-file -Dfile=ojdbc7.jar -DpomFile=ojdbc7.pom`
   
  Option b can also be easily done through either NetBeans or IntelliJ. I've set it up so there should be minimal clicks:
  
  **NetBeans:** Under the Projects view, right-click your project, go to "Custom", and select "install ojdbc7".<br />
  **IntelliJ:** In the run configuration drop down in the top right, select "install ojdbc7.jar".
   
In order to run this project, a Java EE server and a SQL database need to be installed beforehand. I use [GlassFish 4.1](https://glassfish.java.net/) and [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/overview/index.html). You will also need to setup a JDBC connection pool in your Java EE server. [I found this website helpful for setting it up in GlassFish.](https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/) The settings I used (other than username and password) are:

<table>
<tr><td>Pool name: </td><td>FamilyTreePool</td></tr>
<tr><td>Resource type:</td><td><code>java.sql.Driver</code></td></tr>
<tr><td>Database Driver Vendor: </td><td>Oracle</td></tr>
<tr><td>Initial and Minimum Pool Size:</td><td>Zero</td></tr>
<tr><td>URL:</td><td><code>jdbc:oracle:thin:@localhost:1521:XE</code></td></tr>
</table>

## Tools

In addition to the [dependencies](http://hendrixjoseph.github.io/FamilyTree/dependencies.html) listed in the [pom.xml](pom.xml), I am using the following:

### JavaScript Libraries

 * [Data-Driven Documents (D3)](http://d3js.org)
 * [C3.js | D3-based reusable chart library](http://c3js.org)
 * [Word Cloud Generator](https://www.jasondavies.com/wordcloud)

### Server and Database

* [GlassFish 4.1](https://glassfish.java.net/)
* [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/overview/index.html)
 
### Development Tools

#### Desktop Tools

* [IntelliJ IDEA 14.1.5](https://www.jetbrains.com/idea) Ultimate Edition [Student License](https://www.jetbrains.com/student)
* [NetBeans IDE 8.0.2](https://netbeans.org)
* [Oracle SQL Developer 4.1.0.19](http://www.oracle.com/technetwork/developer-tools/sql-developer/overview/index-097090.html)
* [Notepad++ v6.8.6](https://notepad-plus-plus.org)
* [TortoiseGit 1.8.15.0](https://tortoisegit.org)
* [Maven 3.3.3](https://maven.apache.org)

#### NetBeans Plugins

* [NetBeans License Changer Plugin](http://plugins.netbeans.org/plugin/17960/license-changer)

#### Android Tools

* [ForkHub for GitHub](https://play.google.com/store/apps/details?id=jp.forkhub) ([on GitHub](https://github.com/jonan/ForkHub))
* [SGit](https://play.google.com/store/apps/details?id=me.sheimi.sgit) ([on GitHub](https://github.com/sheimi/SGit))
* [Quoda](http://www.getquoda.com/)

Tested in [Firefox 39.0](https://www.mozilla.org/en-US/)

## Icon

The FamilyTree Icon (![FamilyTree Icon](src/site/resources/images/Family-Tree-icon-32.png))  is originally from:

http://www.iconarchive.com/show/the-community-icons-by-afterglow/Family-Tree-icon.html

which attributes it to http://www.afterglow.ie.

## License

This project is for personal academic purposes. I don't care what you do with it, as long as I get credit. Therefore I am using the [the MIT License](LICENSE.md).

----------------------

<table>
<tr><td><a href="https://people.wright.edu/hendrix.11">Joseph Hendrix</a></td></tr>
<tr><td>Created for CS7700 Advanced Database Systems</td></tr>
<tr><td>Version 2.0 is for CS7720 Data Mining</td></tr>
<tr><td><a href="http://www.wright.edu">Wright State University</a></td></tr>
<tr><td><a href="https://engineering-computer-science.wright.edu">College of Engineering & Computer Science</a></td></tr>
<tr><td><a href="https://engineering-computer-science.wright.edu/computer-science-and-engineering">Department of Computer Science & Engineering</a></td></tr>
</table>
