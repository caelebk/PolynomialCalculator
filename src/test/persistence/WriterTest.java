package persistence;

import model.PolynomialCalculator;
import model.SimpleCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testExpressions.txt";
    private static final String TEST_FILE2 = "./data/testPolynomials.txt";
    private Writer testWriter;
    private Writer testWriter2;
    private SimpleCalculator s1;
    private SimpleCalculator s2;
    private PolynomialCalculator p1;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testWriter2 = new Writer(new File(TEST_FILE2));
        s1 = new SimpleCalculator("1 + 2");
        s2 = new SimpleCalculator("1 + 3 + 4");
        p1 = new PolynomialCalculator();
        p1.addTerm(0, 3);
        p1.addTerm(1, 2);
        p1.addTerm(2, 1);
    }

    @Test
    void testWriteExpression() {
        // save expression to file
        testWriter.write(s1);
        testWriter.write(s2);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<SimpleCalculator> expressions = Reader.readExpressions(new File(TEST_FILE));
            SimpleCalculator s1 = expressions.get(0);
            assertEquals("1 + 2", s1.getExpression());

            SimpleCalculator s2 = expressions.get(1);
            assertEquals("1 + 3 + 4", s2.getExpression());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriteExpression2() {
        // save expression to file
        testWriter2.write(p1);
        System.out.println(p1.getPolyExp());
        testWriter2.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<PolynomialCalculator> polyExpressions = PolyReader.readExpressions(new File(TEST_FILE2));
            PolynomialCalculator p1 = polyExpressions.get(0);
            assertEquals("1x^2 + 2x^1 + 3", p1.getPolyExp());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}