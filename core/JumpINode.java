package core;

public class JumpINode implements INode {
    public static final Symbol type = new Symbol();

    private int offset;

    public JumpINode(int offset) {
        this.offset = offset;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        frame.block().jumpBy(offset);
        return null;
    }
}
