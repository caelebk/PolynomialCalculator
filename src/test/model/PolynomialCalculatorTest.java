package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialCalculatorTest {

    public TreeMap<Double, Double> x;
    public TreeMap<Double, Double> y;
    public TreeMap<Double, Double> z;
    public TreeMap<Double, Double> e;
    public TreeMap<Double, Double> f;
    public PolynomialCalculator pol;

    @BeforeEach
    void beforeEach() {
        x = new TreeMap<Double, Double>(Collections.reverseOrder());
        y = new TreeMap<Double, Double>(Collections.reverseOrder());
        z = new TreeMap<Double, Double>(Collections.reverseOrder());
        e = new TreeMap<Double, Double>(Collections.reverseOrder());
        f = new TreeMap<Double, Double>(Collections.reverseOrder());
        x.put(0.0,3.0); //3
        x.put(1.0,2.0); // 2x
        x.put(2.0,4.0); // 4x^2
        //poly = 4x^2 + 2x + 3
        y.put(1.0,0.0); //0x
        y.put(2.0,4.0); //4x^2
        y.put(0.0 ,1.0); //1
        //poly = 4x^2 + 1
        z.put(2.0, 0.0); //0x^2
        z.put(1.0, 1.0); //1x
        z.put(0.0, 0.0); //0
        //poly = 1x^1
        e.put(2.0, 4.0);//4x^2
        //poly = 4x^2
        f.put(0.0, 1.0);//1
        pol = new PolynomialCalculator(z);
    }

    @Test
    void testgetPolyExp() {

        //System.out.println(pol.toString());

        assertEquals("1x^1", pol.getPolyExp());
        pol = new PolynomialCalculator(x);
        assertEquals("4x^2 + 2x^1 + 3", pol.getPolyExp());
        pol = new PolynomialCalculator(y);
        assertEquals("4x^2 + 1", pol.getPolyExp());
        pol = new PolynomialCalculator();
        assertEquals("", pol.getPolyExp());
        pol = new PolynomialCalculator(e);
        assertEquals("4x^2", pol.getPolyExp());
        pol = new PolynomialCalculator(f);
        assertEquals("1", pol.getPolyExp());
    }

    @Test
    void testDerive() {
        pol = new PolynomialCalculator(x);
        pol.derivative();
        assertEquals("8x^1 + 2", pol.getPolyExp());
        pol = new PolynomialCalculator(y);
        pol.derivative();
        assertEquals("8x^1",pol.getPolyExp());
        pol = new PolynomialCalculator(z);
        pol.derivative();
        assertEquals("1",pol.getPolyExp());
        pol = new PolynomialCalculator(e);
        pol.derivative();
        assertEquals("8x^1",pol.getPolyExp());
        pol = new PolynomialCalculator();
        pol.derivative();
        assertEquals("", pol.getPolyExp());
        pol = new PolynomialCalculator(f);
        pol.derivative();
        assertEquals("", pol.getPolyExp());
    }

    @Test
    void testAddTerm() {
        pol = new PolynomialCalculator();
        pol.addTerm(0, 3);
        pol.addTerm(1, 2);
        pol.addTerm(2, 1);
        assertEquals("1x^2 + 2x^1 + 3", pol.getPolyExp());
    }

    @Test
    void testConvertLoadedString() {
        pol = new PolynomialCalculator("1x^2 + 2x^1 + 3");
        assertEquals("1x^2 + 2x^1 + 3", pol.getPolyExp());
    }

    @Test
    void testValidPolynomial() {
        pol = new PolynomialCalculator("1x^2 + 2x^1 + 3");
        assertTrue(pol.valid());
        pol = new PolynomialCalculator("1x^2 + 3");
        assertTrue(pol.valid());
        pol = new PolynomialCalculator("3");
        assertTrue(pol.valid());
        pol = new PolynomialCalculator("3 + 1x^1 + 2x^3");
        assertTrue(pol.valid());
        pol = new PolynomialCalculator(" 2xxx      ");
        assertFalse(pol.valid());
        pol = new PolynomialCalculator("2xxx x x");
        assertFalse(pol.valid());
        pol = new PolynomialCalculator("2x^222x333x22 22x222 x");
        assertFalse(pol.valid());
        assertFalse(pol.validTerm("aax^aaa"));
        assertFalse(pol.validTerm("a2x^xa2a"));
        assertFalse(pol.validTerm("2x^xa"));
        assertFalse(pol.validTerm("ax^2"));
    }

    @Test
    void testEval() {
        pol = new PolynomialCalculator("1x^2 + 2x^1 + 3");
        assertEquals(6, pol.eval(1));
        assertEquals(11, pol.eval(2));
        pol = new PolynomialCalculator("10x^2 + 2x^1 + 3");
        assertEquals(15, pol.eval(1));
        assertEquals(47, pol.eval(2));
    }

}