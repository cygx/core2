import core.*;
import core.primitives.*;
import static core.Expressions.*;
import static core.Primitives.*;
import static core.Statements.*;
import static core.Symbols.*;
import java.io.*;

class t03 implements Test {
    public static void main(String[] args) {
        new t03().run();
    }

    void _01_load_foreign_world() throws IOException, ClassNotFoundException {
        require(new File(t02.WORLD_FILE).canRead(),
            "cannot read " + t02.WORLD_FILE);

        new t02()._02_load_world();
    }
}
