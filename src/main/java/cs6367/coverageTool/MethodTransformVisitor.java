package cs6367.coverageTool;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {
    int lineNumber = 0;
    String mName;
    String className;
    String outputFilepath;

    public MethodTransformVisitor(final MethodVisitor mv, String methodname, String classname, String outputFilepath) {
        super(ASM5, mv);
        this.mName=methodname;
        this.className = classname;
        this.outputFilepath = outputFilepath;
    }

    // // method coverage collection
    // @Override
    // public void visitCode(){
    //     mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    //     mv.visitLdcInsn("function " + mName + " executed");
    //     mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    //     super.visitCode();
    // }

    // // statement coverage collection but not working well all the time
    @Override
    public void visitLineNumber(int line, Label start) {
        lineNumber = line;
        mv.visitTypeInsn(NEW, "java/io/FileWriter");
        mv.visitInsn(DUP);
        mv.visitLdcInsn(outputFilepath);
        mv.visitInsn(ICONST_1);
        mv.visitMethodInsn(INVOKESPECIAL, "java/io/FileWriter", "<init>", "(Ljava/lang/String;Z)V", false);
        mv.visitVarInsn(ASTORE, 1);
        mv.visitTypeInsn(NEW, "java/io/BufferedWriter");
        mv.visitInsn(DUP);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESPECIAL, "java/io/BufferedWriter", "<init>", "(Ljava/io/Writer;)V", false);
        mv.visitVarInsn(ASTORE, 2);
        mv.visitTypeInsn(NEW, "java/io/PrintWriter");
        mv.visitInsn(DUP);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/Writer;)V", false);
        mv.visitVarInsn(ASTORE, 3);
        mv.visitVarInsn(ALOAD, 3);
        mv.visitLdcInsn(className + ":" + lineNumber + "\n");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintWriter", "append", "(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;", false);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, 3);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintWriter", "close", "()V", false);

        super.visitLineNumber(line, start);
    }

    // @Override
    // public void visitLabel(Label l) {
    //     if (lineNumber != 0) {
    //         mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    //         mv.visitLdcInsn("line " + lineNumber + " executed");
    //         mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    //         super.visitLabel(l);
    //     }
    // }
}
