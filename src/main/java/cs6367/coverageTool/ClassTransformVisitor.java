package cs6367.coverageTool;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class ClassTransformVisitor extends ClassVisitor implements Opcodes {
    String className;
    String outputFilepath;
    public ClassTransformVisitor(final ClassVisitor cv, String outputFilepath) {
        super(ASM5, cv);
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      className = name;
      super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
            final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodTransformVisitor(mv, name, className, outputFilepath);
    }
}


