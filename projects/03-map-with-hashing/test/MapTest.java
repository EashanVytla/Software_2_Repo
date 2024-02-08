import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Allen Thomas and Eashan Vytla
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size


    /**
     * Tests the add method using a regular input
     */
    @Test
    public void addRegularTest() {
        Map<String, String> actual = this.constructorTest();
        Map<String, String> expected = this.constructorRef();
        assertEquals(actual, expected);
    }


    /**
     * Tests add kernal method on the empty map
     */
    //allen
    @Test
    public void addEmptyTest() {

    }
    /**
     * Tests the add kernal method by calling it twice
     */
    //allen
    @Test
    public void addThreeTest() {

    }

    /**
     * Tests remove kernal method on the mpa that will be empty
     */
    //allen
    @Test
    public void removeEmptyMapTest() {

    }

    /**
     * Tests remove kernal method on a singl pair
     */
    //allen
    @Test
    public void removeRegularTest() {

    }

    /**
     * Tests remove kernal method on two pairs
     */
    //allen
    @Test
    public void removeTwoTest() {

    }

    /**
     * Tests removeAny kernal method on a map
     */
    //Eashan
    @Test
    public void removeAnyTest() {


    }

    /**
     * Tests value kernal method on a map pair
     */
    @Test
    //Eashan
    public void valueRegularTest() {

    }

    /**
     * Tests value kernal method on two map pairs
     */
    @Test
    //Eashan
    public void valueTwoTest() {

    }



    /**
     * Tests hasKey kernal method when it is false
     */
    @Test
    //Eashan
    public void hasKeyFalseTest() {

    }

    /**
     * Tests hasKey kernal method when it is true
     */
    @Test
    //Eashan
    public void hasKeyTrueTest() {

    }

    /**
     * Tests size kernal method when the map is empty
     */
    //allen
    @Test
    public void sizeEmptyTest() {

    }

    /**
     * Tests size on a map with a single pair.
     */
    //allen
    @Test
    public void sizeOnePairTest() {

    }

    /**
     * Tests size kernal method when there are mutliple pairs.
     */
    //allen
    @Test
    public void sizeMultiplePairTest() {

    }
}
