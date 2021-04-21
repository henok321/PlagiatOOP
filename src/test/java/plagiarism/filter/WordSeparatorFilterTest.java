package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WordSeparatorFilterTest {

  @Mock private Reader in;
  private WordSeparatorFilter wordSeparatorFilter;

  @BeforeEach
  void setUp() {
    wordSeparatorFilter = new WordSeparatorFilter(in);
  }

  @Test
  void shouldSplitOnWhitespace() throws Exception {
    final int charakter = 'a';
    final int whitespace = ' ';
    final int expected = 2;
    final char[] cbuf = new char[2];
    when(in.read()).thenReturn(charakter).thenReturn(charakter).thenReturn(whitespace);
    final int actual = wordSeparatorFilter.read(cbuf);
    assertThat(String.valueOf(cbuf)).isEqualTo("aa");
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    final int input = -1;
    final int expected = -1;
    when(in.read()).thenReturn(input);
    final int actual = wordSeparatorFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
