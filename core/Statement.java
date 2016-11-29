package core;

@FunctionalInterface
public interface Statement extends Value {
    Symbol type = new Symbol();
    static class Dummy {
        static { Statement.type.resolver(() -> Statement.type); }
    }

    default Symbol type() {
        return type;
    }

    int eval(Frame frame);
}
