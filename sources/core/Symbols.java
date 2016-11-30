package core;
import java.io.Serializable;

public interface Symbols {
    Symbol VOID = new Symbol();
    Symbol TRUE = new Symbol();
    Symbol FALSE = new Symbol();

    static class symbols {
        static {
            VOID.stooge = new Serializable() {
                private Object readResolve() {
                    return VOID;
                }
            };

            TRUE.stooge = new Serializable() {
                private Object readResolve() {
                    return TRUE;
                }
            };

            FALSE.stooge = new Serializable() {
                private Object readResolve() {
                    return FALSE;
                }
            };
        }

        public static void registerWith(World world) {
            world.register("void", VOID);
            world.register("true", TRUE);
            world.register("false", FALSE);
        }
    }
}
