package core;

public class Return implements Statement {
    private final Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }

    public int eval(Frame frame) {
        frame.returnValue = expression.eval(frame);
        return Frame.RETURN_OFFSET;
    }
}
