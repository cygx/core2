package core;

public class LocalINode implements INode {
    public static final Symbol type = new Symbol();

    public final int index;

    public LocalINode(int index) {
        this.index = index;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        return frame.locals[index];
    }
}
