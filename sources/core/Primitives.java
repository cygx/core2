package core;
import core.primitives.*;

public interface Primitives {
    static class primitives {
        public static void registerWith(World world) {
            world.register("i64", ImmI64.type);
            world.register("f64", ImmF64.type);
            world.register("@i64", MutI64.type);
            world.register("@f64", MutF64.type);
        }
    }
}
