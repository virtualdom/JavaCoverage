# JavaCoverage
A small test coverage tool for Java tests

# How to use
In your Maven project's `pom.xml` file, simply add the following plugin

```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <additionalClasspathElements>
            <additionalClasspathElement>${project.basedir}/asm-all-5.0.3.jar</additionalClasspathElement>
          </additionalClasspathElements>
          <argLine>-javaagent:./agent.jar=${project.basedir}/stmt-cov.txt:class/prefix/goes/here</argLine>
        </configuration>
      </plugin>
```
