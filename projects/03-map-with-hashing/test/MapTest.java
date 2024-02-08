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
     * Tests the add kernal method using a regular input.
     */
    @Test
    public void addRegularTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2");
        Map<String, String> expected = this.createFromArgsRef("k1", "v1", "k2", "v2", "k3", "v3");
        actual.add("k3", "v3");
        assertEquals(actual, expected);
    }


    /**
     * Tests add kernal method on the empty map
     */
    //allen
    @Test
    public void addEmptyTest() {
        Map<String, String> actual = this.createFromArgsTest();
        Map<String, String> expected = this.createFromArgsRef("k1", "v1");
        actual.add("k1", "v1");
        assertEquals(actual, expected);
    }
    /**
     * Tests the add kernal method by calling it twice
     */
    //allen
    @Test
    public void addTwoTest() {
        Map<String, String> actual = this.createFromArgsTest();
        Map<String, String> expected = this.createFromArgsRef("k1", "v1", "k2", "v2");
        actual.add("k1", "v1");
        actual.add("k2", "v2");
        assertEquals(actual, expected);
    }

    /**
     * Tests remove kernal method on the mpa that will be empty
     */
    //allen
    @Test
    public void removeEmptyMapTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v3");
        Map<String, String> expected = this.createFromArgsRef();
        actual.remove("k1");
        assertEquals(actual, expected);
    }

    /**
     * Tests remove kernal method on a singl pair
     */
    //allen
    @Test
    public void removeRegularTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        Map<String, String> expected = this.createFromArgsRef("k1", "v1","k3", "v3");
        actual.remove("k2");
        assertEquals(actual, expected);
    }

    /**
     * Tests remove kernal method on two pairs
     */
    //allen
    @Test
    public void removeTwoTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        Map<String, String> expected = this.createFromArgsRef("k1", "v1");
        actual.remove("k2");
        actual.remove("k3");
        assertEquals(actual, expected);
    }

    /**
     * Tests removeAny kernal method on a map
     */
    //Eashan
    @Test
    public void removeAnyTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        Map<String, String> expected = this.createFromArgsRef("k2", "v2", "k3", "v3");
        actual.removeAny();
        assertEquals(actual, expected);
    }

    /**
     * Tests removeAny kernal method twice on a map
     */
    @Test
    public void removeAnyTwoTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        Map<String, String> expected = this.createFromArgsRef("k3", "v3");
        actual.removeAny();
        actual.removeAny();
        assertEquals(actual, expected);
    }


    /**
     * Tests value kernal method on a map pair
     */
    @Test
    public void valueRegularTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        String expectedValue = "v1";
        assertEquals(actual.value("k1"), expectedValue);
    }

    /**
     * Tests value kernal method on two map pairs
     */
    @Test
    //Eashan
    public void valueTwoTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        String expectedValue = "v1";
        String expectedValue2 = "v2";
        assertEquals(actual.value("k1"), expectedValue);
        assertEquals(actual.value("k2"), expectedValue2);
    }



    /**
     * Tests hasKey kernal method when it is false
     */
    @Test
    //Eashan
    public void hasKeyFalseTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        boolean expectedValue = false;
        assertEquals(actual.hasKey("k4"), expectedValue);
    }

    /**
     * Tests hasKey kernal method when it is true
     */
    @Test
    //Eashan
    public void hasKeyTrueTest() {
        Map<String, String> actual = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        boolean expectedValue = true;
        assertEquals(actual.hasKey("k1"), expectedValue);
    }

    /**
     * Tests size kernal method when the map is empty
     */
    //allen
    @Test
    public void sizeEmptyTest() {
        Map<String, String> map = this.createFromArgsTest();
        int expected = 0;
        int actual = map.size();
        assertEquals(actual, expected);
    }

    /**
     * Tests size on a map with a single pair.
     */
    //allen
    @Test
    public void sizeOnePairTest() {
        Map<String, String> map = this.createFromArgsTest("k1", "v1");
        int expected = 1;
        int actual = map.size();
        assertEquals(actual, expected);
    }

    /**
     * Tests size kernal method when there are mutliple pairs.
     */
    //allen
    @Test
    public void sizeMultiplePairTest() {
        Map<String, String> map = this.createFromArgsTest("k1", "v1", "k2", "v2", "k3", "v3");
        int expected = 3;
        int actual = map.size();
        assertEquals(actual, expected);
    }
}
