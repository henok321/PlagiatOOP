package plagiarism.exception;

import java.io.Serial;

/**
 * Thrown if the user enters an invalid command line parameter.
 *
 * @author Hendrik Brinkmann
 */
public class InvalidCommandLineParameter extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public InvalidCommandLineParameter(final String message) {
    super(message);
  }
}
