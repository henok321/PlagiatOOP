package plagiat;

import plagiat.exception.InvalidCommandLineParameter;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String path = null;

        try {
            path = readComamndLineParams(args);
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

    private static String readComamndLineParams(String[] args) throws InvalidCommandLineParameter {

        if (args == null || args.length == 0) {
            throw new InvalidCommandLineParameter("missing input file paramter");
        }

        if ((args[0].length() < 3) || !(args[0].substring(0, 3).equalsIgnoreCase("-i="))) {
            throw new InvalidCommandLineParameter("invalid input file paramter");
        }

        return args[0].split("=")[1];
    }
}
