package core.statements;
import core.*;

public class When implements Statement {
    private final Expression condition;
    private final Statement statement;

    public When(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public int eval(Frame frame) {
        return condition.eval(frame) == Symbols.TRUE
            ? statement.eval(frame)
            : 1;
    }

    @Override
    public String asm(World world) {
        return "when " + condition.asm(world) + ":\n  " + statement.asm(world);
    }
}
