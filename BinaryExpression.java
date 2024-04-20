package expression;

import expression.interfaces.BaseExpression;

import java.util.Objects;

public abstract class BinaryExpression extends UltimateExpression {
    private final BaseExpression left;
    private final BaseExpression right;

    protected BinaryExpression(BaseExpression left, BaseExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract double makeOperation(double leftSide, double rightSide);

    protected abstract int makeIntegerOperation(int leftSide, int rightSide);

    @Override
    public double evaluate(double x) {
        return makeOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return makeIntegerOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeIntegerOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    private void appendOperation(StringBuilder sb) {
        sb.append(" ");
        sb.append(getOperationSign());
        sb.append(" ");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(left.toString());

        appendOperation(sb);

        sb.append(right.toString());
        sb.append(")");
        return sb.toString();
    }

    private boolean checkLeftBracketsPlacement(BinaryExpression that) {
        return that.getPriority() < this.getPriority();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (left instanceof BinaryExpression && checkLeftBracketsPlacement((BinaryExpression) left)) {
            sb.append("(");
            sb.append(left.toMiniString());
            sb.append(")");
        } else {
            sb.append(left.toMiniString());
        }

        appendOperation(sb);

        if (right instanceof BinaryExpression && checkRightBracketsPlacement((BinaryExpression) right)) {
            sb.append("(");
            sb.append(right.toMiniString());
            sb.append(")");
        } else {
            sb.append(right.toMiniString());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BinaryExpression that) {
            return this.left.equals(that.left)
                    && this.getOperationSign().equals(that.getOperationSign())
                    && this.right.equals(that.right);
        }
        return false;
    }

    protected boolean checkRightBracketsPlacement(BinaryExpression that) {
        return that.getPriority() < this.getPriority()
                || ((!this.isCommutative() || !that.isCompatibleWithSamePriority())
                && this.getPriority() == that.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getOperationSign());
    }
}
