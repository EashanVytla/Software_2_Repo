import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map4} using default constructor.
 */
public class Map4Test extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

   /**
     * Tests the no Arg constructor.
     */
    @Test
    public void noArgConstructorTest() {
        Map<String, String> actual = this.constructorTest();
        Map<String, String> expected = this.constructorRef();
        assertEquals(actual, expected);
    }


}
