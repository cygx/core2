import core.*;
import core.primitives.*;
import java.io.*;
import static core.Expressions.*;
import static core.Statements.*;
import static core.Symbols.*;

class test {
    public static void main(String[] args) throws Exception {
        World world = new World();
        world.registerSymbol("World", World.type);
        world.registerSymbol("void", VOID);
        world.registerSymbol("false", FALSE);
        world.registerSymbol("true", TRUE);
        world.registerSymbol("f64", F64.type);
        world.registerSymbol("i64", I64.type);
        world.registerSymbol("@f64", MutableF64.type);
        world.registerSymbol("@i64", MutableI64.type);
        world.registerName("i64.lt", I64.lessThan);
        world.registerName("f64.add", F64.add);
        world.registerName("@i64.imm", MutableI64.immutable);
        world.registerName("@i64.preinc", MutableI64.preinc);
        world.registerName("@i64.zero", MutableI64.zero);
        System.out.println(world.gist(world));
        System.out.println();

        Callable say = (w, a) -> {
            System.out.println(a[0].gist(world));
            return VOID;
        };

        world.registerName("say", say);

        Function fn = new Function(0, F64.type).body(
            ret(call(F64.add, getNonlocal(0), constant(3.0)))
        );

        System.out.println(fn.bind(new F64(0.14)).call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(0).body(
            unless(constant(false), ret(constant(1.0))),
            when(constant(true), ret(constant(2.0))),
            when(constant(true), ret(constant(3.0)))
        );

        System.out.println(fn.bind().call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(0, I64.type).returns(constant(42l));
        System.out.println(fn.bind().call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(1).body(
            bindLocal(0, call(MutableI64.zero)),
            sink(call(say, call(MutableI64.preinc, getLocal(0)))),
            when(
                call(I64.lessThan,
                    call(MutableI64.immutable, getLocal(0)),
                    constant(5l)),
                jump(-1))
        );

        fn.bind().call(null);
        System.out.println(fn.asm(world));
        System.out.println();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(I64.type);
        os.close();

        ObjectInputStream is = new ObjectInputStream(
            new ByteArrayInputStream(bs.toByteArray()));

        Symbol sym = (Symbol)is.readObject();
        is.close();

        System.out.println(I64.type == sym);
    }
}
