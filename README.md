<!-- 
  When copying contents to index.md, do the following:
  Remove: README.md
  Remove: Readme 
  Remove: user-content-
  Replace: ]\(([^:http:#]) with ](https://github.com/hendrixjoseph/FamilyTree/blob/master/\1
  Replace: ```(.+) with {% highlight \1 %}
  Replace: ```[^A-z] with {% endhighlight %}
-->

# FamilyTree

FamilyTree is a simple web application using JavaServerFaces, Java EE, and a SQL database to load, display, edit, and examine a family tree. It was initially created as a project for CS7700 Advanced Database Systems at Wright State, and is currently being used for CS7720 Data Mining at the same school.

## Readme Table of Contents

* [Contents of Repository](README.md#user-content-contents-of-repository) 
 * [Configuration files](README.md#user-content-configuration-files)
 * [Source code](README.md#user-content-source-code)
 * [SQL](README.md#user-content-sql)
 * [Directory structure](README.md#user-content-directory-structure)
* [Setup](README.md#user-content-setup)
* [Tools](README.md#user-content-tools)
 * [Server and Database](README.md#user-content-server-and-database)
 * [Development Tools](README.md#user-content-development-tools)
   * [Desktop Tools](README.md#user-content-desktop-tools)
    * [NetBeans Plugins](README.md#user-content-netbeans-plugins)
    * [Android Tools](README.md#user-content-android-tools)
* [License](README.md#user-content-license)

## Contents of Repository

### Configuration files

This repository contains two NetBeans configuration files, namely [`nb-configuration.xml`](nb-configuration.xml) and [`nbactions.xml`](nbactions.xml). Also, it contains the standard Maven [`pom.xml`](pom.xml) and an extra [`ojdbc7.pom`](ojdbc7.pom) to install Oracle's JDBC driver into the local repository. Finally, it contains [`.gitignore`](.gitignore) so I don't accidently commit and push things I don't want onto GitHub.

### Source code

The following types of files can be found in this project:

* `*.java`
* `*.xhtml`
* `*.sql`

The following types of files are not currently found in this project, but some may be added in the future:

* `*.css`
* `*.js`

### SQL

All the necessary SQL statements to create the database (should be) is in [sql/master.sql](sql/master.sql). All the subdirectories in [sql/](sql/) contain pretty much the same material as master.sql, they are only in different directories for easier human viewing.

### Directory structure

If any directory structure of this project does not match the standard Maven configuration, the aim is to eventually change this project to match a standard Maven project.

## Setup

Once this project is downloaded, there are two steps required before it can be built by Maven.

1. Download and install `ojdbc7.jar`

   This can be found at http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html. If that's a dead link, try [Googling `ojdbc7.jar`](https://www.google.com/search?q=ojdbc7.jar).

   Install the jar file with one of the following ways:

   a. `mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true`<br />
   b. `mvn install:install-file -Dfile=ojdbc7.jar -DpomFile=ojdbc7.pom`<br />
   c. I've included the equivalent of option b as a Maven action in NetBeans. In NetBeans, under the Projects view, right-click your project, go to "Custom", and select "install ojdbc7".

2. Setup your database configuration for [`persistence.xml`](src/main/resources/META-INF/persistence.xml).

   In my [`persistence.xml`](src/main/resources/META-INF/persistence.xml) you'll see the following:

```xml
<property name="javax.persistence.jdbc.driver" value="${dbdriver}" />
<property name="javax.persistence.jdbc.url" value="${dburl}" />
<property name="javax.persistence.jdbc.user" value="${dbuser}" />
<property name="javax.persistence.jdbc.password" value="${dbpassword}" />
```

   The variables (e.g. ${dbdriver}) are "filtered" by Maven. I have these values in my [`settings.xml`](settings.xml) in my local `.m2` directory. I'm not going to publish what my username and password are. You need to either change the values here in the [`persistence.xml`](src/main/resources/META-INF/persistence.xml) or set the properties in the `settings.xml`:

```xml
<dbdriver>oracle.jdbc.OracleDriver</dbdriver>
<dbuser>user</dbuser>
<dbpassword>password</dbpassword>
<dburl>jdbc:oracle:thin:@localhost:1521:XE</dburl>
```

## Tools

In addition to the [dependencies](http://hendrixjoseph.github.io/FamilyTree/dependencies.html) listed in the [pom.xml](pom.xml), I am using the following:

### Server and Database

* [GlassFish 4.1](https://glassfish.java.net/)
* [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/overview/index.html)
 
### Development Tools

#### Desktop Tools

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

This project is for personal academic purposes. I don't care what you do with it, as long as I get credit. Therefore I am using the [the MIT License](LICENSE.md).

----------------------

[Joseph Hendrix](https://people.wright.edu/hendrix.11)<br />
Created for CS7700 Advanced Database Systems<br />
Version 2.0 is for CS7720 Data Mining<br />
[Wright State University](http://www.wright.edu)<br />
[College of Engineering & Computer Science](https://engineering-computer-science.wright.edu)<br />
[Department of Computer Science & Engineering](https://engineering-computer-science.wright.edu/computer-science-and-engineering)