package eg.edu.alexu.csd.datastructure.stack.cs03;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

/**
 * @author ahmed molahez
 *
 */
public class Evaluator implements IExpressionEvaluator {
    /**
     * @serialField
     *
     */
    public SLinkedList in = new SLinkedList();
    /**
     * @serialField
     *
     */
    private MyStack stack = new MyStack();
    /**
     * @serialField
     *
     */
    private MyStack stack1 = new MyStack();
    /**
     * @serialField
     *
     */
    private String exp1 = new String();
    /**
     * @serialField
     *
     */
    private StringBuilder infix = new StringBuilder();
    /**
     * @serialField
     *
     */
    private int count, count1, j = 0;
    /**
     * @serialField
     *
     */
    private char[] exp;
    /**
     * @serialField
     *
     */
    private float digit2, var1, var2 = 0;
    /**
     * @serialField
     *
     */
    private final float digit = 10;
    /**
     * @serialField
     *
     */
    public char digi2;
    /**
     * @serialField
     *
     */
    public boolean flag, flag1 = false;

    /**
     * @param poly
     *            xc
     * @return i xc
     */
    private static int mapIndex(final char poly) {
        int i = 0;
        switch (poly) {
        case '+':
            i = 1;
            break;
        case '-':
            i = 1;
            break;
        case '*':
            i = 2;
            break;
        case '/':
            i = 2;
            break;
        case '%':
            i = 2;
            break;
        default:
        }
        return i;
    }

    /**
     * @param poly
     *            operator
     * @param x
     *            var1
     * @param y
     *            var 2
     * @return res
     */
    private float mapIndexx(final char poly, final float x, final float y) {
        float res = 0;
        switch (poly) {
        case '+':
            res = x + y;
            break;
        case '-':
            res = x - y;
            break;
        case '*':
            res = x * y;
            break;
        case '/':
            res = x / y;
            break;
        case '%':
            res = x % y;
            break;
        default:
            throw new RuntimeException();
        }
        return res;
    }

    @Override
    public String infixToPostfix(final String expression) {
        count = 0;
        count1 = 0;
        exp = "".toCharArray();
        infix.delete(0, infix.length());
        exp = expression.toCharArray();
        if (exp.length == 0) {
            throw new RuntimeException();
        } else {

            for (int i = 0; i < exp.length; i++) {
                if (exp[i] == '*' || exp[i] == '/' || exp[i] == '-'
                        || exp[i] == '+' || exp[i] == '%') {
                    count++;
                } else if (exp[i] != ' ' && exp[i] != '(' && exp[i] != ')'
                        && (i == 0 || exp[i - 1] == ' ' || exp[i - 1] == ')'
                                || exp[i - 1] == '(' || exp[i - 1] == '*'
                                || exp[i - 1] == '/' || exp[i - 1] == '-'
                                || exp[i - 1] == '+' || exp[i - 1] == '%')) {

                    count1++;

                }
                if (exp[i] != ' ') {
                    if (exp[i] == ')' || exp[i] == '(' || exp[i] == '+'
                            || exp[i] == '-' || exp[i] == '*' || exp[i] == '/'
                            || exp[i] == '%') {
                        if (exp[i] == ')') {

                            while ((char) stack.peek() != '(') {
                                if ((char) stack.peek() == '+'
                                        || (char) stack.peek() == '-'
                                        || (char) stack.peek() == '*'
                                        || (char) stack.peek() == '/'
                                        || (char) stack.peek() == '%') {
                                    infix.append((char) stack.peek());
                                    infix.append(' ');
                                    stack.pop();
                                }
                            }
                            stack.pop();
                        } else if (stack.isEmpty()
                                || ((mapIndex(exp[i]) == 2)
                                        && (mapIndex((char) stack.peek()) == 1))
                                || (exp[i] == '(')
                                || ((exp[i] == '*' || exp[i] == '+'
                                        || exp[i] == '-' || exp[i] == '/'
                                        || exp[i] == '%')
                                        && (char) stack.peek() == '(')) {

                            stack.push(exp[i]);
                        } else if ((mapIndex(exp[i]) <= mapIndex(
                                (char) stack.peek()))) {
                            infix.append((char) stack.pop());
                            infix.append(' ');

                            while (!stack.isEmpty()
                                    && (char) stack.peek() != '('
                                    && (mapIndex(exp[i]) <= mapIndex(
                                            (char) stack.peek()))) {
                                infix.append((char) stack.pop());
                                infix.append(' ');

                            }
                            stack.push(exp[i]);

                        }

                    } else {
                        if (i != exp.length - 1) {
                            if (exp[i + 1] == ' ' || exp[i + 1] == '%'
                                    || exp[i + 1] == '/' || exp[i + 1] == '+'
                                    || exp[i + 1] == '*' || exp[i + 1] == '-'
                                    || exp[i + 1] == ')') {
                                infix.append(exp[i]);
                                infix.append(' ');

                            } else {

                                infix.append(exp[i]);
                            }
                        } else {
                            infix.append(exp[i]);

                        }

                    }
                }
            }
            while (!stack.isEmpty()) {
                infix.append(' ');
                infix.append((char) stack.pop());

            }

        }
        if ((count1 - count) == 1) {
            count1 = 0;
            count = 0;
            return infix.toString();
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public int evaluate(final String expression) {
        exp1 = expression;
        if (exp1 == "" || exp1 == null) {
            throw new RuntimeException();
        } else {

            for (int i = 0; i < exp1.length(); i++) {
                if (Character.isDigit(exp1.charAt(i))) {
                    flag = true;
                }
            }
            if (flag) {
                for (int i = 0; i < exp1.length(); i++) {
                    if (exp1.charAt(i) != ' ') {
                        if (Character.isDigit(exp1.charAt(i))) {
                            j = i + 1;
                            digit2 = (exp1.charAt(i) - '0');
                            while (j < exp1.length()
                                    && Character.isDigit(exp1.charAt(j))) {
                                i++;
                                digit2 = (digit2 * digit)
                                        + (exp1.charAt(j) - '0');
                                j++;
                            }
                            j = 0;
                            stack1.push(digit2);
                            digit2 = 0;

                        } else {
                            if (stack1.size() < 2) {
                                throw new RuntimeException();
                            } else {
                                var2 = (float) stack1.pop();
                                var1 = (float) stack1.pop();
                                stack1.push(
                                        mapIndexx(exp1.charAt(i), var1, var2));

                            }
                        }
                    }

                }

            } else {
                throw new RuntimeException();
            }
            if (!stack1.isEmpty()) {
                digit2 = (float) stack1.pop();
                return Math.round(digit2);
            } else {
                return 0;
            }

        }
    }

}
