package core;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class Symbol implements Value, Comparable<Symbol> {
    private static final AtomicInteger nextId = new AtomicInteger();

    public final transient int id;

    public Symbol() {
        this.id = nextId.getAndIncrement();
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
        return world.name(this);
    }

    @Override
    public String asm(World world) {
        return '$' + world.name(this);
    }

    public int compareTo(Symbol sym) {
        return id - sym.id;
    }

    private Object readResolve() throws ObjectStreamException {
        return new Symbol();
    }
}
