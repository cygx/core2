package core;

public class GetNonLocal implements Expression {
    public final int index;

    public GetNonLocal(int index) {
        this.index = index;
    }

    public Value eval(Frame frame) {
        return frame.nonlocals[index];
    }
}
