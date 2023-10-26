import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DictionarySorter {
    private static final Logger logger = LoggerFactory.getLogger(DictionarySorter.class);

    public static void sortDictionary(String inputFileName, String outputFileName, String separator, int batchSize) {
        List<String> terms = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                terms.add(line);
            }
        } catch (IOException e) {
            logger.error("An error occurred while reading the file '{}': {}", inputFileName, e.getMessage());
        }

        List<String> sortedTerms = new ArrayList<>();
        for (int i = 0; i < terms.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, terms.size());
            List<String> batch = terms.subList(i, endIndex);
            batch.sort(Comparator.comparing(term -> term.split(separator)[0]));
            sortedTerms.addAll(batch);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String term : sortedTerms) {
                writer.write(term + "\n");
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file '{}': {}", outputFileName, e.getMessage());
        }
    }
}