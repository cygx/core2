package core;
import core.primitives.*;
import core.statements.*;

public interface Statements {
    static Statement bindLocal(int i, Expression e) {
        return new BindLocal(i, e);
    }

    static Statement jump(int o) {
        return new Jump(o);
    }

    static Statement ret(Expression e) {
        return new Ret(e);
    }

    static Statement sink(Expression e) {
        return new Sink(e);
    }

    static Statement unless(Expression e, Statement s) {
        return new Unless(e, s);
    }

    static Statement when(Expression e, Statement s) {
        return new When(e, s);
    }
}
