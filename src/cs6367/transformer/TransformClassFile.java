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

		// OUR CODE MUST GO IN HERE.
		// IT MUST TRANSFORM byteCode DEPENDING ON
		// HOW WE WANT TO GATHER COVERAGE INFORMATION.
		// USE ASM TO DO THIS, NOT JAVASSIST

		return byteCode;
	}

	// // this is the professor's original code
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
