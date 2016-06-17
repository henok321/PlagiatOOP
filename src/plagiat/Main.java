package plagiat;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("test.txt");
        Plagiarism plagiarism = null;
        try {
            plagiarism = new Plagiarism(file);
        } catch (IOException e) {
            System.out.println("error while reading file");
        }
        System.out.println(plagiarism);
        System.out.println("Checksum:\t" + plagiarism.checksum());
        System.out.println(plagiarism.longestWord());
        System.out.println(plagiarism.shortestWord());
        System.out.println(plagiarism.mostFrequent());
        System.out.println(plagiarism.rarest());
    }
}
