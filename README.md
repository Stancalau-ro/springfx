# SpringFX #

An short project that exemplifies how you can integrate Spring with JavaFX.

### Prerequisites ###

* [Download Maven](https://maven.apache.org/download.cgi)
* Ensure JAVA_HOME environment variable is set
* Ensure PATH environment variable contains MAVEN_HOME/bin
* Run `mvn clean install` in the project root to build the entire project

### Running the Application###

* In the root folder, enter the command `mvn jfx:run`

Please note that 'mvn clean install' might not work on Java 7. A workaround would be to add the following to your pom.xml <dependencies>:


```
#!xml

<dependency>
  <groupId>javafx</groupId>
  <artifactId>jfxrt</artifactId>
  <version>2.2</version>
  <scope>system</scope>
  <systemPath>C:\Program Files\Java\jre7\lib\jfxrt.jar</systemPath> <!-- Windows -->
  <!-- <systemPath>/usr/lib/jvm/java-7-oracle/jre/lib/jfxrt.jar</systemPath> on Linux -->
</dependency>
```



The recommended way to go would be just to use Java 8. If you are not sure about what version of Java your system is using, run:
 >java -version

and

 >javac -version


More information about this project can be found here: http://stancalau.ro/javafx-and-spring/