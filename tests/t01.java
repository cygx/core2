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
        world.register("say", (w, a) -> {
            System.out.println(a[0].gist(world));
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
}
