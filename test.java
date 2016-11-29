import core.*;
import static core.Symbols.*;

class test {
    public static void main(String[] args) throws Exception {
        Callable say = (w, a) -> {
            System.out.println(a[0].gist());
            return null;
        };

        Callable fadd64 = (w, a) -> new DoubleValue(
            ((DoubleValue)a[0]).value + ((DoubleValue)a[1]).value);

        Expression one = new Constant(new DoubleValue(1.0));
        Expression two = new Constant(new DoubleValue(2.0));
        Expression three = new Constant(new DoubleValue(3.0));

        Function fn = new Function(0, DoubleValue.type).body(
            new Return(new Call(fadd64, new GetNonLocal(0), three))
        );

        System.out.println(fn.bind(new DoubleValue(0.14)).call(null).gist());

        fn = new Function(0).body(
            new If(new Constant(null), new Return(one)),
            new If(new Constant(TRUE), new Return(two)),
            new If(new Constant(TRUE), new Return(three))
        );

        System.out.println(fn.bind().call(null).gist());
    }
}
