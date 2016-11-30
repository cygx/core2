package core.expressions;
import core.*;

public class Duplicate implements Expression {
    public final Value value;

    public Duplicate(Value value) {
        this.value = value;
    }

    public Value eval(Frame frame) {
        return value.dup();
    }

    public String asm(World world) {
        return "dup(" + value.asm(world) + ")";
    }
}
