package plagiarism;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Reads a text, filters irrelevant words and counts word occurrences. The checksum gives
 * information if the input text might be a plagiarism or not.
 *
 * @author Hendrik Brinkmann
 */
public class Plagiarism {

  private static final Set<String> IRRELEVANT_WORDS =
      Set.of(
          "die", "der", "und", "in", "zu", "den", "das", "von", "sie", "ist", "des", "sich", "mit",
          "dem", "dass", "er", "es", "ein", "ich", "auf", "so", "eine", "auch", "als", "an", "nach",
          "wie", "im", "man", "aber", "aus", "durch", "wenn", "nur", "war", "noch", "werden", "bei",
          "hat", "wir", "was", "wird", "sein", "einen", "welche", "sind", "oder", "zur", "um",
          "haben", "einer", "mir", "ihm", "einem", "ihr", "uns", "da", "zum", "kann", "doch", "vor",
          "mich", "ihn", "du", "hatte", "seine", "am", "denn", "nun", "unter", "sehr", "selbst",
          "schon", "hier", "bis", "habe", "ihre", "dann", "ihnen", "seiner", "alle", "meine", "vom",
          "wo", "eines", "sei", "ja", "wurde", "seinen", "wohl", "dieses", "ihren", "würde",
          "diesen", "sondern", "weil", "welcher", "diesem", "alles", "waren", "will", "mein",
          "also", "soll", "worden", "lassen", "dies", "machen", "ihrer");

  private final Map<String, Integer> map = new HashMap<>();

  public Plagiarism(final InputStream input) throws IOException {
    final String text;
    try (input) {
      text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
    }
    Arrays.stream(text.toLowerCase().split("[^\\p{L}\\p{N}]+"))
        .filter(word -> !word.isEmpty())
        .filter(word -> !IRRELEVANT_WORDS.contains(word))
        .forEach(word -> map.merge(word, 1, Integer::sum));
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
