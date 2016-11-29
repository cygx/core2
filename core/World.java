package core;
import java.util.*;

public class World implements Value {
    public static final Symbol type = new Symbol();

    private final Map<Object, Object> registry = new HashMap<>();

    public World() {}

    public void register(String name, Value value) {
        registry.put(name, value);
        registry.put(value, name);
    }

    public String name(Value value) {
        String name = (String)registry.get(value);
        return name != null ? name : "?";
    }

    public String typeName(Value value) {
        String name = (String)registry.get(value.type());
        return name != null ? name : '[' + value.getClass().getName() + ']';
    }

    public Symbol type() {
        return type;
    }
}
