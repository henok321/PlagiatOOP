package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IrrelevantWordsFilterTest {

  private IrrelevantWordsFilter irrelevantWordsFilter;

  static Stream<Arguments> filterParameters() {
    return Stream.of(Arguments.of("die", 0), Arguments.of("pizza", 5), Arguments.of("", -1));
  }

  @ParameterizedTest
  @MethodSource("filterParameters")
  void filterTest(final String input, final int expected) throws Exception {
    irrelevantWordsFilter = new IrrelevantWordsFilter(new StringReader(input));
    final char[] cbuf = new char[128];

    final int actual = irrelevantWordsFilter.read(cbuf);
    assertThat(actual).isEqualTo(expected);
  }
}
