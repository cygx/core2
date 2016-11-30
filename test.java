import core.*;
import core.primitives.*;
import java.io.*;
import static core.Expressions.*;
import static core.Statements.*;
import static core.Symbols.*;

class test {
    public static void main(String[] args) throws Exception {
        World world = new World();
        world.register("World", World.type);
        world.register("void", VOID);
        world.register("false", FALSE);
        world.register("true", TRUE);
        world.register("f64", ImmF64.type);
        world.register("i64", ImmI64.type);
        world.register("@f64", MutF64.type);
        world.register("@i64", MutI64.type);
        world.register("i64.lt", ImmI64.lt);
        world.register("f64.add", F64.add);
        world.register("@i64.imm", MutI64.imm);
        world.register("@i64.preinc", MutI64.preinc);
        world.register("@i64.zero", MutI64.zero);
        System.out.println(world.gist(world));
        System.out.println();

        Callable say = (w, a) -> {
            System.out.println(a[0].gist(world));
            return VOID;
        };

        world.register("say", say);

        Function fn = new Function(0, ImmF64.type).body(
            ret(call(F64.add, getNonlocal(0), constant(3.0)))
        );

        System.out.println(fn.bind(new ImmF64(0.14)).call(null).gist(world));
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

        fn = new Function(0, ImmI64.type).returns(constant(42l));
        System.out.println(fn.bind().call(null).gist(world));
        System.out.println(fn.asm(world));
        System.out.println();

        fn = new Function(1).body(
            bindLocal(0, call(MutI64.zero)),
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
