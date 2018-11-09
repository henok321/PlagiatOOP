package plagiarism.exception;

/**
 * Thrown if the user enters an invalid command line parameter.
 *
 * @author Hendrik Brinkmann
 */
public class InvalidCommandLineParameter extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCommandLineParameter(String message) {
        super(message);
    }
}
