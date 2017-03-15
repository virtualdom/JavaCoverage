package cs6367.coverageTool;

import java.lang.instrument.Instrumentation;
import java.io.File;

public class Premain {
  public static void premain (String arguments, Instrumentation instrumentation) {
    String [] terms;
    String filename, classPrefix;
    if (arguments != null) terms = arguments.split(":");
    else {
      System.out.println("Please be sure the -javaagent flag is set.");
      System.out.println("-javaagent:agent.jar=output/file/path:full/class/path/to/test");
      return;
    }
    File coverageFile = new File(terms[0]);
    if (coverageFile.exists()) {
      System.out.println("File " + terms[0] + " already exists.");
      System.out.println("We don't want to overwrite your file, so please delete it and try again.");
      return;
    }
    filename = terms[0];
    if (terms.length < 2) classPrefix = "";
    else classPrefix = terms[1];
    instrumentation.addTransformer(new TransformClassFile(filename, classPrefix));
  }
}
