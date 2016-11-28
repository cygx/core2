package core;

@FunctionalInterface
public interface Callable extends Value {
    static final Symbol type = new Symbol();

    Value call(World world, Value... args);

    default Symbol type() {
        return type;
    }
}
