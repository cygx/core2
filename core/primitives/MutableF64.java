package core.primitives;
import core.*;

public class MutableF64 implements Value {
    public static final Symbol type = new Symbol();

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
