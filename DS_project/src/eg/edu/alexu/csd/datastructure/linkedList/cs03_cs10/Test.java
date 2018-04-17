package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import java.util.Scanner;


/**
 * @author ahmed molahez
 *
 */
public class Test {
    /**
     * @serialField
     */
    private static int choice, i, j, t, index, index1, index2;
    /**
     * @serialField
     */
    private static char var, var1;
    /**
     * @serialField
     */
    private static int number;
    /**
     * @serialField
     */
    static final int SIZE = 100;
    /**
     * @serialField
     */
    private static float value;
    /**
     * @serialField
     */
    private  static int[][] c = new int[SIZE][SIZE];
    /**
     * @serialField
     */
    private static boolean[] state = new boolean[] {false, false, false,
            false };
    /**
     * @serialField
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * @serialField
     */
    private static PolynomialSolver sss = new PolynomialSolver();
    /**
     *
     * @param poly
     *            d
     * @return i
     */
    private static int mapIndex(final char poly) {
        int ii = 0;
        switch (poly) {
        case 'A':
            ii = 0;
            break;
        case 'B':
            ii = 1;
            break;
        case 'C':
            ii = 2 + 1;
            break;
        case 'R':
            ii = 2;
            break;
        default:
            throw new RuntimeException();
        }
        return ii;
    }
    /**
    *
    *
    *
    *
    */
    private  static void case1() {
        System.out.println("Insert the variable name: A, B or C");
        var = sc.next().charAt(0);
        index = mapIndex(var);
        state[index] = true;
        System.out.println("Insert the number of terms");
        number = sc.nextInt();
        System.out.println("Insert the polynomial terms in the form:");
        System.out.println(
                "(coeff1, exponent1), (coeff2, exponent2), ..");
        for (i = 0; i < number; i++) {
            for (j = 0; j < 2; j++) {
                t = sc.nextInt();
                c[i][j] = t;
            }
        }
        sss.setPolynomial(var, c);
        System.out.println("Polynomial " + var + " is set");
    }
    /**
     * @param  args d
     *
     */
    public static void main(final String[] args) {

        while (choice != 2 + 2 + 2 + 2) {
            System.out.println("Please choose an action");
            System.out.println("-----------------------");
            System.out.println("1- Set a polynomial variable");
            System.out.println("2- Print the value of a polynomial variable");
            System.out.println("3- Add two polynomials");
            System.out.println("4- Subtract two polynomials");
            System.out.println("5- Multiply two polynomials");
            System.out.println("6- Evaluate a polynomial at some point");
            System.out.println("7- Clear a polynomial variable");
            System.out.println("8- Exit");
            choice = sc.nextInt();
            switch (choice) {
            case 1:
              case1();
                break;
            case 2:
                System.out.println("Insert the variable name: A, B, C or R");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                while (!state[index]) {
                    System.out.println("Variable not set");
                    System.out
                            .println("Insert the variable name: A, B, C or R");
                    var = sc.next().charAt(0);
                    index = mapIndex(var);
                }
                System.out.println("Value in " + var + " : " + sss.print(var));
                break;
            case 2 + 1:
                System.out.println(
                        "Insert first operand variable name: A, B or C");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                while (!state[index]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert first operand variable name: A, B or C");
                    var = sc.next().charAt(0);
                    index = mapIndex(var);
                }
                System.out.println(
                        "Insert second operand variable name: A, B or C");
                var1 = sc.next().charAt(0);
                index1 = mapIndex(var1);
                while (!state[index1]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert second operand variable name: A, B or C");
                    var1 = sc.next().charAt(0);
                    index1 = mapIndex(var1);
                }
                sss.setPolynomial('R', sss.add(var, var1));
                index2 = mapIndex('R');
                state[index2] = true;
                System.out.println("Result set in R: " + sss.print('R'));
                break;
            case 2 + 2:
                System.out.println(
                        "Insert first operand variable name: A, B or C");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                while (!state[index]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert first operand variable name: A, B or C");
                    var = sc.next().charAt(0);
                    index = mapIndex(var);
                }
                System.out.println(
                        "Insert second operand variable name: A, B or C");
                var1 = sc.next().charAt(0);
                index1 = mapIndex(var1);
                while (!state[index1]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert second operand variable name: A, B or C");
                    var1 = sc.next().charAt(0);
                    index1 = mapIndex(var1);
                }
                sss.setPolynomial('R', sss.subtract(var, var1));
                index2 = mapIndex('R');
                state[index2] = true;
                System.out.println("Result set in R: " + sss.print('R'));
                break;
            case 2 + 2 + 1:
                System.out.println(
                        "Insert first operand variable name: A, B or C");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                while (!state[index]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert first operand variable name: A, B or C");
                    var = sc.next().charAt(0);
                    index = mapIndex(var);
                }
                System.out.println(
                        "Insert second operand variable name: A, B or C");
                var1 = sc.next().charAt(0);
                index1 = mapIndex(var1);
                while (!state[index1]) {
                    System.out.println("Variable not set");
                    System.out.println(
                            "Insert second operand variable name: A, B or C");
                    var1 = sc.next().charAt(0);
                    index1 = mapIndex(var1);
                }
                sss.setPolynomial('R', sss.multiply(var, var1));
                index2 = mapIndex('R');
                state[index2] = true;
                System.out.println("Result set in R: " + sss.print('R'));
                break;
            case 2 + 2 + 2:
                System.out.println("Insert the variable name: A, B, C or R");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                while (!state[index]) {
                    System.out.println("Variable not set");
                    System.out
                            .println("Insert the variable name: A, B, C or R");
                    var = sc.next().charAt(0);
                    index = mapIndex(var);
                }
                System.out.println("Enter the value for evaluation");
                value = sc.nextFloat();
                System.out.println("Evaluation of " + var + " :"
                        + sss.evaluatePolynomial(var, value));
                break;
            case 2 + 2 + 2 + 1:
                System.out.println("Insert the variable name: A, B, C or R");
                var = sc.next().charAt(0);
                index = mapIndex(var);
                sss.clearPolynomial(var);
                state[index] = false;
                System.out.println(var + " is cleared");
            default:
                throw new RuntimeException();
            }
            if (choice != 2 + 2 + 2 + 2) {
                System.out.println(
                        "============================="
                        + "=======================================");
            }
        }
    }
}
