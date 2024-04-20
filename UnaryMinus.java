package expression;

import expression.interfaces.BaseExpression;

public class UnaryMinus extends UnaryExpression {
    public UnaryMinus(BaseExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSign() {
        return "-";
    }

    @Override
    protected int getPriority() {
        return 3;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    protected boolean isCompatibleWithSamePriority() {
        return true;
    }

    @Override
    protected double makeOperation(double operand) {
        return -1 * operand;
    }

    @Override
    protected int makeIntegerOperation(int operand) {
        return -1 * operand;
    }
}
