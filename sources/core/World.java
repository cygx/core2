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

    private final Map<Object, Object> registry = new HashMap<>();
    private transient Map<String, Symbol> deserializationStage;

    public World() {}

    public Symbol type() {
        return type;
    }

    public Value get(String name) {
        return (Value)registry.get(name);
    }

    public Callable getCallable(String name) {
        return (Callable)registry.get(name);
    }

    public Symbol getSymbol(String name) {
        return (Symbol)registry.get(name);
    }

    public String getTypeName(Value value) {
        String name = (String)registry.get(value.type());
        return name != null ? name : '[' + value.getClass().getName() + ']';
    }

    public String getName(Value value) {
        String name = (String)registry.get(value);
        return name != null ? name : "???";
    }

    public void register(String name, Callable callable) {
        register(name, (Value)callable);
    }

    public void register(String name, Value value) {
        if(registry.containsKey(name))
            throw new IllegalStateException("already registered '" + name + "'");

        registry.put(name, value);
        registry.put(value, name);
    }

    private Serializable stooge(String name) {
        return new Serializable() {
            private Object readResolve() {
                Symbol symbol = null;
                if(deserializationStage == null)
                    deserializationStage = new HashMap<>();
                else symbol = deserializationStage.get(name);

                if(symbol == null) {
                    symbol = new Symbol(stooge(name));
                    deserializationStage.put(name, symbol);
                }

                return symbol;
            }
        };
    }

    public Symbol createSymbol(String name) {
        Symbol symbol = new Symbol(stooge(name));
        register(name, symbol);
        return symbol;
    }

    private Object readResolve() {
        deserializationStage = null;
        return this;
    }
}
