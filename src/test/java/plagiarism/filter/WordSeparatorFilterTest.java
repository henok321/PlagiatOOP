package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

class WordSeparatorFilterTest {

  private WordSeparatorFilter wordSeparatorFilter;

  @Test
  void shouldSplitOnWhitespace() throws Exception {
    wordSeparatorFilter = new WordSeparatorFilter(new StringReader("aa "));
    final int expected = 2;
    final char[] cbuf = new char[2];
    final int actual = wordSeparatorFilter.read(cbuf);
    assertThat(String.valueOf(cbuf)).isEqualTo("aa");
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    wordSeparatorFilter = new WordSeparatorFilter(new StringReader(""));

    final int actual = wordSeparatorFilter.read();
    assertThat(actual).isEqualTo(-1);
  }
}
