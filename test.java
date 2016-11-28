import core.*;
import java.util.*;

class test {
    public static void main(String[] args) throws Exception {
        Callable say = (w, a) -> {
            System.out.println(a[0].gist());
            return null;
        };

        Callable fadd64 = (w, a) -> new DoubleValue(
            ((DoubleValue)a[0]).value + ((DoubleValue)a[1]).value);

        Value three = new DoubleValue(3.0);

        INode body = new CallINode(say,
            new CallINode(fadd64,
                new NonLocalINode(0),
                new ConstINode(new DoubleValue(0.14))
            )
        );

        ProtoFunction proto = new ProtoFunction(body, 0);
        Function fn = proto.bind(three);
        fn.call(null);

        new BlockINode(
            new CallINode((w, a) -> {
                try { Thread.sleep(1000); }
                catch(InterruptedException e) {}
                System.out.print(".");
                return null;
            }),
            new JumpINode(-1)
        ).eval(new Frame(null));
    }
}
