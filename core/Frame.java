package core;

public class Frame implements Value {
    public static final Symbol type = new Symbol();

    public final World world;
    public final Value[] locals;
    public final Value[] nonlocals;
    private Block block;

    public Frame(World world, Value[] locals, Value[] nonlocals) {
        this.world = world;
        this.locals = locals;
        this.nonlocals = nonlocals;
    }

    public Frame(World world, int size, Value... nonlocals) {
        this(world, new Value[size], nonlocals);
    }

    public Frame(World world) {
        this(world, null, null);
    }

    public Frame set(Value... values) {
        return set(0, values);
    }

    public Frame set(int offset, Value... values) {
        for(Value value : values)
            locals[offset++] = value;

        return this;
    }

    public Block block() {
        return block;
    }

    public Block enterBlock() {
        return block = new Block(block);
    }

    public void leaveBlock() {
        block = block.parent;
    }

    public Symbol type() {
        return type;
    }
}
