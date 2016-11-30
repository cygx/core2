package core.primitives;
import core.*;
import java.io.Serializable;

public class MutableF64 implements Value {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    public double value;

    public MutableF64(double value) {
        this.value = value;
    }

    public Symbol type() {
        return type;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
