package core.expressions;
import core.*;

public class Constant implements Expression {
    public final Value value;

    public Constant(Value value) {
        this.value = value;
    }

    public Value eval(Frame frame) {
        return value;
    }
}
