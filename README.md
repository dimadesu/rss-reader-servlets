RSS Reader With Servlets And JSP
================================

##Requirements

- Java SE 8 JDK
- Maven 3.2.3
- Tomcat 8.0.12
- HSQLDB 2.3.2

##Build

Project can be built by executing `mvn clean install compile` in the system command prompt from the project root directory. Executing this should compile sources and create the war file `rss-reader-servlets.war` in the `target` folder of project.

##Database

HSQLDB is used as the database for the project. At the moment of writing the latest version is 2.3.2. Please get it as zip archive from it's website http://hsqldb.org/ and extract it. To run it double click `\bin\runServer.bat` in the extracted folder. Schema is located in project folder `src\main\resources.sql`, execute it to create tables.

##Deploy to Tomcat

Stop Tomcat by double-clicking `TOMCAT_HOME/webapps/shutdown.bat`. Copy generated war into `TOMCAT_HOME/webapps/` folder. Start Tomcat by executing `TOMCAT_HOME/webapps/startup.bat`.

##Inside the Browser

Navigate to `http://localhost:8080/rss-reader-servlets/`
