package expression;

import expression.interfaces.BaseExpression;

public class Subtract extends BinaryExpression {
    public Subtract(BaseExpression left, BaseExpression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double leftSide, double rightSide) {
        return leftSide - rightSide;
    }

    @Override
    protected int makeIntegerOperation(int leftSide, int rightSide) {
        return leftSide - rightSide;
    }

    @Override
    protected String getOperationSign() {
        return "-";
    }

    @Override
    protected int getPriority() {
        return 1;
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
