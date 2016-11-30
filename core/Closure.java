package core;
import java.io.Serializable;

public class Closure implements Callable {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    private final Function fn;
    private final Value[] nonlocals;

    public Closure(Function fn, Value[] nonlocals) {
        this.fn = fn;
        this.nonlocals = nonlocals;
    }

    public Value call(World world, Value... args) {
        Value[] locals = new Value[fn.frameSize];
        for(int i = 0; i < args.length; ++i)
            locals[i] = args[i];

        Frame frame = new Frame(world, locals, nonlocals, fn.statements);

        if(fn.returnExpression != null)
            frame.returnValue = fn.returnExpression.eval(frame);

        if(fn.statements != null)
            frame.run();

        return frame.returnValue;
    }

    public Symbol type() {
        return type;
    }
}
