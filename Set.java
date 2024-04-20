package expression;

import expression.interfaces.BaseExpression;

public class Set extends BinaryExpression {
    public Set(BaseExpression left, BaseExpression right) {
        super(left, right);
    }

    @Override
    protected double makeOperation(double leftSide, double rightSide) {
        return (int) leftSide | (1 << (int) rightSide);
    }

    @Override
    protected int makeIntegerOperation(int leftSide, int rightSide) {
        return leftSide | (1 << rightSide);
    }

    @Override
    protected String getOperationSign() {
        return "set";
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    protected boolean isCompatibleWithSamePriority() {
        return false;
    }
}
