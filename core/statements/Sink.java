package core.statements;
import core.*;

public class Sink implements Statement {
    private final Expression expression;

    public Sink(Expression expression) {
        this.expression = expression;
    }

    public int eval(Frame frame) {
        expression.eval(frame);
        return 1;
    }

    @Override
    public String asm(World world) {
        return "sink " + expression.asm(world);
    }
}
