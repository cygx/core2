package core;

public class Function implements Callable {
    public final Symbol type = new Symbol();

    private final INode body;
    private final ProtoFrame protoFrame;
    public final Symbol returnType;
    public final Symbol[] parameters;

    public Function(INode body, ProtoFrame protoFrame, Symbol returnType,
            Symbol... parameters) {
        this.body = body;
        this.protoFrame = protoFrame;
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Value call(World world, Value... args) {
        return body.eval(protoFrame.spawn(world, args));
    }

    public Symbol type() {
        return type;
    }
}
