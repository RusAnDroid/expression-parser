package expression;

import expression.interfaces.BaseExpression;

public class Multiply extends BinaryExpression {
    public Multiply(BaseExpression left, BaseExpression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double leftSide, double rightSide) {
        return leftSide * rightSide;
    }

    @Override
    protected int makeIntegerOperation(int leftSide, int rightSide) {
        return leftSide * rightSide;
    }

    @Override
    protected String getOperationSign() {
        return "*";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    protected boolean isCompatibleWithSamePriority() {
        return true;
    }
}
