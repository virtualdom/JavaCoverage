# JavaCoverage
A small test coverage tool for Java tests

# How to use
In your Maven project's `pom.xml` file, make sure the following tags exist.

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.19.1</version>
        <configuration>
          <additionalClasspathElements>
            <additionalClasspathElement>${project.basedir}/asm-all-5.0.3.jar</additionalClasspathElement>
            <additionalClasspathElement>${project.basedir}/coverage-tool.jar</additionalClasspathElement>
          </additionalClasspathElements>
          <argLine>-javaagent:./coverage-tool.jar=${project.basedir}/stmt-cov.txt:full/class/path/goes/here</argLine>
          <properties>
            <property>
              <name>listener</name>
              <value>cs6367.listener.TestStartListener</value>
            </property>
          </properties>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```

