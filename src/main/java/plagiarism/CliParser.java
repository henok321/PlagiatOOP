package plagiarism;

import plagiarism.exception.InvalidCommandLineParameter;

public class CliParser {

    public String parseCliParams(String[] args) throws InvalidCommandLineParameter {
        if(args==null)
            throw new InvalidCommandLineParameter("missing input file paramter");
        else if((args[0].length() < 3) || !(args[0].startsWith("-i=")))
            throw new InvalidCommandLineParameter("invalid input file parameter");
        else
            return args[0].split("=")[1];


    }
}
