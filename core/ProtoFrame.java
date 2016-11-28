package core;

public class ProtoFrame implements Value {
    public static final Symbol type = new Symbol();

    private final int size;
    private final Value[] nonlocals;

    public ProtoFrame(int size, Value... nonlocals) {
        this.size = size;
        this.nonlocals = nonlocals.length > 0 ? nonlocals : null;
    }

    public Frame spawn(World world) {
        return new Frame(world, size, nonlocals);
    }

    public Frame spawn(World world, Value... args) {
        Value[] locals = new Value[size];
        for(int i = 0; i < args.length; ++i)
            locals[i] = args[i];

        return new Frame(world, locals, nonlocals);
    }

    public Symbol type() {
        return type;
    }
}
