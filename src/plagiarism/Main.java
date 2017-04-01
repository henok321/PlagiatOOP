package plagiarism;

import plagiarism.exception.InvalidCommandLineParameter;

import java.io.File;
import java.io.IOException;

/**
 * Initialize an instance of  {@link Plagiarism}, get user input and execute the program.
 *
 * @author Hendrik Brinkmann
 */
public class Main {

    public static void main(String[] args) {

        String path = null;

        try {
            path = readCommandLineParams(args);
        } catch (InvalidCommandLineParameter e) {
            e.printStackTrace();
        }

        File file = new File(path);
        Plagiarism plagiarism = null;

        try {
            plagiarism = new Plagiarism(file);
        } catch (IOException e) {
            System.out.println("error while reading file");
        }

        System.out.println(plagiarism);
        System.out.println("Checksum:\t" + plagiarism.checksum());
    }

    private static String readCommandLineParams(String[] args) throws InvalidCommandLineParameter {

        if (args == null || args.length == 0) {
            throw new InvalidCommandLineParameter("missing input file parameter");
        }

        if ((args[0].length() < 3) || !(args[0].startsWith("-i="))) {
            throw new InvalidCommandLineParameter("invalid input file parameter");
        }

        return args[0].split("=")[1];
    }
}
