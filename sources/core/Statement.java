package core;
import java.io.Serializable;

@FunctionalInterface
public interface Statement extends Value {
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

    int eval(Frame frame);
}
