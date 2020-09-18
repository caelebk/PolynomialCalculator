package persistence;

import model.SimpleCalculator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    private static final String TEST_FILE = "./data/testExpressions.txt";

    @Test
    void testConstructor() {
        Reader x = new Reader();
    }
    @Test
    void testParseFile1() {
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
    void testIOException() {
        try {
            Reader.readExpressions(new File("./path/does/not/exist/testExpression.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}