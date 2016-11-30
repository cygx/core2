import core.*;
import core.primitives.*;
import java.io.*;
import static core.Expressions.*;
import static core.Statements.*;
import static core.Symbols.*;

class test {
    public static void main(String[] args) throws Exception {
        fn = new Function(0).body(
            unless(constant(false), ret(constant(1.0))),
            when(constant(true), ret(constant(2.0))),
            when(constant(true), ret(constant(3.0)))
        );

        System.out.println(fn.bind().call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(0, ImmI64.type).returns(constant(42l));
        System.out.println(fn.bind().call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(1).body(
            bindLocal(0, variable(0l)),
            sink(call(say, call(MutI64.preinc, getLocal(0)))),
            when(call(I64.lt, call(MutI64.imm, getLocal(0)), constant(5l)),
                jump(-1))
        );

        fn.bind().call(null);
        System.out.println(fn.asm(world));
        System.out.println();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(ImmI64.type);
        os.close();

        ObjectInputStream is = new ObjectInputStream(
            new ByteArrayInputStream(bs.toByteArray()));

        Symbol sym = (Symbol)is.readObject();
        is.close();

        System.out.println(ImmI64.type == sym);
    }
}
