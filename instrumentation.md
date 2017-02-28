How to instrument and compile
=============================

First, we need to make the instrumenting jar file

1. Navigate to project root.
2. javac -cp .:./lib/javassist-3.14.0.jar ./src/cs6367/agent/Premain.java ./src/cs6367/transformer/TransformClassFile.java
3. jar cvfm ./test.jar ./manifest/manifest.mf ./src/cs6367/agent/Premain.class ./src/cs6367/transformer/TransformClassFile.class

  Now we must compile the classes we wish to test

4. javac src/cs6367/test/*.java

  Finally, we run the class we want to test!

5. cd src
6. java -cp .:../lib/javassist-3.14.0.jar -javaagent:../test.jar cs6367.test.TestInstrumentation
