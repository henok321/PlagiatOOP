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
        CliParser cliParser = new CliParser();

        try {
            path = cliParser.parseCliParams(args);
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
}
