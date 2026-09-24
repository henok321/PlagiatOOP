package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CharacterFilterTest {

  private CharacterFilter characterFilter;

  private static Stream<Arguments> filterParameters() {
    return Stream.of(
        Arguments.of("$", ' '),
        Arguments.of(" ", ' '),
        Arguments.of("A", 'a'),
        Arguments.of("a", 'a'),
        Arguments.of("1", '1'),
        Arguments.of("", -1));
  }

  @ParameterizedTest
  @MethodSource("filterParameters")
  void filterTest(final String input, final int expected) throws Exception {
    characterFilter = new CharacterFilter(new StringReader(input));
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
