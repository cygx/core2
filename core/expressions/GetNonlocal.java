package core.expressions;
import core.*;

public class GetNonlocal implements Expression {
    public final int index;

    public GetNonlocal(int index) {
        this.index = index;
    }

    public Value eval(Frame frame) {
        return frame.nonlocals[index];
    }
}
