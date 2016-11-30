package core.primitives;
import core.*;
import static core.Symbols.*;
import java.io.Serializable;

public class ImmI64 extends I64 {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    public final long value;

    public ImmI64(long value) {
        this.value = value;
    }

    public long unbox() {
        return value;
    }

    public Symbol type() {
        return type;
    }
}
