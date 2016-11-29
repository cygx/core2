import core.*;
import core.primitives.*;
import static core.Expressions.*;
import static core.Statements.*;
import static core.Symbols.*;

class test {
    public static void main(String[] args) throws Exception {
        Callable say = (w, a) -> {
            System.out.println(a[0].gist());
            return VOID;
        };

        Function fn = new Function(0, F64.type).body(
            ret(call(F64.add, getNonlocal(0), constant(3.0)))
        );

        System.out.println(fn.bind(new F64(0.14)).call(null).gist());

        fn = new Function(0).body(
            unless(constant(false), ret(constant(1.0))),
            when(constant(true), ret(constant(2.0))),
            when(constant(true), ret(constant(3.0)))
        );

        System.out.println(fn.bind().call(null).gist());

        fn = new Function(1).body(
            bindLocal(0, call(MutableI64.zero)),
            sink(call(say, call(MutableI64.preinc, getLocal(0)))),
            when(
                call(I64.lessThan,
                    call(MutableI64.immutable, getLocal(0)),
                    constant(5l)),
                jump(-1))
        );

        fn.bind().call(null);
    }
}
