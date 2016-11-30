package core.primitives;
import core.*;
import java.io.Serializable;

public abstract class F64 implements Value {
    public static final Callable add =
        (w, a) -> new ImmF64(((F64)a[0]).value() + ((F64)a[1]).value());

    public abstract double value();

    @Override
    public String toString() {
        return Double.toString(value());
    }

    @Override
    public String asm(World world) {
        return world.getTypeName(this) + '<' + toString() + '>';
    }
}
