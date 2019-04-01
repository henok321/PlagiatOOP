package plagiarism;

import plagiarism.exception.InvalidCommandLineParameter;

import java.io.File;
import java.io.IOException;

/**
 * v
 * Initialize an instance of  {@link Plagiarism}, get user input and execute the program.
 *
 * @author Hendrik Brinkmann
 */
public class Main {

    public static void main(String[] args) {

        String path = null;

        try {
            path = parseCliParams(args);
        } catch (InvalidCommandLineParameter e) {
            System.err.println("Invalid parameter.");
        }

        File file = new File(path);
        Plagiarism plagiarism = null;

        try {
            plagiarism = new Plagiarism(file);
        } catch (IOException e) {
            System.err.println("error while reading file");
        }

        System.out.println(plagiarism);
        System.out.println("Checksum:\t" + plagiarism.checksum());
    }

    private static String parseCliParams(String[] args) throws InvalidCommandLineParameter {
        if (args == null)
            throw new InvalidCommandLineParameter("missing input file paramter");
        else if ((args[0].length() < 3) || !(args[0].startsWith("-i=")))
            throw new InvalidCommandLineParameter("invalid input file parameter");
        else
            return args[0].split("=")[1];


    }
}
