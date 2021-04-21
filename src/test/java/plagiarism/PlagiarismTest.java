package plagiarism;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class PlagiarismTest {

  private Plagiarism plagiarism;

  @Test
  void checksum() throws Exception {
    final String input = "Hallo Welt";
    plagiarism = new Plagiarism(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    final int expected = 972;
    final int actual = plagiarism.checksum();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void checksumWithSpecialCharacters() throws Exception {
    final String input = "Hallo Welt!";
    plagiarism = new Plagiarism(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    final int expected = 972;
    final int actual = plagiarism.checksum();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void checksumWithIrrelevantWords() throws Exception {
    final String input = "Hallo die Welt";
    plagiarism = new Plagiarism(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    final int expected = 972;
    final int actual = plagiarism.checksum();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void checksumWithIrrelevantWordsAndSpecialCharacters() throws Exception {
    final String input = "Hallo, die Welt!";
    plagiarism = new Plagiarism(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    final int expected = 972;
    final int actual = plagiarism.checksum();
    assertThat(actual).isEqualTo(expected);
  }
}
