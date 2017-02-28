package cs6367.transformer;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;

// import org.objectweb.asm.ClassReader;
// import org.objectweb.asm.ClassWriter;

public class TransformClassFile implements ClassFileTransformer {
	public byte[] transform(ClassLoader loader, String className,
			Class classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] byteCode = classfileBuffer;

		if (className.equals("cs6367/test/Lion")) {
			System.out.println("Instrumenting......");
			try {
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
						classfileBuffer));
				CtMethod[] methods = ctClass.getDeclaredMethods();
				for (CtMethod method : methods) {
					method.addLocalVariable("startTime", CtClass.longType);
					method.insertBefore("startTime = System.nanoTime();");
					method.insertAfter("System.out.println(\"Execution Duration "
							+ "(nano sec): \"+ (System.nanoTime() - startTime) );");
				}
				byteCode = ctClass.toBytecode();
				ctClass.detach();
				System.out.println("Instrumentation complete.");
			} catch (Throwable ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
			}
		}
		return byteCode;
	}

	// public static void main(final String args[]) throws Exception {
	// 	FileInputStream is = new FileInputStream(args[0]);

	// 	ClassReader cr = new ClassReader(is);
	// 	ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
	// 	ClassTransformVisitor ca = new ClassTransformVisitor(cw);
	// 	cr.accept(ca, 0);
	// 	FileOutputStream fos = new FileOutputStream(args[1]);
	// 	fos.write(cw.toByteArray());
	// 	fos.close();
	// }
}
