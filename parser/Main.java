package expression.parser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("-5").toMiniString());
    }
}
