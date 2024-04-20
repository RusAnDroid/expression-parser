package expression;

import expression.interfaces.BaseExpression;

public class Count extends UnaryExpression {
    public Count(BaseExpression operand) {
        super(operand);
    }

    @Override
    protected double makeOperation(double operand) {
        return Integer.bitCount((int) operand);
    }

    @Override
    protected int makeIntegerOperation(int operand) {
        return Integer.bitCount(operand);
    }

    @Override
    protected String getOperationSign() {
        return "count";
    }

    @Override
    protected int getPriority() {
        return 3;
    }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    protected boolean isCompatibleWithSamePriority() {
        return true;
    }
}
