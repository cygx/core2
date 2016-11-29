package core;

public class Function implements Value {
    public final Symbol type = new Symbol();
    private final Symbol[] NO_PARAMETERS = new Symbol[0];

    final int frameSize;
    final Symbol returnType;
    Expression returnExpression;
    Symbol[] parameters;
    Statement[] statements;

    public Function(int frameSize, Symbol returnType) {
        this.frameSize = frameSize;
        this.returnType = returnType;
        this.parameters = NO_PARAMETERS;
    }

    public Function(int frameSize) {
        this(frameSize, Symbols.VOID);
    }

    public Function signature(Symbol... parameters) {
        this.parameters = parameters;
        return this;
    }

    public Function returns(Expression returnExpression) {
        this.returnExpression = returnExpression;
        return this;
    }

    public Function body(Statement... statements) {
        this.statements = statements;
        return this;
    }

    public Symbol returnType() {
        return returnType.type();
    }

    public int arity() {
        return parameters.length;
    }

    public Closure bind() {
        return new Closure(this, null);
    }

    public Closure bind(Value... nonlocals) {
        return new Closure(this, nonlocals);
    }

    public Symbol type() {
        return type;
    }
}
