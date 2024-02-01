import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

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
    @Test
    public void multiplyBy10TestInt() {
        NaturalNumber test = this.constructorTest(9);
        NaturalNumber actual = this.constructorRef(99);
        test.multiplyBy10(9);


        assertEquals(test, actual);
    }

    @Test
    public void divideBy10TestStr() {
        NaturalNumber test = this.constructorTest("81");
        NaturalNumber actual = this.constructorRef("8");
        test.divideBy10();


        assertEquals(test, actual);
    }
    @Test
    public void divideBy10TestNN() {
        NaturalNumber numTest = new NaturalNumber1L(23);
        NaturalNumber test = this.constructorTest(numTest);
        NaturalNumber numActual = new NaturalNumber1L(2);
        NaturalNumber actual = this.constructorRef(numActual);
        test.divideBy10();


        assertEquals(test, actual);
    }

    @Test
    public void isZeroTestEmpty() {
        NaturalNumber test = this.constructorTest();

        assertEquals(test.isZero(), true);
    }
}
