package core;
import java.io.*;
import java.util.*;

public class World implements Value {
    public static final Symbol type = new Symbol();
    static {
        type.stooge = new Serializable() {
            private Object readResolve() {
                return type;
            }
        };
    }

    private final Map<String, Symbol> symbols = new HashMap<>();
    private final Map<Object, Object> registry = new HashMap<>();

    public World() {}

    public Symbol type() {
        return type;
    }

    public String getTypeName(Value value) {
        String name = (String)registry.get(value.type());
        return name != null ? name : '[' + value.getClass().getName() + ']';
    }

    public String getName(Value value) {
        String name = (String)registry.get(value);
        return name != null ? name : "???";
    }

    public void register(String name, Value value) {
        registry.put(name, value);
        registry.put(value, name);
    }

    public Symbol createSymbol(String name) {
        Symbol symbol = new Symbol(new Serializable() {
            private Object readResolve() {
                Symbol s = symbols.get(name);
                return s != null ? s : createSymbol(name);
            }
        });
        symbols.put(name, symbol);
        register(name, symbol);
        return symbol;
    }
}
