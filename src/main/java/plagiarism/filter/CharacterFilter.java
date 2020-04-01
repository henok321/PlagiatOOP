package plagiarism.filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Read a single character and apply the filter criteria.
 *
 * <p>Special characters will be discarded and replaced by whitespaces, digits will be left
 * unchanged and letters will be converted to lower case.
 *
 * @author Hendrik Brinkmann
 */
public class CharacterFilter extends FilterReader {

  public CharacterFilter(final Reader in) {
    super(in);
  }

  @Override
  public int read() throws IOException {
    final int c = in.read();
    if (c == -1) return -1;
    if (Character.isLetterOrDigit(c)) return Character.toLowerCase(c);
    return ' ';
  }
}
