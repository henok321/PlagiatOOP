package plagiat.exception;

/**
 * Thrown if the user enters an invalid command line parameter.
 *
 * @author Hendrik Brinkmann
 */
public class InvalidCommandLineParameter extends Exception {

    public InvalidCommandLineParameter(String message) {
        super(message);
    }
}
