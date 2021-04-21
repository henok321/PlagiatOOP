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
class IrrelevantWordsFilterTest {
  @Mock private Reader in;

  private IrrelevantWordsFilter irrelevantWordsFilter;

  @BeforeEach
  void setUp() {
    irrelevantWordsFilter = new IrrelevantWordsFilter(in);
  }

  @Test
  void shouldFilterIrrelevantWord() throws Exception {
    final int expected = 0;
    final char[] cbuf = "die".toCharArray();
    when(in.read(cbuf)).thenReturn(cbuf.length);

    final int actual = irrelevantWordsFilter.read(cbuf);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterRelevantWord() throws Exception {
    final int expected = 5;
    final char[] cbuf = "pizza".toCharArray();
    when(in.read(cbuf)).thenReturn(cbuf.length);

    final int actual = irrelevantWordsFilter.read(cbuf);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    final int input = -1;
    final int expected = -1;
    when(in.read()).thenReturn(input);
    final int actual = irrelevantWordsFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
