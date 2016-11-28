package core;

public class CallINode implements INode {
    public static final Symbol type = new Symbol();

    private final Callable callee;
    private final INode[] args;

    public CallINode(Callable callee, INode... args) {
        this.callee = callee;
        this.args = args;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        Value[] values = new Value[args.length];
        for(int i = 0; i < args.length; ++i)
            values[i] = args[i].eval(frame);

        return callee.call(frame.world, values);
    }
}
