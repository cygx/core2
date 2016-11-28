package core;

public interface INode extends Value {
    Value eval(Frame frame);
}
