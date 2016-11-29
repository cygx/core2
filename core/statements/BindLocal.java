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
}
