package expression;

import expression.interfaces.BaseExpression;

public abstract class UltimateExpression implements BaseExpression {
    protected abstract String getOperationSign();

    protected abstract int getPriority();

    protected abstract boolean isCommutative();

    protected abstract boolean isCompatibleWithSamePriority();
}
