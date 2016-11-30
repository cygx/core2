package core;
import java.io.Serializable;

@FunctionalInterface
public interface Callable extends Value {
    Symbol type = new Symbol();
    static class Dummy {
        static {
            type.stooge = new Serializable() {
                private Object readResolve() {
                    return type;
                }
            };
        }
    }

    Value call(World world, Value... args);

    default Symbol type() {
        return type;
    }
}
