package core.primitives;
import core.*;

public class F64 implements Value {
    public static final Symbol type = new Symbol();
    static { type.resolver(() -> type); }

    public static final Callable add =
        (w, a) -> new F64(((F64)a[0]).value + ((F64)a[1]).value);

    public final double value;

    public F64(double value) {
        this.value = value;
    }

    public Symbol type() {
        return type;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public String asm(World world) {
        return world.typeName(this) + '<' + toString() + '>';
    }
}