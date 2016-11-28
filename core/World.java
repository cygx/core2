package core;

public class World implements Value {
    public static final Symbol type = new Symbol();

    public Symbol type() {
        return type;
    }

    public String gist() {
        return"World(@" + Integer.toHexString(hashCode()) + ")";
    }
}
