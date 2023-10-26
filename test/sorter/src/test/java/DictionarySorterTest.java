import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DictionarySorterTest {
    private static final Logger logger = LoggerFactory.getLogger(DictionarySorterTest.class);

    @Test
    public void testSortDictionary() {
        String inputFile = "test_input.txt";
        String outputFile = "test_output.txt";
        String separator = ":";
        int batchSize = 2;

        try {
            String testInput = "banana:A fruit\napple:A fruit\norange:A fruit\n";
            Files.write(Path.of(inputFile), testInput.getBytes());

            DictionarySorter.sortDictionary(inputFile, outputFile, separator, batchSize);

            List<String> sortedTerms = Files.readAllLines(Path.of(outputFile));
            assertEquals("apple:A fruit", sortedTerms.get(0));
            assertEquals("banana:A fruit", sortedTerms.get(1));
            assertEquals("orange:A fruit", sortedTerms.get(2));

        } catch (IOException e) {
            logger.error("An error occurred during the test: {}", e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(Path.of(inputFile));
                Files.deleteIfExists(Path.of(outputFile));
            } catch (IOException e) {
                logger.error("An error occurred while deleting temporary files: {}", e.getMessage());
            }
        }
    }
}