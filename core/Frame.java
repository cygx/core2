package core;
import java.io.Serializable;

public class Frame implements Value, Runnable {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    public static final int RETURN_OFFSET = 1 << 30;

    public final World world;
    public final Value[] locals;
    public final Value[] nonlocals;
    public final Statement[] statements;
    public Value returnValue;

    public Frame(World world, Value[] locals, Value[] nonlocals,
            Statement[] statements) {
        this.world = world;
        this.locals = locals;
        this.nonlocals = nonlocals;
        this.statements = statements;
    }

    public void run() {
        int i = 0;
        do i += statements[i].eval(this);
        while(i < statements.length);
    }

    public Symbol type() {
        return type;
    }
}
