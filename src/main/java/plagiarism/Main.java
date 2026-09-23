package plagiarism;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Get user input and execute the program.
 *
 * @author Hendrik Brinkmann
 */
public class Main {

  public static void main(final String[] args) {
    try {
      final String path = parseCliParams(args);
      final var file = new File(path);
      final InputStream input;

      if (file.exists() && file.canRead()) {
        input = new FileInputStream(file);
      } else {
        input = Main.class.getResourceAsStream("/" + file.getPath());
      }

      if (input == null) {
        System.err.println("Cannot resolve file with path <" + path + ">.");
      } else {
        final Plagiarism plagiarism = new Plagiarism(input);
        System.out.println("Plagiarism: " + plagiarism);
        System.out.println("Checksum: " + plagiarism.checksum());
      }
    } catch (final IllegalArgumentException e) {
      System.err.println("Invalid parameter: " + e.getMessage());
    } catch (final IOException e) {
      System.err.println("Unknown error while processing file: " + e.getMessage());
    }
  }

  private static String parseCliParams(final String[] args) {
    if (args == null || args.length == 0) {
      throw new IllegalArgumentException("missing input file parameter");
    }
    if (args[0].length() < 3 || !args[0].startsWith("-i=")) {
      throw new IllegalArgumentException("invalid input file parameter");
    }
    return args[0].split("=")[1];
  }
}
