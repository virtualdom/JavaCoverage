package cs6367.agent;

import java.lang.instrument.Instrumentation;
import cs6367.transformer.TransformClassFile;

public class Premain {
  public static void premain (String arguments, Instrumentation instrumentation) {
    String [] terms;
    if (arguments != null) terms = arguments.split(":");
    else {
      terms = new String[1];
      terms[0] = "stmt-cov.txt";
    }
    instrumentation.addTransformer(new TransformClassFile(terms[0]));
  }
}
