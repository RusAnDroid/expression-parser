package expression;

import expression.interfaces.BaseExpression;

import java.util.Objects;

public class Const implements BaseExpression {
    private final double value;
    private final boolean isInteger;

    public Const(double value) {
        this.value = value;
        isInteger = false;
    }

    public Const(int value) {
        this.value = value;
        isInteger = true;
    }

    @Override
    public int evaluate(int x) {
        return (int) Math.round(value);
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) Math.round(value);
    }

    @Override
    public String toString() {
        if (isInteger) {
            return Integer.toString((int) value);
        }
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Const) {
            Const that = (Const) other;
            return this.value == that.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
