package core.primitives;
import core.*;
import java.io.Serializable;

public abstract class F64 implements Value {
    public static final Callable add =
        (w, a) -> new ImmF64(((F64)a[0]).unbox() + ((F64)a[1]).unbox());

    public abstract double unbox();

    @Override
    public String toString() {
        return Double.toString(unbox());
    }

    @Override
    public String asm(World world) {
        return world.getTypeName(this) + '<' + toString() + '>';
    }
}
