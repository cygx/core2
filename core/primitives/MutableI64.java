package core.primitives;
import core.*;

public class MutableI64 implements Value {
    public static final Symbol type = new Symbol();

    public static final Callable preinc = (w, a) -> {
        MutableI64 arg = (MutableI64)a[0];
        arg.value += 1;
        return arg;
    };

    public static final Callable postinc = (w, a) -> {
        MutableI64 arg = (MutableI64)a[0];
        I64 pre = new I64(arg.value);
        arg.value += 1;
        return pre;
    };

    public static final Callable zero = (w, a) -> {
        return new MutableI64(0);
    };

    public static final Callable immutable = (w, a) -> {
        MutableI64 arg = (MutableI64)a[0];
        return new I64(arg.value);
    };

    public long value;

    public MutableI64(long value) {
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
