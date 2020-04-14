package plagiarism;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plagiarism.exception.InvalidCommandLineParameter;

/**
 * Initialize an instance of {@link Plagiarism}, get user input and execute the program.
 *
 * @author Hendrik Brinkmann
 */
public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {

    final AtomicReference<String> path = new AtomicReference<>();

    try {
      path.set(parseCliParams(args));
    } catch (final InvalidCommandLineParameter e) {
      LOGGER.error("Invalid parameter.");
    }

    final File file = new File(path.get());

    try {
      InputStream input;

      if (file.exists() && file.canRead()) {
        input = new FileInputStream(file);
      } else {
        input = Main.class.getResourceAsStream("/" + file.getPath());
      }

      if (input == null) {
        LOGGER.error("Cannot resolve file with path <{}>.", file);
      } else {
        Plagiarism plagiarism = new Plagiarism(input);
        LOGGER.info(plagiarism.toString());
        LOGGER.info("Checksum:\t{}", plagiarism.checksum());
      }
    } catch (IOException e) {
      LOGGER.error("Unknown error while processing file with path <{}>.", file);
    }
  }

  private static String parseCliParams(final String[] args) {
    if (args == null) {
      throw new InvalidCommandLineParameter("missing input file paramter");
    } else if ((args[0].length() < 3) || !(args[0].startsWith("-i="))) {
      throw new InvalidCommandLineParameter("invalid input file parameter");
    } else {
      return args[0].split("=")[1];
    }
  }
}
