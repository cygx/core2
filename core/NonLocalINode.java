package core;

public class NonLocalINode implements INode {
    public static final Symbol type = new Symbol();

    public final int index;

    public NonLocalINode(int index) {
        this.index = index;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        return frame.nonlocals[index];
    }
}
