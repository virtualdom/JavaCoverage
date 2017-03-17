package cs6367.listener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import java.io.*;

public class TestStartListener extends RunListener {
    public void testStarted(Description description) throws Exception {
      String classname = description.getDisplayName().replace(description.getMethodName() + "(", "");
      classname = classname.substring(0, classname.length() - 1);
      PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("./stmt-cov.txt", true)));
      p.println("[TEST] " + classname + ":" + description.getMethodName());
      p.close();
    }
}
