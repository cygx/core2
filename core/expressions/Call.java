package core.expressions;
import core.*;

public class Call implements Expression {
    private final Callable callee;
    private final Expression[] args;

    public Call(Callable callee, Expression... args) {
        this.callee = callee;
        this.args = args;
    }

    public Value eval(Frame frame) {
        Value[] values = new Value[args.length];
        for(int i = 0; i < args.length; ++i)
            values[i] = args[i].eval(frame);

        return callee.call(frame.world, values);
    }
}
