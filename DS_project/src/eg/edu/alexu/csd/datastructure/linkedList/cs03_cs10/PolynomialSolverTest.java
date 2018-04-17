package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;



import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

/**
 * @author ahmed molahez
 *
 */
public class PolynomialSolverTest {
    /**
     * @serialField
     *
     */
    PolynomialSolver poly = new PolynomialSolver();
    /**
     * @serialField
     *
     */
    final int[][] x = new int[][] {{-120, 5 }, {-1, 3 }, {27, 2 }, {1, 1 },
            {-1, 0 } };
    /**
     * @serialField
     *
     */
    final int[][] y = new int[][] {{3, 7 }, {45, 5 }, {176, 3 },
            {128, 1 } };
    /**
     * @serialField
     *
     */
    final int[][] yy = new int[][] {{3, 7 }, {-75, 5 }, {175, 3 },
            {27, 2 }, {129, 1 }, {-1, 0 } };
            /**
             * @serialField
             *
             */
     final int[][] zz = new int[][] {{10, 10 }, {9, 9 }, {8, 8 }, {7, 7 },
                {6, 6 }, {5, 5 }, {4, 4 }, {3, 3 }, {2, 2 },
                {1, 1 }, {10, 0 } };
                /**
                 * @serialField
                 *
                 */
    final int[][] res = new int[][] {{100, 20 }, {180, 19 }, {241, 18 },
                    {284, 17 }, {310, 16 }, {320, 15 }, {315, 14 }, {296, 13 },
                    {264, 12 }, {220, 11 }, {365, 10 }, {300, 9 }, {244, 8 },
                    {196, 7 }, {155, 6 }, {120, 5 }, {90, 4 }, {64, 3 },
                    {41, 2 }, {20, 1 }, {100, 0 } };
                    /**
                     * @serialField
                     *
                     */
   final int[][] res1 = new int[][] {{20, 10 }, {18, 9 }, {16, 8 },
                        {14, 7 }, {12, 6 }, {10, 5 }, {8, 4 }, {6, 3 }, {4, 2 },
                        {2, 1 }, {20, 0 } };
    /**
     * given test.
     */
    @Test
    public void print() {
        IPolynomialSolver o = (IPolynomialSolver) poly;
        /*
         * instance.setPolynomial('B', new int[][]{ {-120,5}, {-1,3}, {27,2},
         * {1,1}, {-1,0} }); instance.setPolynomial('A', new int[][]{ {-120,5},
         * {-1,3}, {27,2}, {1,1}, {-1,0} }); instance.setPolynomial('C', new
         * int[][]{ {-120,5}, {-1,3}, {27,2}, {1,1}, {-1,0} });
         * instance.clearPolynomial('B'); instance.clearPolynomial('A');
         * instance.clearPolynomial('C'); instance.setPolynomial('A', new
         * int[][]{ {-120,5}, {-1,3}, {27,2}, {1,1}, {-1,0} });
         * instance.clearPolynomial('A');
         */
        assertNull("Polynomial A can't be printed", o.print('B'));
    }
    /**
     * given test.
     */
    @Test
    public void evaluate() {
        IPolynomialSolver instance = (IPolynomialSolver) poly;
        try {
            instance.setPolynomial('B', x);
            instance.setPolynomial('C', y);
            instance.evaluatePolynomial('A', 2 + 2 + 1);
            fail("Solver evaluated unseted polynomial");
        } catch (RuntimeException f) {
            final int z = 5;
        }
    }
    /**
     * given test.
     */
    @Test
    public void testSolveAdd() {
        IPolynomialSolver instance = (IPolynomialSolver) poly;
        instance.setPolynomial('C', y);
        instance.setPolynomial('B', x);
        assertNull("Polynomial R is not set yet", instance.print('R'));
        int[][] result1 = instance.add('B', 'C');
        instance.clearPolynomial('R');
        assertNull("Polynomial R must be set after evaluation",
                instance.print('R'));
        int[][] expected = yy;
        assertArrayEquals(expected[0], result1[0]);
        assertArrayEquals(expected[1], result1[1]);
    }
    /**
     * given test.
     */
    @Test
    public void testMultiply() {
        IPolynomialSolver instance = (IPolynomialSolver) poly;
        instance.setPolynomial('A', zz);
        assertNull("Polynomial R is not set yet", instance.print('R'));
        int[][] result1 = instance.multiply('A', 'A');
        assertNotNull("Polynomial R must be set after evaluation",
                instance.print('R'));
        int[][] expected = res;
        assertArrayEquals(expected, result1);
    }
    /**
     * given test.
     */
    @Test
    public void testAdd() {
        IPolynomialSolver instance = (IPolynomialSolver) poly;
        instance.setPolynomial('A', zz);
        assertNull("Polynomial R is not set yet", instance.print('R'));
        int[][] result1 = instance.add('A', 'A');
        assertNotNull("Polynomial R must be set after evaluation",
                instance.print('R'));
        int[][] expected = res1;
        assertArrayEquals(expected, result1);
    }
    /**
     * given test.
     */
    @Test
    public void testSub() {
        IPolynomialSolver instance = (IPolynomialSolver) poly;
        instance.setPolynomial('A', zz);
        assertNull("Polynomial R is not set yet", instance.print('R'));
        int[][] result1 = instance.subtract('A', 'A');
        assertNotNull("Polynomial R must be set after evaluation",
                instance.print('R'));
        int[][] expected = new int[][] {{0, 0 } };
        assertArrayEquals(expected, result1);
    }

}
