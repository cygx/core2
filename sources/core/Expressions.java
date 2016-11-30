package core;
import core.expressions.*;
import core.primitives.*;

public interface Expressions {
    Expression CONSTANT_FALSE = new Constant(Symbols.FALSE);
    Expression CONSTANT_TRUE = new Constant(Symbols.TRUE);

    Expression[] GET_LOCAL = {
        new GetLocal(0),
        new GetLocal(1),
        new GetLocal(2),
        new GetLocal(3),
    };

    Expression[] GET_NONLOCAL = {
        new GetNonlocal(0),
        new GetNonlocal(1),
        new GetNonlocal(2),
        new GetNonlocal(3),
    };

    static Expression call(Callable c, Expression... a) {
        return new Call(c, a);
    }

    static Expression constant(boolean v) {
        return v ? CONSTANT_TRUE : CONSTANT_FALSE;
    }

    static Expression constant(double v) {
        return new Constant(new ImmF64(v));
    }

    static Expression constant(long v) {
        return new Constant(new ImmI64(v));
    }

    static Expression variable(long v) {
        return new Duplicate(new MutI64(v));
    }

    static Expression getLocal(int i) {
        return i < GET_LOCAL.length ? GET_LOCAL[i] : new GetLocal(i);
    }

    static Expression getNonlocal(int i) {
        return i < GET_NONLOCAL.length ? GET_NONLOCAL[i] : new GetNonlocal(i);
    }
}
