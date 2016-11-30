package core.primitives;
import core.*;
import static core.Symbols.*;

public abstract class I64 implements Value {
    public static final Callable add = (w, a) ->
        new ImmI64(((I64)a[0]).unbox() + ((I64)a[1]).unbox());

    public static final Callable sub = (w, a) ->
        new ImmI64(((I64)a[0]).unbox() - ((I64)a[1]).unbox());

    public static final Callable mul = (w, a) ->
        new ImmI64(((I64)a[0]).unbox() * ((I64)a[1]).unbox());

    public static final Callable div = (w, a) ->
        new ImmI64(((I64)a[0]).unbox() / ((I64)a[1]).unbox());

    public static final Callable mod = (w, a) ->
        new ImmI64(((I64)a[0]).unbox() % ((I64)a[1]).unbox());

    public static final Callable eq = (w, a) ->
        ((I64)a[0]).unbox() == ((I64)a[1]).unbox() ? TRUE : FALSE;

    public static final Callable lt = (w, a) ->
        ((I64)a[0]).unbox() < ((I64)a[1]).unbox() ? TRUE : FALSE;

    public static final Callable le = (w, a) ->
        ((I64)a[0]).unbox() <= ((I64)a[1]).unbox() ? TRUE : FALSE;

    public static final Callable gt = (w, a) ->
        ((I64)a[0]).unbox() > ((I64)a[1]).unbox() ? TRUE : FALSE;

    public static final Callable ge = (w, a) ->
        ((I64)a[0]).unbox() >= ((I64)a[1]).unbox() ? TRUE : FALSE;

    public abstract long unbox();

    public static long unbox(Object obj) {
        return ((I64)obj).unbox();
    }

    @Override
    public String toString() {
        return Long.toString(unbox());
    }

    @Override
    public String asm(World world) {
        return world.getTypeName(this) + '<' + toString() + '>';
    }
}
