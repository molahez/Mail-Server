package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

/**
 * @author ahmed molahez
 *
 */
public class Term {
    /**
     * @serialField
     */
     int coeff = 0;
    /**
     * @serialField
     */
     int exp = 0;
    /**
     * @serialField
     */
     int ss = 0;

    /**
    *
    * @throws IOException
    */
    public Term() {
        coeff = 0;
        exp = 0;

    }
    /**
    *
    * @param c dc
    * @param e cd
    */
    public Term(final int c, final int e) {
        coeff = c;
        exp = e;
    }
    /**
    *
    * @param c dc
    * @param e cd
    * @param s llk
    */
    public Term(final int c, final int e, final int s) {
        coeff = c;
        exp = e;
        ss = s;
    }

}
