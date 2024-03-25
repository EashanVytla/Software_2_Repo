import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber3;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {
    @Override
    protected final NaturalNumber constructorTest() {
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        return new NaturalNumber3(n);
    }

    //test routine case with 9 and 99
    @Override
    @Test
    public void multiplyBy10TestInt() {
        NaturalNumber test = this.constructorTest(9);
        NaturalNumber actual = this.constructorRef(99);
        test.multiplyBy10(9);

        assertEquals(test, actual);
    }

    @Test
    public void multiplyBy10TestZero() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber actual = this.constructorRef(0);
        test.multiplyBy10(0);

        assertEquals(test, actual);
    }

    //test empty case where NN is empty and appends 2
    @Test
    public void multiplyBy10EmptyTest() {
        NaturalNumber actual = this.constructorTest();
        NaturalNumber expected = this.constructorRef(2);
        actual.multiplyBy10(2);
        assertEquals(actual, expected);

    }
    //test multiply by 10 to go voer the max integer value
    @Test
    public void multiplyBy10MaxTest() {
        NaturalNumber actual = this.constructorTest("2147483649");
        NaturalNumber expected = this.constructorRef("214748364");
        expected.multiplyBy10(9);
        assertEquals(actual, expected);

    }
    //test MultiplyBy10 when a NN is 0
    @Test
    public void multiplyBy10ZeroTest() {
        NaturalNumber actual = this.constructorTest(20);
        NaturalNumber expected = this.constructorRef(2);
        expected.multiplyBy10(0);
        assertEquals(actual, expected);

    }
    //Test DivideBy10 with the Str Constructor Routine case
    @Override
    @Test
    public void divideBy10TestStr() {
        NaturalNumber test = this.constructorTest("81");
        NaturalNumber actual = this.constructorRef("8");
        test.divideBy10();


        assertEquals(test, actual);
    }
    //Test Divide By 10 NN constructor routine cas
    @Override
    @Test
    public void divideBy10TestNN() {
        NaturalNumber numTest = new NaturalNumber1L(23);
        NaturalNumber test = this.constructorTest(numTest);
        NaturalNumber numActual = new NaturalNumber1L(2);
        NaturalNumber actual = this.constructorRef(numActual);
        test.divideBy10();


        assertEquals(test, actual);
    }
//Test DivideBy10 With both being 0
    @Test
    public void divideBy10ZeroTest() {
        NaturalNumber actual = this.constructorTest();
        NaturalNumber expected = this.constructorRef(0);
        int expectedNum = 0;
        int remainder = actual.divideBy10();
        assertEquals(expected, actual);
        assertEquals(expectedNum, remainder);

    }
//Test is Zero with an empty NN
    @Override
    @Test
    public void isZeroTestEmpty() {
        NaturalNumber test = this.constructorTest();
        assertTrue(test.isZero());
    }
    //Test is Zero with a NN that is 0
    @Test
    public void isZeroTrueTest() {
        NaturalNumber actual = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);
        boolean actualBoolean = actual.isZero();
        boolean expectedBoolean = expected.isZero();
        assertTrue(actualBoolean);
        assertTrue(expectedBoolean);
    }
    //Test isZero with a NN that is not 0

    @Test
    public void isZeroFalseTest() {
        NaturalNumber actual = this.constructorTest(2);
        NaturalNumber expected = this.constructorRef(9);
        boolean actualBoolean = actual.isZero();
        boolean expectedBoolean = expected.isZero();
        assertTrue(!actualBoolean);
        assertTrue(!expectedBoolean);

    }
    //Testing the empty contructor
    @Test
    public void emptyConstructorTest() {
        NaturalNumber actual = this.constructorTest();
        NaturalNumber expected = this.constructorRef();
        assertEquals(actual, expected);
    }
    //testing the constructor with zero
    @Test
    public void constructorZeroTest() {
        NaturalNumber actual = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef(0);
        assertEquals(actual, expected);
    }
    //testing string constructor
    @Test
    public void ConstructorStringTest() {
        NaturalNumber actual = this.constructorTest("65");
        NaturalNumber expected = this.constructorRef("65");
        assertEquals(actual, expected);
    }
    //testing string contructor with zero
    @Test
    public void ConstructorStringZeroTest() {
        NaturalNumber actual = this.constructorTest("0");
        NaturalNumber expected = this.constructorRef("0");
        assertEquals(actual, expected);
    }
    //testing string contructor with number over max Int value
    @Test
    public void ConstructorStringMaxTest() {
        NaturalNumber actual = this.constructorTest("2147483648");
        NaturalNumber expected = this.constructorRef("2147483648");
        assertEquals(actual, expected);
    }
}
