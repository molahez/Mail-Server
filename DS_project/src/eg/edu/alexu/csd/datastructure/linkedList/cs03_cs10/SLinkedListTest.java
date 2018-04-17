package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
/**
 * @author ahmed molhez
 *
 */
public class SLinkedListTest {
    /**
     * An object to test the class on.
     */
    SLinkedList slinkedlist = new SLinkedList();

    /**
     * given test.
     */
    @Test
    public void cs65TestSet() {
        ILinkedList instance = (ILinkedList) slinkedlist;
        final int i = 3;
        instance.add(1);
        instance.add(i);
        instance.add(1);
        instance.add(1);
        instance.remove(0);
        instance.remove(0);
        instance.remove(0);
        instance.remove(0);
        instance.add(1);
        assertEquals(1, instance.get(0));
    }

    /**
     * given test.
     */
    @Test
    public void cs65TestSetError() {
        ILinkedList instance = (ILinkedList) slinkedlist;
        final int i = 4;
        try {
            instance.add(1);
            instance.add(2);
            instance.set(i, 'F');
            fail("You should throw an exception"
                    + " when trying to set in a wrong index");
        } catch (RuntimeException f) {
           final int z = 5;
        }
    }

    /**
     * given test.
     */
    @Test
    public void cs65TestSubList() {
        ILinkedList object = (ILinkedList) slinkedlist;
        final int i = 4;
        object.add(0, 0);
        object.add(1, 1);
        object.add(2, 2);
        object.add(2);
        object.add(2);
        object.add(2);
        object.set(1, 2);
        object.set(i, 2);
        object.remove(1);
        assertEquals(object.get(1), 2);

    }

    /**
     * given test..
     */
    @Test
    public void cs56TestContains() {
        ILinkedList c = (ILinkedList) slinkedlist;
        final int z = 3;
        for (int i = 0; i < z; i++) {
            c.add(i);
        }
        c.add(0, z);
        c.add(z + 1, z + 1);

        assertTrue(c.contains(z + 1));
        assertTrue(c.contains(0));
        assertTrue(c.contains(1));
        assertTrue(c.contains(2));
        assertTrue(c.contains(z));
        assertFalse(c.contains(z + z + z));
        assertFalse(c.contains(z + z + 1));
    }

    /**
     * given test.
     */
    @Test
    public void cs56TestAddRemoveTwoLists5() {
        ILinkedList c = (ILinkedList) slinkedlist;
        final int z = 3;
        for (int i = 0; i < z; i++) {
            c.add(i);
        }
        c.add(0, z);
        c.add(z + 1, z + 1);
        c.set(1, z + z + 1);
        c.set(z + 1, z + z + z);
        ILinkedList d = (ILinkedList) slinkedlist;
        d.add(z);
        d.add(z + z + z);
        d.add(1);
        d.add(2);
        d.add(z + z + z);
        for (int i = 0; i < z + 2; i++) {
            assertEquals(c.get(i), d.get(i));
        }
    }
}
