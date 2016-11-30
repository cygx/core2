package core;
import java.io.Serializable;

public interface Value extends Serializable {
    Symbol type();

    default boolean is(Symbol type) {
        return type() == type;
    }

    default String gist(World world) {
        return world.getTypeName(this) + '(' + toString() + ')';
    }

    default String asm(World world) {
        throw new RuntimeException(
            "don't know how to disassemble " + getClass().getSimpleName());
    }
}
