package core.primitives;
import core.*;
import static core.Symbols.*;

public class I64 implements Value {
    public static final Symbol type = new Symbol();

    public static final Callable add = (w, a) ->
        new I64(((I64)a[0]).value + ((I64)a[1]).value);

    public static final Callable lessThan = (w, a) ->
        ((I64)a[0]).value < ((I64)a[1]).value ? TRUE : FALSE;

    public final long value;

    public I64(long value) {
        this.value = value;
    }

    public Symbol type() {
        return type;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
