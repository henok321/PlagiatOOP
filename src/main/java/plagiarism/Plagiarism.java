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
 * <p>The filtered data will be stored in this object, and the object provides a method to calculate
 * a simple checksum of the input text.
 *
 * <p>This checksum gives information if the input text might be a plagiarism or not.
 *
 * @author Hendrik Brinkmann
 */
public class Plagiarism {

  private final Map<String, Integer> map;

  public Plagiarism(final InputStream input) throws IOException {
    this.map = new HashMap<>();
    init(input);
  }

  private void init(final InputStream input) throws IOException {

    try (final FilterReader filterChain =
        new IrrelevantWordsFilter(
            new WordSeparatorFilter(
                new CharacterFilter(new BufferedReader(new InputStreamReader(input)))))) {
      final var buffer = new char[256];
      int readChars;

      while ((readChars = filterChain.read(buffer)) != -1) {
        if (readChars != 0) {
          map.merge(new String(buffer, 0, readChars), 1, Integer::sum);
        }
      }
    }
  }

  public int checksum() {
    return map.entrySet().stream()
        .mapToInt(entry -> entry.getKey().chars().sum() * entry.getValue())
        .sum();
  }

  @Override
  public String toString() {
    return map.toString();
  }
}
