import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size
    @Test
    public final void testChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        m.changeToExtractionMode();
        assertEquals("false", m.isInInsertionMode());
    }

    //test add kernal method by adding a term to an a non empty SM
    @Test
    public final void addRoutineTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red");
        m.add("red");
        assertEquals(mExpected, m);
    }

    //test add kernal method by adding mulitple term to an a non empty SM
    @Test
    public final void addManyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red","blue","purple");
        m.add("red");
        m.add("blue");
        m.add("purple");
        assertEquals(mExpected, m);
    }

    //test removeFirst kernal method by removing from a non empty SM
    @Test
    public final void removeFirstRoutineTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green","red","purple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,"red","purple");
        String removed = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(removed, "green");
    }
    //test removeFirst kernal method by removing multple from a non empty SM
    @Test
    public final void removeFirstManyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green",
                "red", "purple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red");
        String removed1 = m.removeFirst();
        String removed2 = m.removeFirst();
        assertEquals(removed1, "green");
        assertEquals(removed2, "purple");
        assertEquals(mExpected, m);
    }
    //test removeFirst kernal method by removing fto end up with an empty SM
    @Test
    public final void removeFirstEmptyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String removed = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(removed, "green");
    }
    //test size kernal method with one element in Insertion mode
    @Test
    public final void sizeRoutineOneInsertionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        assertEquals(m.size(), 1);
    }
    //test size kernal method with many elements in insertion mode
    public final void sizeRoutineManyInsertionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green","red","purple");
        assertEquals(m.size(), 3);
    }
    //test size kernal method with one elements in extraction mode
    @Test
    public final void sizeRoutineOneExtractionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        assertEquals(m.size(), 1);
    }
    //test size kernal method with many elements in extraction mode
    @Test
    public final void sizeRoutineManyExtractionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green","red","purple");
        assertEquals(m.size(), 3);
    }
    //test size kernal method with no elements in insertion mode
    @Test
    public final void sizeEmptyInsertionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(m.size(), 0);
    }
    //test size kernal method with no elements in extraction mode
    @Test
    public final void sizeEmptyExtractionModeTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertEquals(m.size(), 0);
    }

    //test isInInsertionMode method with many elements in insertion mode
    @Test
    public final void isInInsertionModeTrueManyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        assertEquals(m.isInInsertionMode(), true);
    }
    //test isInInsertionMode method with many elements in extraction mode
    @Test
    public final void isInInsertionModeFalseManyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue");
        assertTrue(!m.isInInsertionMode());
    }
    //test isInInsertionMode method with one elements in insertion mode
    @Test
    public final void isInInsertionModeTrueOneTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        assertEquals(m.isInInsertionMode(), true);
    }
    //test isInInsertionMode method with one elements in extraction mode
    @Test
    public final void isInInsertionModeFalseOneTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red");
        assertTrue(!m.isInInsertionMode());
    }
    //test isInInsertionMode method with no elements in insertion mode
    @Test
    public final void isInInsertionModeTrueEmptyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(m.isInInsertionMode(), true);
    }
    //test isInInsertionMode method with no elements in extraction mode
    @Test
    public final void isInInsertionModeFalseEmptyTest() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertEquals(m.isInInsertionMode(), false);
    }
}
