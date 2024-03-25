import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Allen Thomas and Eashan Vytla
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size


    @Test
    public void onlyConstructorTest() {
        Set<String> actual = this.constructorTest();
        Set<String> expected = this.createFromArgsRef();
        assertEquals(actual, expected);
    }

    //testing adding from empty set
    @Test
    public void addEmptySetTest() {
        Set<String> actual = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef("one");
        actual.add("one");
        assertEquals(actual, expected);
    }

    //tests with adding one to non empty
    @Test
    public void addRoutineTest() {
        Set<String> actual = this.createFromArgsTest("one","two");
        Set<String> expected = this.createFromArgsRef("one");
        expected.add("two");
        assertEquals(actual, expected);
    }

    //tests removing a element to become empty
    @Test
    public void removeEmptySetTest() {
        Set<String> actual = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef("one");
        String removed = expected.remove("one");
        assertEquals(expected, actual);
        assertEquals(removed, "one");
    }

    //tests removing from a non empty set
    @Test
    public void removeRoutineTest() {
        Set<String> actual = this.createFromArgsTest("one","two");
        Set<String> expected = this.createFromArgsRef("one");
        String removed = actual.remove("two");
        assertEquals(expected, actual);
        assertEquals(removed, "two");
    }

    //tests remove any to become empty set
    @Test
    public void removeAnyEmptyTest() {
        Set<String> actual = this.createFromArgsTest("one");
        Set<String> expected = this.createFromArgsRef("one");
        String removedActual = actual.removeAny();
        boolean contains = expected.contains(removedActual);
        assertTrue(contains);
    }

    //tests remove any to become empty set
    @Test
    public void removeAnyExistingTest() {
        Set<String> actual = this.createFromArgsTest("one","two", "three");
        Set<String> expected = this.createFromArgsRef("one", "two", "three");
        String removed = actual.removeAny();
        boolean contains = expected.contains(removed);
        assertTrue(contains);
    }

    //tests contains when it is true
    @Test
    public void containsTrueTest() {
        Set<String> n = this.createFromArgsTest("one");
        boolean contains = n.contains("one");
        assertTrue(contains);
    }

    //tests contains when it is true and multiple in set
    @Test
    public void containsTrueMultipleTest() {
        Set<String> n = this.createFromArgsTest("one","two","three");
        boolean contains = n.contains("two");
        assertTrue(contains);
    }

    //tests contains when it is false and single ins et
    @Test
    public void containsFalseTest() {
        Set<String> set = this.createFromArgsTest("one");
        boolean contains = set.contains("four");
        assertTrue(!contains);
    }

    //tests contains when it is false and multiple in set
    @Test
    public void containsFalseMultipleTest() {
        Set<String> set = this.createFromArgsTest("one","two","three");
        boolean contains = set.contains("four");
        assertTrue(!contains);
    }

    //tests size when set is empty
    @Test
    public void sizeEmptyTest() {
        Set<String> set = this.createFromArgsTest();
        int expected = 0;
        int actual = set.size();
        assertEquals(actual, expected);
    }

    //tests size when set has multple elements
    @Test
    public void sizeRoutineTest() {
        Set<String> set = this.createFromArgsTest("one","two","three");
        int expected = 3;
        int actual = set.size();
        assertEquals(actual, expected);
    }

    //tests size when set has one element
    @Test
    public void sizeOneTest() {
        Set<String> set = this.createFromArgsTest("one");
        int expected = 1;
        int actual = set.size();
        assertEquals(actual, expected);
    }
}
