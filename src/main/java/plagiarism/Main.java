package plagiarism;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import plagiarism.exception.InvalidCommandLineParameter;

/**
 * Initialize an instance of {@link Plagiarism}, get user input and execute the program.
 *
 * @author Hendrik Brinkmann
 */
public class Main {

  public static void main(final String[] args) {

    final AtomicReference<String> path = null;

    try {
      path.set(parseCliParams(args));
    } catch (final InvalidCommandLineParameter e) {
      System.err.println("Invalid parameter.");
    }

    final File file = new File(path.get());
    Plagiarism plagiarism = null;

    try {
      plagiarism = new Plagiarism(file);
    } catch (final IOException e) {
      System.err.println("error while reading file");
    }

    System.out.println(plagiarism);
    System.out.println("Checksum:\t" + plagiarism.checksum());
  }

  private static String parseCliParams(final String[] args) throws InvalidCommandLineParameter {
    if (args == null) throw new InvalidCommandLineParameter("missing input file paramter");
    else if ((args[0].length() < 3) || !(args[0].startsWith("-i=")))
      throw new InvalidCommandLineParameter("invalid input file parameter");
    else return args[0].split("=")[1];
  }
}
