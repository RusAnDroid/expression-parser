package expression.parser;

import expression.*;
import expression.interfaces.BaseExpression;
import expression.interfaces.TripleExpression;
import expression.interfaces.TripleParser;

public final class ExpressionParser extends BaseParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) {
        source = new StringSource(expression);
        final BaseExpression result = parseExpression();

        if (eof()) {
            return result;
        }

        throw error("End of expression expected");
    }

    private BaseExpression parseExpression() {
        skipWhitespace();
        BaseExpression result = parseSetClear();
        skipWhitespace();
        return result;
    }

    private BaseExpression parseMulDiv() {
        skipWhitespace();
        BaseExpression leftSide = parseElement();
        skipWhitespace();
        while (test('*') || test('/')) {
            if (take('*')) {
                leftSide = new Multiply(leftSide, parseElement());
            } else if (take('/')) {
                leftSide = new Divide(leftSide, parseElement());
            }
            skipWhitespace();
        }
        return leftSide;
    }


    private BaseExpression parseAddSub() {
        skipWhitespace();
        BaseExpression leftSide = parseMulDiv();
        skipWhitespace();
        while (test('+') || test('-')) {
            if (take('+')) {
                leftSide = new Add(leftSide, parseMulDiv());
            } else if (take('-')) {
                leftSide = new Subtract(leftSide, parseMulDiv());
            }
            skipWhitespace();
        }
        return leftSide;
    }

    private BaseExpression parseSetClear() {
        skipWhitespace();
        BaseExpression result = parseAddSub();
        skipWhitespace();
        while (test('s') || test('c')) {
            if (take('s') && expect("et") && (test('-') || test('(') || isWhitespace())) {
                result = new Set(result, parseAddSub()); // :NOTE: а что делать с командами на s?
            } else if (take('c') && expect("lear")
                    && (test('-') || test('(') || isWhitespace())) {
                result = new Clear(result, parseAddSub());
            } else {
                throw error("Unexpected symbol after command: " + ch);
            }
            skipWhitespace();
        }
        return result;
    }

    private BaseExpression parseElement() {
        skipWhitespace();
        //take();
        if (test('(')) {
            return parseBrackets();
        } else if (between('x', 'z')) {
            return new Variable("" + take());
        } else if (test('-')) {
            take('-');
            if (between('0', '9')) {
                return parseConst(true);
            } else {
                return new UnaryMinus(parseElement());
            }
        } else if (between('0', '9')) {
            return parseConst(false);
        } else if (take('c')) {
            expect("ount");
            if (test('-') || test('(') || isWhitespace()) {
                return new Count(parseElement());
            } else {
                throw error("Unexpected symbol after command: " + ch);
            }
        } else {
            throw error("Unexpected start of expression: '" + ch + "'");
        }
    }

    private int takeInteger(boolean isNegative) {
        final StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        while (between('0', '9')) {
            sb.append(take());
        }
        return Integer.parseInt(sb.toString());
    }

    private BaseExpression parseConst(boolean isNegative) {
        return new Const(takeInteger(isNegative));
    }

    private BaseExpression parseBrackets() {
        take('(');
        BaseExpression expr = parseExpression();
        skipWhitespace();
        take(')');
        skipWhitespace();
        return expr;
    }

    private boolean isWhitespace() {
        return !Character.isLetter(ch) && !Character.isDigit(ch)
                && !test('-') && !test('*') && !test('/') && !test('+')
                && !test('(') && !test(')');
    }

    private void skipWhitespace() {
       while (isWhitespace()) {
           take();
           if (eof()) {
               break;
           }
       }
    }
}