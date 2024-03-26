import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.list.List;

/**
 * JUnit test fixture for {@code List<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class ListTest {

    /**
     * Invokes the appropriate {@code List} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new list
     * @ensures constructorTest = (<>, <>)
     */
    protected abstract List<String> constructorTest();

    /**
     * Invokes the appropriate {@code List} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new list
     * @ensures constructorRef = (<>, <>)
     */
    protected abstract List<String> constructorRef();

    /**
     * Constructs a {@code List<String>} with the entries in {@code args} and
     * length of the left string equal to {@code leftLength}.
     *
     * @param list
     *            the {@code List} to construct
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @updates list
     * @requires list = (<>, <>) and 0 <= leftLength <= args.length
     * @ensures <pre>
     * list = ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    private void createFromArgsHelper(List<String> list, int leftLength,
            String... args) {
        for (String s : args) {
            list.addRightFront(s);
            list.advance();
        }
        list.moveToStart();
        for (int i = 0; i < leftLength; i++) {
            list.advance();
        }
    }

    /**
     * Creates and returns a {@code List<String>} of the implementation under
     * test type with the given entries.
     *
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires 0 <= leftLength <= args.length
     * @ensures <pre>
     * createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    protected final List<String> createFromArgsTest(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorTest();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /**
     * Creates and returns a {@code List<String>} of the reference
     * implementation type with the given entries.
     *
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires 0 <= leftLength <= args.length
     * @ensures <pre>
     * createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    protected final List<String> createFromArgsRef(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorRef();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /*
     * Test cases for constructor, addRightFront, removeRightFront, advance,
     * moveToStart, leftLength, and rightLength.
     */

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        List<String> list1 = this.constructorTest();
        List<String> list2 = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");

        list1.addRightFront("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");

        list1.addRightFront("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "purple", "red");

        list1.addRightFront("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "purple",
                "green", "purple");

        list1.addRightFront("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftEmptyRightOne() {

        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(0);

        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "red", "blue");

        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftNonEmptyRightOne() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "purple");

        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "purple",
                "purple");

        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightOne() {

        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(1, "red");

        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(1, "green", "red", "blue");

        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightOne() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "purple",
                "purple", "red");

        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "green", "purple");

        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);

        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");

        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "purple",
                "purple");

        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "purple",
                "green", "purple");
        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);

        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");

        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "purple");

        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "purple",
                "green", "purple");

        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);

        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");

        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "purple");

        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "purple",
                "green", "purple");

        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    /*
     * Test cases for iterator.
     */

    @Test
    public final void testIteratorEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0);

        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list3, list2);
    }

    @Test
    public final void testIteratorOnlyRight() {

        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0, "red", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "red");

        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorOnlyLeft() {

        List<String> list1 = this.createFromArgsTest(3, "red", "green", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(3, "red", "green", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "green", "red");

        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorLeftAndRight() {

        List<String> list1 = this.createFromArgsTest(2, "purple", "red",
                "green", "blue", "yellow");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(2, "purple", "red", "green",
                "blue", "yellow");
        List<String> list4 = this.createFromArgsRef(0, "yellow", "blue",
                "green", "red", "purple");

        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    /*
     * Test cases for other methods: moveToFinish
     */

    @Test
    public final void testMoveToFinishLeftEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);

        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(3, "green", "red", "blue");

        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(3, "yellow", "purple",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "purple",
                "purple");

        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "yellow", "purple",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "purple",
                "green", "purple");

        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishShowBug() {

        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");

        list1.moveToFinish();

        list1.addRightFront("red");
        assertEquals(list2, list1);
    }



    @Test
    public final void testRetreatLeftOneRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(1, "red", "blue", "yellow",
        "green");
        List<String> list2 = this.createFromArgsRef(0, "red", "blue", "yellow",
        "green");

        list1.retreat();

        assertEquals(list2, list1);
    }


    @Test
    public final void testRetreatLeftOneRightEmpty() {

        List<String> list1 = this.createFromArgsTest(1, "red");
        List<String> list2 = this.createFromArgsRef(0, "red");

        list1.retreat();

        assertEquals(list2, list1);
    }
    @Test
    public final void testRetreatLeftNonEmptyRightOne() {

        List<String> list1 = this.createFromArgsTest(4, "red", "blue", "green",
                "yellow", "purple");
        List<String> list2 = this.createFromArgsRef(3, "red", "blue", "green",
                "yellow", "purple");

        list1.retreat();

        assertEquals(list2, list1);
    }
    @Test
    public final void testRetreatLeftNonEmptyRightNonEmpty() {

        List<String> list1 = this.createFromArgsTest(2, "red", "blue", "green",
                "purple");
        List<String> list2 = this.createFromArgsRef(1, "red", "blue", "green",
                "purple");

        list1.retreat();
        assertEquals(list2, list1);
    }

    @Test
    public final void testRetreatLeftOneRightOne() {

        List<String> list1 = this.createFromArgsTest(1, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "red", "blue");

        list1.retreat();

        assertEquals(list2, list1);
    }
    @Test
    public final void testRetreatLeftNonEmptyRightEmpty() {

        List<String> list1 = this.createFromArgsTest(4, "red", "blue", "green",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "red", "blue", "green",
                "purple");

        list1.retreat();

        assertEquals(list2, list1);
    }


}
