import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public interface Test extends Runnable {
    static class PreconditionException extends RuntimeException {
        public PreconditionException(String msg) {
            super(msg);
        }
    }

    default void is(Object a, Object b)   { assert a == b: a + " != " + b; }
    default void is(int a, int b)         { assert a == b: a + " != " + b; }
    default void is(long a, long b)       { assert a == b: a + " != " + b; }
    default void is(boolean a, boolean b) { assert a == b: a + " != " + b; }
    default void is(float a, float b)     { assert a == b: a + " != " + b; }
    default void is(double a, double b)   { assert a == b: a + " != " + b; }

    default void iseq(Object a, Object b) {
        assert Objects.equals(a, b): a + " !eq " + b;
    }

    default void iseq(Object[] a, Object[] b) {
        assert Arrays.equals(a, b):
            Arrays.toString(a) + " !eq " + Arrays.toString(b);
    }

    default void isa(Object o, Class c) {
        assert c.isInstance(o):
            o.getClass().getSimpleName() + " !isa " + c.getSimpleName();
    }

    default void isnull(Object o) {
        assert o == null: "notnull " + o.getClass().getSimpleName();
    }

    default void notnull(Object o) {
        assert o != null: "isnull";
    }

    default void require(boolean b, String msg) {
        if(!b) throw new PreconditionException(msg);
    }

    default void run() {
        Test.run(this);
    }

    static void run(Test t) {
        run(t, "1".equals(System.getenv("PRINT_STACK_TRACE")));
    }

    static void run(Test t, boolean printStackTrace) {
        Method[] methods = t.getClass().getDeclaredMethods() ;
        Arrays.sort(methods, (a, b) -> a.getName().compareTo(b.getName()));

        int count = 0;
        for(Method m : methods) {
            if(m.getName().startsWith("_"))
                ++count;
        }

        System.out.println("1.." + count);

        final PrintStream out = System.out;
        try {
            System.setOut(new PrintStream(new OutputStream() {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                public void write(int b) throws IOException {
                    if(b != '\n') bos.write(b);
                    else {
                        byte[] bytes = bos.toByteArray();
                        bos.reset();
                        out.write(' ');
                        out.write(bytes);
                        out.println();
                    }
                }
                public void flush() throws IOException {
                    byte[] bytes = bos.toByteArray();
                    bos.reset();
                    out.write(bytes);
                    out.flush();
                }
            }));

            int id = 0;
            boolean skipping = false;
            for(Method m : methods) {
                if(!m.getName().startsWith("_")) continue;
                String ok = "ok " + ++id + " -" + m.getName().replace('_', ' ');
                if(skipping) {
                    out.println(ok + " # SKIP precondition failed");
                    continue;
                }

                try {
                    m.invoke(t);
                    out.println(ok);
                }
                catch(IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                catch(InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    String msg = cause.getMessage();
                    if(msg == null) msg = "?";
                    else msg = msg.replace("\n", "\n  ");
                    StackTraceElement[] trace = cause.getStackTrace();
                    StackTraceElement top = trace[
                        trace[0].getClassName().equals("Test") ? 1 : 0];
                    out.printf(" %s: %s (%s:%d)\n",
                        cause.getClass().getSimpleName(), msg,
                        top.getFileName(), top.getLineNumber());

                    if(printStackTrace) {
                        for(StackTraceElement s : cause.getStackTrace()) {
                            out.println("   at " + s);
                            if(s.getMethodName().startsWith("_")) break;
                        }
                    }

                    out.println("not " + ok);

                    if(cause instanceof PreconditionException)
                        skipping = true;
                }
            }
        }
        finally { System.setOut(out); }
    }
}
