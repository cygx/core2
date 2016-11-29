package core.statements;
import core.*;

public class Jump implements Statement {
    public static final Symbol type = new Symbol();

    private int offset;

    public Jump(int offset) {
        this.offset = offset;
    }

    public Symbol type() {
        return type;
    }

    public int eval(Frame frame) {
        return offset;
    }

    @Override
    public String asm(World world) {
        return "jump " + offset;
    }
}
