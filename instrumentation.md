How to instrument and compile
=============================

First, we need to make the instrumenting jar file

1. Navigate to src
2. javac -cp .:../lib/asm-all-5.0.3.jar ./cs6367/agent/Premain.java ./cs6367/transformer/*.java
3. jar cvfm ../test.jar ../manifest/manifest.mf ./cs6367/agent/Premain.class ./cs6367/transformer/*.class

  Now we must compile the classes we wish to test

4. javac cs6367/test/TestInstrumentation.java

  Finally, we run the class we want to test!

5. cd src
6. java -cp .:../lib/asm-all-5.0.3.jar -javaagent:../test.jar cs6367.test.TestInstrumentation

  See the results!

7. cat stmt-cov.txt

