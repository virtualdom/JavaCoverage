package cs6367.coverageTool;

import java.lang.instrument.Instrumentation;

public class Premain {
  public static void premain (String arguments, Instrumentation instrumentation) {
    String [] terms;
    if (arguments != null) terms = arguments.split(":");
    else {
      terms = new String[1];
      terms[0] = "/Users/dom/Desktop/stmt-cov.txt";
    }
    instrumentation.addTransformer(new TransformClassFile(terms[0]));
  }
}
