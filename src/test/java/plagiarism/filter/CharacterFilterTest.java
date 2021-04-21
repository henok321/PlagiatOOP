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
class CharacterFilterTest {

  @Mock private Reader in;

  private CharacterFilter characterFilter;

  @BeforeEach
  void setUp() {
    characterFilter = new CharacterFilter(in);
  }

  @Test
  void shouldFilterSpecialCharacter() throws Exception {
    final int input = '$';
    final int expected = ' ';
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterWhitespace() throws Exception {
    final int input = ' ';
    final int expected = ' ';
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldFilterToLowerCase() throws Exception {
    final int input = 'A';
    final int expected = 'a';
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterLowerCase() throws Exception {
    final int input = 'a';
    final int expected = 'a';
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldNotFilterNumeric() throws Exception {
    final int input = '1';
    final int expected = '1';
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void shouldEndStream() throws Exception {
    final int input = -1;
    final int expected = -1;
    when(in.read()).thenReturn(input);
    final int actual = characterFilter.read();
    assertThat(actual).isEqualTo(expected);
  }
}
