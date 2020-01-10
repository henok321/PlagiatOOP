package plagiarism;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import plagiarism.filter.CharacterFilter;
import plagiarism.filter.IrrelevantWordsFilter;
import plagiarism.filter.WordSeparatorFilter;

/**
 * Plagiarism builds a filter chain to read and filter an input text.
 *
 * <p>The filtered data will be stored in this object and the object provides a method to calculate
 * a simple checksum of the input text.
 *
 * <p>This checksum gives information, if the input text might be a plagiarism or not.
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

    try (FilterReader filterChain =
        new IrrelevantWordsFilter(
            new WordSeparatorFilter(
                new CharacterFilter(new BufferedReader(new FileReader(file)))))) {
      char[] buffer = new char[256];
      int readChars;

      while ((readChars = filterChain.read(buffer)) != -1) {
        if (readChars != 0) {
          String word = new String(buffer, 0, readChars);
          map.compute(word, (k, v) -> (v == null) ? 1 : ++v);
        }
      }
    }
  }

  public int checksum() {
    return map.entrySet().stream()
        .mapToInt(entry -> digitSum(entry.getKey()) * entry.getValue())
        .sum();
  }

  private int digitSum(String word) {
    return word.chars().sum();
  }

  @Override
  public String toString() {
    return map.toString();
  }
}
