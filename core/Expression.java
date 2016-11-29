package core;

@FunctionalInterface
public interface Expression extends Value {
    final Symbol type = new Symbol();

    default Symbol type() {
        return type;
    }

    Value eval(Frame frame);
}
