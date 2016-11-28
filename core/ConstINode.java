package core;

public class ConstINode implements INode {
    public static final Symbol type = new Symbol();

    public final Value value;

    public ConstINode(Value value) {
        this.value = value;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        return value;
    }
}
