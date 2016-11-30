package core.primitives;
import core.*;
import java.io.Serializable;

public class MutF64 extends F64 {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    public double value;

    public MutF64(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

    public Symbol type() {
        return type;
    }
}
