package plagiat.filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class WordSeparator extends FilterReader {

    public WordSeparator(Reader in) {
        super(in);
    }


    @Override
    public int read(char[] cbuf) throws IOException {
        int c = in.read();
        if (c == -1) return -1;
        int i = 0;
        while ((c != -1 && !Character.isWhitespace(c))) {
            cbuf[i] = (char) c;
            c = in.read();
            i++;
        }
        return i;
    }
}
