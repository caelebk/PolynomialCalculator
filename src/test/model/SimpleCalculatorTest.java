package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    public int x;
    public int y;
    SimpleCalculator test;

    @BeforeEach
    void beforeEach() {
        x = 10;
        y = 5;
        test = new SimpleCalculator("");
    }
    @Test
    void testAdd() {
        assertEquals(15, test.add(x,y));
    }
    @Test
    void testSubtract() {
        assertEquals(5, test.subtract(x,y));
    }
    @Test
    void testDivide() {
        assertEquals(2, test.divide(x,y));
    }
    @Test
    void testMultiply() {
        assertEquals(50, test.multiply(x,y));
    }
    @Test
    void testEval() {
        test = new SimpleCalculator("1 + 2 - 3");
        assertEquals(0.0, test.eval());
        test = new SimpleCalculator("1 * 3 / 3");
        assertEquals(1.0, test.eval());
        test = new SimpleCalculator("1");
        assertEquals(1.0, test.eval());
        test = new SimpleCalculator("1 + 3 * 4");
        assertEquals(13.0, test.eval());
        test = new SimpleCalculator("1 + 3 * 4 - 5 / 5");
        assertEquals(12.0, test.eval());
        test = new SimpleCalculator("1 * 3 + 3 * 4 / 4 + 13 * 2");
        assertEquals(32.0, test.eval());
        test = new SimpleCalculator("113 * 1 + 3");
        assertEquals(116.0, test.eval());
        test = new SimpleCalculator("1 ");
        assertEquals(1.0, test.eval());
        test = new SimpleCalculator("1 + 2");
        assertEquals(3.0, test.eval());
        test = new SimpleCalculator("1    +      2 ");
        assertEquals(3.0, test.eval());
    }
    @Test
    void testGetExpression() {
        assertEquals("", test.getExpression());
        test = new SimpleCalculator("1 + 3");
        assertEquals("1 + 3", test.getExpression());
    }
    @Test
    void testValidInfix() {
        test = new SimpleCalculator("1+2");
        assertFalse(test.valid());
        test = new SimpleCalculator("1 + 2 + 3");
        assertTrue(test.valid());
        test = new SimpleCalculator("1 + 2");
        assertTrue(test.valid());
        test = new SimpleCalculator("12 + 2");
        assertTrue(test.valid());
        test = new SimpleCalculator("12 + 2 +");
        assertFalse(test.valid());
        test = new SimpleCalculator("12 2");
        assertFalse(test.valid());
        test = new SimpleCalculator("1 2 +");
        assertFalse(test.valid());
        test = new SimpleCalculator("+ 2");
        assertFalse(test.valid());
        test = new SimpleCalculator("avc");
        assertFalse(test.valid());
    }

    @Test
    void testInfixToPostfix() {
        test = new SimpleCalculator("8 + 3 * 2 - 3 / 4");
        assertEquals("8 3 2 * + 3 4 / - ", test.getPostfix());
        test = new SimpleCalculator("1 * 3 + 3 * 4 / 4 + 13 * 2");
        assertEquals("1 3 * 3 4 * 4 / + 13 2 * + ", test.getPostfix());
    }
}
