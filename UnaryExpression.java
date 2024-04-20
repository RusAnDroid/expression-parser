package expression;

import expression.interfaces.BaseExpression;

import java.util.Objects;

public abstract class UnaryExpression extends UltimateExpression  {
    private final BaseExpression operand;

    public UnaryExpression(BaseExpression operand) {
        this.operand = operand;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof UnaryExpression that) {
            return this.operand.equals(that.operand)
                    && this.getOperationSign().equals(that.getOperationSign());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getOperationSign());
    }

    protected abstract double makeOperation(double operand);

    protected abstract int makeIntegerOperation(int operand);

    @Override
    public double evaluate(double x) {
        return makeOperation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return makeIntegerOperation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeIntegerOperation(operand.evaluate(x, y, z));
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getOperationSign());
        if (operand instanceof UltimateExpression && this.getPriority() > ((UltimateExpression) operand).getPriority()) {
            sb.append("(");
            sb.append(operand.toMiniString());
            sb.append(")");
        } else {
            sb.append(" ");
            sb.append(operand.toMiniString());
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getOperationSign());
        sb.append("(");
        sb.append(operand.toString());
        sb.append(")");
        return sb.toString();
    }

}
