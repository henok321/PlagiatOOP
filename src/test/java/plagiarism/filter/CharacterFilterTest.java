package plagiarism.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

class CharacterFilterTest {

  private CharacterFilter characterFilter;

  @Test
  void shouldFilterSpecialCharacter() throws Exception {
    characterFilter = new CharacterFilter(new StringReader("$"));
    final int expected = ' ';
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterWhitespace() throws Exception {
    characterFilter = new CharacterFilter(new StringReader(" "));
    final int expected = ' ';
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldFilterToLowerCase() throws Exception {
    characterFilter = new CharacterFilter(new StringReader("A"));

    final int expected = 'a';
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterLowerCase() throws Exception {
    characterFilter = new CharacterFilter(new StringReader("a"));
    final int expected = 'a';
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterNumeric() throws Exception {
    characterFilter = new CharacterFilter(new StringReader("1"));
    final int expected = '1';
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    characterFilter = new CharacterFilter(new StringReader(""));
    final int expected = -1;
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
