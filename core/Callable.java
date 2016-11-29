package core;

@FunctionalInterface
public interface Callable extends Value {
    Symbol type = new Symbol();
    static class Dummy {
        static { Callable.type.resolver(() -> Callable.type); }
    }

    Value call(World world, Value... args);

    default Symbol type() {
        return type;
    }
}
