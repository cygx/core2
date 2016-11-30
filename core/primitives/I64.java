package core.primitives;
import core.*;
import static core.Symbols.*;

public abstract class I64 implements Value {
    public static final Callable add = (w, a) ->
        new ImmI64(((I64)a[0]).value() + ((I64)a[1]).value());

    public static final Callable sub = (w, a) ->
        new ImmI64(((I64)a[0]).value() - ((I64)a[1]).value());

    public static final Callable mul = (w, a) ->
        new ImmI64(((I64)a[0]).value() * ((I64)a[1]).value());

    public static final Callable div = (w, a) ->
        new ImmI64(((I64)a[0]).value() / ((I64)a[1]).value());

    public static final Callable mod = (w, a) ->
        new ImmI64(((I64)a[0]).value() % ((I64)a[1]).value());

    public static final Callable lt = (w, a) ->
        ((I64)a[0]).value() < ((I64)a[1]).value() ? TRUE : FALSE;

    public static final Callable le = (w, a) ->
        ((I64)a[0]).value() <= ((I64)a[1]).value() ? TRUE : FALSE;

    public static final Callable gt = (w, a) ->
        ((I64)a[0]).value() > ((I64)a[1]).value() ? TRUE : FALSE;

    public static final Callable ge = (w, a) ->
        ((I64)a[0]).value() >= ((I64)a[1]).value() ? TRUE : FALSE;

    public abstract long value();

    @Override
    public String toString() {
        return Long.toString(value());
    }

    @Override
    public String asm(World world) {
        return world.getTypeName(this) + '<' + toString() + '>';
    }
}
