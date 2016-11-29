package core;
import java.io.Serializable;

@FunctionalInterface
public interface SymbolResolver extends Serializable {
    Symbol resolve();
}
