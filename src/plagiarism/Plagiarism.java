package plagiarism;

import plagiarism.filter.CharacterFilter;
import plagiarism.filter.IrrelevantWordsFilter;
import plagiarism.filter.WordSeparator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Plagiarism builds a filter chain to read and filter an input text.
 * <p>
 * The filtered data will be stored in this object and the object provides a method to calculate a simple checksum of the input text.
 * <p>
 * This checksum gives information, if the input text might be a plagiarism or not.
 *
 * @author Hendrik Brinkmann
 */
public class Plagiarism {

    private Map<String, Integer> map;
    private File file;

    public Plagiarism(File file) throws IOException {
        this.file = file;
        this.map = new HashMap<>();
        init();
    }

    private void init() throws IOException {

        BufferedReader inputReader = new BufferedReader(new FileReader(file));
        FilterReader filterChain = new IrrelevantWordsFilter(new WordSeparator(new CharacterFilter(inputReader)));

        char[] buffer = new char[256];
        int c = -1;

        while ((c = filterChain.read(buffer)) != -1) {
            if (c != 0) {
                String s = new String(buffer, 0, c);
                map.compute(s, (k, v) -> (v == null) ? 1 : ++v);
            }
        }
    }

    public int checksum() {
        return map.keySet()
                .stream()
                .mapToInt(s -> digitSum(s) * map.get(s))
                .sum();
    }

    private int digitSum(String s) {
        return s.chars().sum();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
