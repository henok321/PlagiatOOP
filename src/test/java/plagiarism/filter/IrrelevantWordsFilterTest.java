package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

class IrrelevantWordsFilterTest {

  private IrrelevantWordsFilter irrelevantWordsFilter;

  @Test
  void shouldFilterIrrelevantWord() throws Exception {
    irrelevantWordsFilter = new IrrelevantWordsFilter(new StringReader("die"));
    final int expected = 0;
    final char[] cbuf = "die".toCharArray();

    final int actual = irrelevantWordsFilter.read(cbuf);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterRelevantWord() throws Exception {
    irrelevantWordsFilter = new IrrelevantWordsFilter(new StringReader("pizza"));
    final int expected = 5;
    final char[] cbuf = "pizza".toCharArray();

    final int actual = irrelevantWordsFilter.read(cbuf);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    irrelevantWordsFilter = new IrrelevantWordsFilter(new StringReader(""));
    final int expected = -1;
    final int actual = irrelevantWordsFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
