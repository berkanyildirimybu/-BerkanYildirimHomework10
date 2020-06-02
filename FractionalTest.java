import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionalTest {

    @Test
    public void fractionalConstructTest(){
        assertEquals(5,new Fractional(5,2).numerator);
        assertEquals(2,new Fractional(5,2).denominator);
        assertNotEquals(7,new Fractional(5,2).numerator);
        assertNotEquals(7,new Fractional(5,2).denominator);
    }

    @Test
    public void simplifyMethodTest() {

        // In case of NaN, simplify() method should thrown IllegalArgument exception;
        assertThrows(IllegalArgumentException.class, () -> {
            new Fractional(0, 0).simplify();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Fractional(1, 0).simplify();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Fractional(-1, 0).simplify();
        });

        assertEquals("-1/5", new Fractional(-2, 10).simplify().toString());
        assertEquals("3", new Fractional(6, 2).simplify().toString());
        assertEquals("-3", new Fractional(6, -2).simplify().toString());
        assertEquals("7/2", new Fractional(14, 4).simplify().toString());
        assertEquals("-4/3", new Fractional(8, -6).simplify().toString());
        assertEquals("-1/3", new Fractional(-15, 45).simplify().toString());
        assertEquals("5/3", new Fractional(-20, -12).simplify().toString());
        assertEquals("1", new Fractional(4, 4).simplify().toString());
        assertEquals("0", new Fractional(0, 4).simplify().toString());
    }

    @Test
    public void isNaNMethodTest() {

        assertTrue(new Fractional(0, 0).isNaN());
        assertFalse(new Fractional(5, 0).isNaN());
        assertFalse(new Fractional(5, 2).isNaN());
        assertFalse(new Fractional(0, 2).isNaN());
    }

    @Test
    public void isInfinityMethodTest() {

        // Positive Infinity case
        assertTrue(new Fractional(10, 0).isInfinity());

        // Negative Infinity case
        assertTrue(new Fractional(-10, 0).isInfinity());

        // Not an infinity case
        assertFalse(new Fractional(7, 5).isInfinity());
        assertFalse(new Fractional(-5, 5).isInfinity());
        assertFalse(new Fractional(10, -5).isInfinity());
        assertFalse(new Fractional(-15, -5).isInfinity());
    }

    @Test
    public void signMethodTest() {

        // Sign should throw a arithmetic exception at NaN case.
        assertThrows(ArithmeticException.class, () -> {
            new Fractional(0, 0).sign();
        });

        assertEquals(+1, new Fractional(5, 0).sign());
        assertEquals(-1, new Fractional(-5, 0).sign());

        // Zero should do not have a sign.
        assertEquals(0, new Fractional(0, 5).sign());

        assertEquals(+1, new Fractional(5, 5).sign());
        assertEquals(-1, new Fractional(-5, 5).sign());
        assertEquals(+1, new Fractional(-5, -5).sign());
        assertEquals(-1, new Fractional(5, -5).sign());
    }

    @Test
    public void getValueMethodTest() {

        assertEquals(0.4, new Fractional(2,5).getValue(),0.0);
        assertEquals(-0.4, new Fractional(2,-5).getValue(),0.0);
    }

    @Test
    public void EqualsMethodTest() {

        assertTrue(new Fractional(5, 2).equals(new Fractional(5, 2)));
        assertFalse(new Fractional(5, 2).equals(new Fractional(7, 2)));
    }

    @Test
    public void toStringMethodTest() {

        // NaN case
        assertEquals(Fractional.NotANumber, new Fractional(0, 0).toString());

        // Positive and Negative Infinity Case
        assertEquals(Fractional.PositiveInfinity, new Fractional(1, 0).toString());
        assertEquals(Fractional.NegativeInfinity, new Fractional(-1, 0).toString());

        assertEquals("-2/10", new Fractional(-2, 10).toString());
        assertEquals("6/2", new Fractional(6, 2).toString());
        assertEquals("-6/2", new Fractional(6, -2).toString());
        assertEquals("14/4", new Fractional(14, 4).toString());
        assertEquals("-8/6", new Fractional(8, -6).toString());
        assertEquals("-15/45", new Fractional(-15, 45).toString());
        assertEquals("20/12", new Fractional(-20, -12).toString());
        assertEquals("4/4", new Fractional(4, 4).toString());
        assertEquals("0", new Fractional(0, 4).toString());
    }
}