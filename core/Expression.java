package core;

@FunctionalInterface
public interface Expression extends Value {
    Symbol type = new Symbol();
    static class Dummy {
        static { Expression.type.resolver(() -> Expression.type); }
    }

    default Symbol type() {
        return type;
    }

    Value eval(Frame frame);
}
