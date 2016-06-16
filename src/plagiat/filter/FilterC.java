package plagiat.filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FilterC extends FilterReader {

    private static Set<String> irrelevantWords = new HashSet<String>(Arrays.asList(
            new String[]{
                    "die", "der", "und", "in", "zu", "den", "das", "von", "sie", "ist", "des", "sich", "mit", "dem", "dass",
                    "er", "es", "ein", "ich", "auf", "so", "eine", "auch", "als", "an", "nach", "wie", "im", "man", "aber",
                    "aus", "durch", "wenn", "nur", "war", "noch", "werden", "bei", "hat", "wir", "was", "wird", "sein",
                    "einen", "welche", "sind", "oder", "zur", "um", "haben", "einer", "mir", "ihm", "einem", "ihr", "uns",
                    "da", "zum", "kann", "doch", "vor", "mich", "ihn", "du", "hatte", "seine", "am", "denn", "nun",
                    "unter", "sehr", "selbst", "schon", "hier", "bis", "habe", "ihre", "dann", "ihnen", "seiner", "alle",
                    "meine", "vom", "wo", "eines", "sei", "ja", "wurde", "seinen", "wohl", "dieses", "ihren", "würde",
                    "diesen", "sondern", "weil", "welcher", "diesem", "alles", "waren", "will", "mein", "also", "soll",
                    "worden", "lassen", "dies", "machen", "ihrer"}
    ));

    public FilterC(Reader in) {
        super(in);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        int c = in.read(cbuf);
        if (c == -1) return -1;
        String s = String.valueOf(Arrays.copyOfRange(cbuf, 0, c));
        if (irrelevantWords.contains(s)) return 0;
        return c;
    }
}
