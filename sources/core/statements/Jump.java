package core.statements;
import core.*;

public class Jump implements Statement {
    private int offset;

    public Jump(int offset) {
        this.offset = offset;
    }

    public int eval(Frame frame) {
        return offset;
    }

    @Override
    public String asm(World world) {
        return "jump " + offset;
    }
}
