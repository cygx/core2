package core;

public class Block implements Value {
    public static final Symbol type = new Symbol();

    public final Block parent;
    public Value value;
    public int offset;
    public BlockAction action;

    public Block(Block parent) {
        this.parent = parent;
    }

    public void jumpBy(int offset) {
        this.action = BlockAction.JUMP;
        this.offset = offset;
    }

    public Symbol type() {
        return type;
    }
}
