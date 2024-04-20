package expression;

import expression.interfaces.BaseExpression;

public class Add extends BinaryExpression {
    public Add(BaseExpression left, BaseExpression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double leftSide, double rightSide) {
        return leftSide + rightSide;
    }

    @Override
    protected int makeIntegerOperation(int leftSide, int rightSide) {
        return leftSide + rightSide;
    }

    @Override
    protected String getOperationSign() {
        return "+";
    }

    @Override
    protected int getPriority() {
        return 1;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    protected boolean isCompatibleWithSamePriority() {
        return true;
    }
}
