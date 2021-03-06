package core.expressions;
import core.*;

public class GetLocal implements Expression {
    public final int index;

    public GetLocal(int index) {
        this.index = index;
    }

    public Value eval(Frame frame) {
        return frame.locals[index];
    }

    @Override
    public String asm(World world) {
        return "get-local(" + index + ")";
    }
}
