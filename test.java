import core.*;
import core.primitives.*;
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
        world.register("f64", F64.type);
        world.register("i64", I64.type);
        world.register("i64.lt", I64.lessThan);
        world.register("f64.add", F64.add);
        world.register("@f64", MutableF64.type);
        world.register("@i64", MutableI64.type);
        world.register("@i64.imm", MutableI64.immutable);
        world.register("@i64.preinc", MutableI64.preinc);
        world.register("@i64.zero", MutableI64.zero);
        System.out.println(world.gist(world));
        System.out.println();

        Callable say = (w, a) -> {
            System.out.println(a[0].gist(world));
            return VOID;
        };

        world.register("say", say);

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
    }
}
