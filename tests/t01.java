import core.*;
import core.primitives.*;
import static core.Expressions.*;
import static core.Statements.*;
import static core.Symbols.*;

class t01 implements Test {
    public static void main(String[] args) {
        new t01().run();
    }

    World world;

    void _01_create_world() {
        world = new World();
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
        world.register("print", (w, a) -> {
            System.out.print(a[0]);
            return VOID;
        });
        world.register("println", (w, a) -> {
            System.out.println(a.length > 0 ? a[0] : "");
            return VOID;
        });

        System.out.println(world.gist(world));
    }

    void _02_add_floats_via_closure() {
        Function fn = new Function(0, ImmF64.type).body(
            ret(call(F64.add, getNonlocal(0), constant(1.0)))
        );

        Value rv = fn.bind(new ImmF64(0.5)).call(world);
        isa(rv, ImmF64.class);
        is(((F64)rv).unbox(), 1.5);
    }

    void _03_switch() {
        Function fn = new Function(1).body(
            when(call(I64.eq, getLocal(0), constant(1l)), ret(constant(1.0))),
            when(call(I64.eq, getLocal(0), constant(2l)), ret(constant(2.0))),
            when(call(I64.eq, getLocal(0), constant(3l)), ret(constant(3.0))),
            unless(constant(false), ret(constant(-1.0)))
        );

        Closure cl = fn.bind();
        is(((F64)cl.call(world, new ImmI64(1))).unbox(), 1.0);
        is(((F64)cl.call(world, new ImmI64(2))).unbox(), 2.0);
        is(((F64)cl.call(world, new ImmI64(3))).unbox(), 3.0);
        is(((F64)cl.call(world, new ImmI64(42))).unbox(), -1.0);
    }

    void _04_default_return() {
        Function fn = new Function(0).returns(constant(42l));
        Value rv = fn.bind().call(world);
        isa(rv, ImmI64.class);
        is(I64.unbox(rv), 42l);
    }

    void _05_loop() {
        Callable print = world.getCallable("print");
        Callable println = world.getCallable("println");
        Function fn = new Function(1).body(
            bindLocal(0, variable(0l)),
            sink(call(print, call(MutI64.preinc, getLocal(0)))),
            when(call(I64.lt, call(MutI64.imm, getLocal(0)), constant(5l)),
                jump(-1)),
            sink(call(println)),
            ret(getLocal(0))
        );

        is(I64.unbox(fn.bind().call(world)), 5l);
    }
}
