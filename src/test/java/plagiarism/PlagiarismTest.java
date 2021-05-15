package plagiarism;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlagiarismTest {

  private Plagiarism plagiarism;

  @ParameterizedTest
  @ValueSource(
      strings = {
        "Hallo Welt",
        "Hallo die Welt",
        "Hallo Welt!",
        "Hallo die Welt",
        "Hallo, die Welt!"
      })
  void checksum(final String input) throws Exception {
    plagiarism = new Plagiarism(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    final int actual = plagiarism.checksum();
    assertThat(actual).isEqualTo(972);
  }
}
