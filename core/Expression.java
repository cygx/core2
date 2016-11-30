package core;
import java.io.Serializable;

@FunctionalInterface
public interface Expression extends Value {
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

    default Symbol type() {
        return type;
    }

    Value eval(Frame frame);
}
