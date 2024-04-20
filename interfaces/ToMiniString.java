package expression.interfaces;

public interface ToMiniString {
    default String toMiniString() {
        return toString();
    }
}
