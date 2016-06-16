package plagiat;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("test.txt");
        Plagiat plagiat = null;
        try {
            plagiat = new Plagiat(file);
        } catch (IOException e) {
            System.out.println("error while reading file");
        }
        System.out.println(plagiat);
        System.out.println("Checksum:\t" + plagiat.checksum());
    }
}
