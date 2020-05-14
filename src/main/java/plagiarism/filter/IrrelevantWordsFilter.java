package plagiarism.filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

/**
 * Discard irrelevant words. All the irrelevant words are defined in an internal set in this class.
 *
 * @author Hendrik Brinkmann
 */
public class IrrelevantWordsFilter extends FilterReader {

  private static final Set<String> irrelevantWords =
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

  public IrrelevantWordsFilter(final Reader in) {
    super(in);
  }

  @Override
  public int read(final char[] cbuf) throws IOException {
    final int c = in.read(cbuf);

    if (c == -1) {
      return -1;
    }

    final String s = new String(cbuf, 0, c);

    if (irrelevantWords.contains(s)) {
      return 0;
    }
    return c;
  }
}
