import core.*;
import core.primitives.*;
import static core.Expressions.*;
import static core.Primitives.*;
import static core.Statements.*;
import static core.Symbols.*;
import java.io.*;

class t02 implements Test {
    static final String WORLD_FILE = "tests/t02-world.tmp";

    public static void main(String[] args) {
        new t02().run();
    }

    World world;

    void _00_create_world() {
        world = new World();
        symbols.registerWith(world);
        primitives.registerWith(world);
        world.createSymbol("dummy");
    }

    void _01_dump_world() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(
            new FileOutputStream(WORLD_FILE));
        os.writeObject(world);
        os.close();
    }

    void _02_load_world() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(
            new FileInputStream(WORLD_FILE));
        Object obj = is.readObject();
        is.close();

        isa(obj, World.class);
        World w = (World)obj;

        is(w.get("void"), VOID);
        is(w.get("true"), TRUE);
        is(w.get("false"), FALSE);

        is(w.get("i64"), ImmI64.type);
        is(w.get("f64"), ImmF64.type);
        is(w.get("@i64"), MutI64.type);
        is(w.get("@f64"), MutF64.type);

        Symbol s = w.getSymbol("dummy");
        notnull(s);
    }
}
