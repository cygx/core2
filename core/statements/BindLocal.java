package core.statements;
import core.*;

public class BindLocal implements Statement {
    public final int index;

    Expression expression;

    public BindLocal(int index, Expression expression) {
        this.index = index;
        this.expression = expression;
    }

    public int eval(Frame frame) {
        frame.locals[index] = expression.eval(frame);
        return 1;
    }

    @Override
    public String asm(World world) {
        return "bind-local " + index + ", " + expression.asm(world);
    }
}
