package core.statements;
import core.*;

public class Ret implements Statement {
    private final Expression expression;

    public Ret(Expression expression) {
        this.expression = expression;
    }

    public int eval(Frame frame) {
        frame.returnValue = expression.eval(frame);
        return Frame.RETURN_OFFSET;
    }

    public String asm(World world) {
        return "ret " + expression.asm(world);
    }
}
