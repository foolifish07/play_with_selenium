

### Refer

http://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html

http://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html


## install dependencies
```
mvn compile
```


### Run a single test

- `-test`

  This parameter overrides the includes/excludes parameters, and the TestNG suiteXmlFiles parameter. 

http://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html

- **Don't forget** to set test directory in `pom.xml`

```xml
...
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.20</version>
          <configuration>
          	<testSourceDirectory>${basedir}/src/main/java</testSourceDirectory
           	<testClassesDirectory>
              ${project.build.directory}/classes
            </testClassesDirectory>
...
```


```
# 1. test local driver
mvn surefire:test -Dtest=PlayWebDriver#localDriver

# 2. test remote driver
# First launch chromedriver server
mvn surefire:test -Dtest=PlayWebDriver#remoteDriver

# 3. test multisession
mvn surefire:test -Dtest=PlayWebDriver#multiSession
```
### Parallel test

- Using Suite XML Files

  This configuration will override the includes and excludes patterns and run all tests in the suite files.

```bash
# run parallel
mvn surefire:test -Dsurefire.suiteXmlFiles="src/main/resources/testng-parallel.xml"
```



### Selenide
```
# start up
$ mvn surefire:test -Dtest=PlaySelenide#startup

# screenshot
$ mvn surefire:test \
  -Dtest=PlaySelenide#screenshot \
  -Dselenide.reports=test-result-command/reports

```
### 