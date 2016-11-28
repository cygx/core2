package core;

public class ProtoFunction implements Value {
    public static final Symbol type = new Symbol();
    private static final Symbol[] NO_PARAMETERS = new Symbol[0];

    private INode body;
    private int frameSize;
    private Symbol returnType;
    private Symbol[] parameters = NO_PARAMETERS;

    public ProtoFunction(INode body, int frameSize) {
        this.body = body;
        this.frameSize = frameSize;
    }

    public ProtoFunction signature(Symbol returnType, Symbol... parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
        return this;
    }

    public Function bind(Value... nonlocals) {
        ProtoFrame protoFrame = new ProtoFrame(frameSize, nonlocals);
        return new Function(body, protoFrame, returnType, parameters);
    }

    public Symbol type() {
        return type;
    }
}
