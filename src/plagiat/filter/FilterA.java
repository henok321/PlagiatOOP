package plagiat.filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class FilterA extends FilterReader {

    public FilterA(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = in.read();
        if (c == -1) return -1;
        if (Character.isLetterOrDigit(c)) return Character.toLowerCase(c);
        return ' ';
    }
}
