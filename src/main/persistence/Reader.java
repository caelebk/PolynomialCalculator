package persistence;

import model.SimpleCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of expressions parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<SimpleCalculator> readExpressions(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of expressions parsed from list of strings
    // where each string contains data for one account
    private static List<SimpleCalculator> parseContent(List<String> fileContent) {
        List<SimpleCalculator> simpleCalculators = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            simpleCalculators.add(parseCalculator(lineComponents));
        }

        return simpleCalculators;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components is size 1 as a representation of the expression
    // EFFECTS: returns an expression constructed from components
    private static SimpleCalculator parseCalculator(List<String> components) {
        String exp = components.get(0);
        return new SimpleCalculator(exp);
    }
}