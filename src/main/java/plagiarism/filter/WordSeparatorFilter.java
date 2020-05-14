package plagiarism.filter;

import static java.lang.Character.*;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This filter separates the character stream to single words.
 *
 * <p>The end of a word has been reached if the last read character is a whitespace or if the end of
 * the stream has been reached.
 *
 * @author Hendrik Brinkmann
 */
public class WordSeparatorFilter extends FilterReader {

  public WordSeparatorFilter(final Reader in) {
    super(in);
  }

  @Override
  public int read(final char[] cbuf) throws IOException {
    int c = in.read();

    if (c == -1) {
      return -1;
    }

    int i = 0;

    while (c != -1 && !isWhitespace(c)) {
      cbuf[i] = (char) c;
      c = in.read();
      i++;
    }

    return i;
  }
}
