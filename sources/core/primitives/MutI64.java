package core.primitives;
import core.*;
import java.io.Serializable;

public class MutI64 extends I64 {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    public static final Callable preinc = (w, a) -> {
        MutI64 arg = (MutI64)a[0];
        arg.value += 1;
        return arg;
    };

    public static final Callable postinc = (w, a) -> {
        MutI64 arg = (MutI64)a[0];
        ImmI64 pre = new ImmI64(arg.value);
        arg.value += 1;
        return pre;
    };

    public static final Callable imm = (w, a) -> {
        MutI64 arg = (MutI64)a[0];
        return new ImmI64(arg.value);
    };

    public long value;

    public MutI64(long value) {
        this.value = value;
    }

    public long unbox() {
        return value;
    }

    public Symbol type() {
        return type;
    }

    @Override
    public Value dup() {
        return new MutI64(value);
    }
}
