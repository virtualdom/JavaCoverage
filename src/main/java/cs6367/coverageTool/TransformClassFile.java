package cs6367.coverageTool;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class TransformClassFile implements ClassFileTransformer {
  String outputFilepath;
  String classPrefix;
  public TransformClassFile (String outputFilepath, String classPrefix) {
    this.outputFilepath = outputFilepath;
    this.classPrefix = classPrefix;
  }

  public byte[] transform(ClassLoader loader, String className,
      Class classBeingRedefined, ProtectionDomain protectionDomain,
      byte[] classfileBuffer) throws IllegalClassFormatException {
    byte[] byteCode = classfileBuffer;

    if (className.startsWith(classPrefix)) {
        ClassReader reader = new ClassReader(byteCode);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassTransformVisitor visitor = new ClassTransformVisitor(writer, outputFilepath);
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

    return null;
  }

  // // this is the professor's original code
  // public static void main(final String args[]) throws Exception {
  //  FileInputStream is = new FileInputStream(args[0]);

  //  ClassReader cr = new ClassReader(is);
  //  ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
  //  ClassTransformVisitor ca = new ClassTransformVisitor(cw);
  //  cr.accept(ca, 0);
  //  FileOutputStream fos = new FileOutputStream(args[1]);
  //  fos.write(cw.toByteArray());
  //  fos.close();
  // }
}
