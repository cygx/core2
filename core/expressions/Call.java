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

    @Override
    public String asm(World world) {
        StringBuilder b = new StringBuilder(world.name(callee));
        b.append('(');
        if(args.length > 0) {
            b.append(args[0].asm(world));
            for(int i = 1; i < args.length; ++i) {
                b.append(", ");
                b.append(args[i].asm(world));
            }
        }
        b.append(')');
        return b.toString();
    }
}
