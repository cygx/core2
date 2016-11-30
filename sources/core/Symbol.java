package core;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public final class Symbol implements Value, Comparable<Symbol> {
    private static final AtomicInteger nextId = new AtomicInteger();

    public static class SerializationException extends ObjectStreamException {
        public SerializationException(String msg) {
            super(msg);
        }
    }

    public final int id;
    public Serializable stooge;

    public Symbol() {
        id = nextId.getAndIncrement();
    }

    public Symbol(Serializable stooge) {
        this();
        this.stooge = stooge;
    }

    public Symbol type() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("@%x", id);
    }

    @Override
    public String gist(World world) {
        return world.getName(this);
    }

    @Override
    public String asm(World world) {
        return '$' + world.getName(this);
    }

    public int compareTo(Symbol sym) {
        return id - sym.id;
    }

    private Object writeReplace() throws ObjectStreamException {
        if(stooge == null)
            throw new SerializationException("no symbol stooge");

        return stooge;
    }
}
