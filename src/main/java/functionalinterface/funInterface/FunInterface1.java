package functionalinterface.funInterface;

@FunctionalInterface
public interface FunInterface1 {
    public abstract void say();

    default void aaa() {

    }

    static void bbb() {

    }
}
