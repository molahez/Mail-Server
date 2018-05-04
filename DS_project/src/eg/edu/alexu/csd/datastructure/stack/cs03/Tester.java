package eg.edu.alexu.csd.datastructure.stack.cs03;

import java.util.Scanner;

/**
 * @author ahmed molahez
 *
 */
public class Tester {
    /**
     * @serialField
     */
    public static MyStack st = new MyStack();
    /**
     * @serialField
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * @serialField
     */
    public static int choice;
    /**
     * @serialField
     */
    public static String temp;

    /**
     * @param args
     *            d
     *
     */
    public static void main(final String[] args) {

        while (choice != 2 + 2 + 2) {
            System.out.println("Please choose an action");
            System.out.println("-----------------------");
            System.out.println("1- Push");
            System.out.println("2- Pop");
            System.out.println("3- Peek");
            System.out.println("4- Get size");
            System.out.println("5- check if empty");
            choice = sc.nextInt();
            switch (choice) {
            case 1:
                System.out.println("Enter the object you want to push");
                temp = sc.next();
                st.push(temp);
                break;

            case 2:
                if (st.isEmpty()) {
                    System.out.println(
                            "Stack is already empty invalid operation");
                } else {
                    System.out.println("The elemet removed is " + st.pop());
                }
                break;
            case 2 + 1:
                if (st.isEmpty()) {
                    System.out.println(
                            "Stack is already empty invalid operation");
                } else {
                    System.out.println("The elemet on the top is " + st.peek());
                }
                break;
            case 2 + 2:
                System.out.println("Stack's size is " + st.size());
                break;
            case 2 + 2 + 1:
                if (st.isEmpty()) {
                    System.out.println("Stack is empty");
                } else {
                    System.out.println(
                            "Stack is not empty and it's size is " + st.size());
                }
                break;
                default:
                    throw new RuntimeException();

            }
            /*
             * Evaluator ss = new Evaluator(); String k = "{8+3}";
             * System.out.println(ss.infixToPostfix(k)); //
             * System.out.println(ss.infixToPostfix(k));km //
             * System.out.println(ss.evaluate("2")); //
             * System.out.println(ss.evaluate(ss.infixToPostfix(k))); //
             * System.out.println(ss.evaluate(ss.infixToPostfix(k)));
             */
        }
    }
}
