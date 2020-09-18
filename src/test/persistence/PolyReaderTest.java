package persistence;

import model.PolynomialCalculator;
import model.SimpleCalculator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PolyReaderTest {
    private static final String TEST_FILE = "./data/testPolynomials.txt";

    @Test
    void testConstructor() {
        PolyReader x = new PolyReader();
    }
    @Test
    void testParseFile1() {
        try {
            List<PolynomialCalculator> expressions = PolyReader.readExpressions(new File(TEST_FILE));
            PolynomialCalculator p1 = expressions.get(0);
            assertEquals("1x^2 + 2x^1 + 3", p1.getPolyExp());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    void testIOException() {
        try {
            Reader.readExpressions(new File("./path/does/not/exist/testExpression2.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}