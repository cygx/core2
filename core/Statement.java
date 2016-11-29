package core;

@FunctionalInterface
public interface Statement extends Value {
    final Symbol type = new Symbol();

    default Symbol type() {
        return type;
    }

    int eval(Frame frame);
}
