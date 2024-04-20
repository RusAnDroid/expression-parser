package expression;

import expression.interfaces.BaseExpression;

public class Main {
    public static void main(String[] args) {
        BaseExpression expr = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"),
                                new Variable("x")
                        ),
                        new Multiply(
                                new Const(2),
                                new Variable("x")
                        )
                ),
                new Const(1)
        );
        System.out.println(expr.evaluate(Double.parseDouble(args[0])));
    }
}
