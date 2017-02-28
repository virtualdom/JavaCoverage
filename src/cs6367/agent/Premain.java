package cs6367.agent;

import java.lang.instrument.Instrumentation;
import cs6367.transformer.TransformClassFile;

public class Premain {
  public static void premain (String arguments, Instrumentation instrumentation) {
    System.out.println("Executing premain function!");
    instrumentation.addTransformer(new TransformClassFile());
  }
}
