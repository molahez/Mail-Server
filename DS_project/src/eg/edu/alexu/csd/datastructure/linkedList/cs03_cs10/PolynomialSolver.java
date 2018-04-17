package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

/**
 * @author ahmed molahez
 *
 */
public class PolynomialSolver implements IPolynomialSolver {
    /**
     * @serialField
     */
    public SLinkedList[] polynomials = {new SLinkedList(), new SLinkedList(),
            new SLinkedList(), new SLinkedList() };
    /**
     * @serialField
     */
    private boolean[] state = new boolean[] {false, false, false, false };

    /**
     * @param poly
     *            7rf
     * @return i
     */
    private static int mapIndex(final char poly) {
        int i = 0;
        switch (poly) {
        case 'A':
            i = 0;
            break;
        case 'B':
            i = 1;
            break;
        case 'C':
            i = 2;
            break;
        case 'R':
            i = 2 + 1;
            break;
        default:
            throw new RuntimeException();
        }
        return i;
    }
    /**
     * @param poly linkedlist.
     * @return res
     */
    private int[][] convertarray(final SLinkedList poly) {
        int sizz = 2;
        int[][] res = new int[poly.size()][sizz];
        for (int i = 0; i < poly.size(); i++) {
            res[i][0] = ((Term) poly.get(i)).coeff;
            res[i][1] = ((Term) poly.get(i)).exp;
        }
        return res;
    }
    /**
     * @param poly linkedlist
     *
     */
    private static void sortPoly(final SLinkedList poly) {
        int n = poly.size();
        Term temp = new Term();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (((Term) poly.get(j - 1)).exp < ((Term) poly.get(j)).exp) {
                    // swap element
                    temp.exp = ((Term) poly.get(j - 1)).exp;
                    temp.coeff = ((Term) poly.get(j - 1)).coeff;
                    ((Term) poly.get(j - 1)).exp = ((Term) poly.get(j)).exp;
                    ((Term) poly.get(j - 1)).coeff = ((Term) poly.get(j)).coeff;
                    ((Term) poly.get(j)).exp = temp.exp;
                    ((Term) poly.get(j)).coeff = temp.coeff;
                }
            }
        }
    }
    /**
     * @param poly linkedlist
     *
     */
    private static void simplifyPoly(final SLinkedList poly) {

        for (int i = 0; i < poly.size() - 1; i++) {
            if (((Term) poly.get(i)).exp == ((Term) poly.get(i + 1)).exp) {
                ((Term) poly.get(i)).coeff += ((Term) poly.get(i + 1)).coeff;
                poly.remove(i + 1);
                i--;
            }
        }
    }
    /**
     * Set polynomial terms (coefficients & exponents).
     *
     * @param poly
     *            name of the polynomial
     * @param terms
     *            array of [coefficients][exponents]
     */
    public void setPolynomial(final char poly, final  int[][] terms) {

        int index = mapIndex(poly);

        clearPolynomial('R');
        clearPolynomial(poly);
        state[index] = true;

        for (int i = 0; i < terms.length; i++) {
            polynomials[index].add(new Term(terms[i][0], terms[i][1]));
        }
        sortPoly(polynomials[index]);
        simplifyPoly(polynomials[index]);
    }

    /**
     * Print the polynomial in ordered human readable representation.
     *
     * @param poly
     *            name of the polynomial
     * @return polynomial in the form like 27x^2+x-1
     */
    public String print(final char poly) {

        String polynomial = new String();
        int index = mapIndex(poly);

        if (polynomials[index].size() == 0) {
            return null;
        }

        for (int i = 0; i < polynomials[index].size(); i++) {
            if (((Term) polynomials[index].get(i)).coeff == 0) {
                continue;
            } else if (((Term) polynomials[index].get(i)).coeff > 0) {
                if (i != 0) {
                    polynomial += "+";
                }
            } else if (((Term) polynomials[index].get(i)).coeff < 0) {
                polynomial += "-";
            }
            if (((Term) polynomials[index].get(i)).coeff != 1
                    && ((Term) polynomials[index].get(i)).coeff != -1) {
                polynomial += Integer.toString(
                        Math.abs(((Term) polynomials[index].get(i)).coeff));
            }
            if (((Term) polynomials[index].get(i)).exp == 0) {
                if (((Term) polynomials[index].get(i)).coeff == 1
                        || ((Term) polynomials[index].get(i)).coeff == -1) {
                    polynomial += "1";
                } else {
                    continue;
                }
            } else {
                polynomial += "x";
                if (((Term) polynomials[index].get(i)).exp != 1) {
                    polynomial += "^" + Integer
                            .toString(((Term) polynomials[index].get(i)).exp);
                }
            }
        }
        return polynomial;
    }

    /**
     * Clear the polynomial.
     *
     * @param poly
     *            name of the polynomial
     */
    public void clearPolynomial(final char poly) {

        int index = mapIndex(poly);
        state[index] = false;
        polynomials[index].clear();

    }

    /**
     * Evaluate the polynomial.
     *
     * @param poly
     *            name of the polynomial
     * @param value thepolynomial constant value
     * @return the value of the polynomial
     */
    public float evaluatePolynomial(final char poly, final float value) {

        int index = mapIndex(poly);
        if (!state[index]) {
            throw new RuntimeException();
        }
        float polyValue = 0;

        for (int i = 0; i < polynomials[index].size(); i++) {
            if (value == 0 && ((Term) polynomials[index].get(i)).exp < 0) {
                throw new RuntimeException();
            }
            polyValue += ((Term) polynomials[index].get(i)).coeff
                    * Math.pow(value, ((Term) polynomials[index].get(i)).exp);
        }

        return polyValue;
    }

    /**
     * Add two polynomials.
     *
     * @param poly1
     *            first polynomial
     * @param poly2
     *            second polynomial
     * @return the result polynomial
     */
    public int[][] add(final char poly1, final char poly2) {

        int index1 = mapIndex(poly1);
        if (!state[index1]) {
            throw new RuntimeException();
        }
        int index2 = mapIndex(poly2);
        if (!state[index2]) {
            throw new RuntimeException();
        }
        clearPolynomial('R');

        for (int i = 0; i < polynomials[index1].size(); i++) {
            polynomials[2 + 1]
                    .add(new Term(((Term) polynomials[index1].get(i)).coeff,
                            ((Term) polynomials[index1].get(i)).exp));
        }

        for (int i = 0; i < polynomials[index2].size(); i++) {
            polynomials[2 + 1]
                    .add(new Term(((Term) polynomials[index2].get(i)).coeff,
                            ((Term) polynomials[index2].get(i)).exp));

        }

        sortPoly(polynomials[2 + 1]);
        simplifyPoly(polynomials[2 + 1]);
        state[2 + 1] = true;
        return convertarray(polynomials[2 + 1]);

    }

    /**
     * Subtract two polynomials.
     *
     * @param poly1
     *            first polynomial
     * @param poly2
     *            second polynomial
     * @return the result polynomial
     */
    public int[][] subtract(final char poly1, final char poly2) {
        int index1 = mapIndex(poly1);
        if (!state[index1]) {
            throw new RuntimeException();
        }
        int index2 = mapIndex(poly2);
        if (!state[index2]) {
            throw new RuntimeException();
        }
        clearPolynomial('R');
        for (int i = 0; i < polynomials[index1].size(); i++) {
            polynomials[2 + 1]
                    .add(new Term(((Term) polynomials[index1].get(i)).coeff,
                            ((Term) polynomials[index1].get(i)).exp));
        }

        for (int i = 0; i < polynomials[index2].size(); i++) {
            polynomials[2 + 1].add(
                    new Term(((Term) polynomials[index2].get(i)).coeff * -1,
                            ((Term) polynomials[index2].get(i)).exp));

        }

        sortPoly(polynomials[2 + 1]);
        simplifyPoly(polynomials[2 + 1]);
        for (int i = 0; i < polynomials[2 + 1].size(); i++) {

            if (((Term) polynomials[2 + 1].get(i)).coeff == 0) {
                polynomials[2 + 1].remove(i);
                i--;
            }
        }

        if (polynomials[2 + 1].isEmpty()) {
            polynomials[2 + 1].add(new Term(0, 0));
        }
        state[2 + 1] = true;
        return convertarray(polynomials[2 + 1]);

    }

    /**
     * Multiply two polynomials.
     *
     * @param poly1
     *            first polynomial
     * @param poly2
     *            second polynomial
     * @return the result polynomial
     */
    public int[][] multiply(final char poly1, final char poly2) {
        int index1 = mapIndex(poly1);
        if (!state[index1]) {
            throw new RuntimeException();
        }
        int index2 = mapIndex(poly2);
        if (!state[index2]) {
            throw new RuntimeException();
        }
        int temp;
        int temp1;

        clearPolynomial('R');

        for (int i = 0; i < polynomials[index1].size(); i++) {
            for (int j = 0; j < polynomials[index2].size(); j++) {
                temp = ((Term) polynomials[index1].get(i)).coeff
                        * ((Term) polynomials[index2].get(j)).coeff;
                temp1 = ((Term) polynomials[index1].get(i)).exp
                        + ((Term) polynomials[index2].get(j)).exp;
                polynomials[2 + 1].add(new Term(temp, temp1));

            }
            temp = 0;
            temp1 = 0;
        }
        sortPoly(polynomials[2 + 1]);
        simplifyPoly(polynomials[2 + 1]);
        return convertarray(polynomials[2 + 1]);

    }
}
