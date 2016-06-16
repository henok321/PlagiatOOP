package plagiat;

import plagiat.filter.FilterA;
import plagiat.filter.FilterB;
import plagiat.filter.FilterC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Plagiat {

    private Map<String, Integer> map;
    private File file;

    public Plagiat(File file) throws IOException {
        this.file = file;
        this.map = new HashMap<>();
        init();
    }

    private void init() throws IOException {
        BufferedReader inputReader = new BufferedReader(new FileReader(file));
        FilterC filterC = new FilterC(new FilterB(new FilterA(inputReader)));
        char[] buffer = new char[256];
        int c = -1;
        while ((c = filterC.read(buffer)) != -1) {
            if (c != 0) {
                String s = String.valueOf(Arrays.copyOfRange(buffer, 0, c));
                if (map.containsKey(s)) map.put(s, map.get(s) + 1);
                else map.put(s, 1);
            }
        }
    }

    public int checksum() {
        int i = 0;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            i += digitSum(e.getKey()) * e.getValue();
        }
        return i;
    }

    private int digitSum(String s) {
        int i = 0;
        for (char c : s.toCharArray()) {
            i += c;
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k).append("\t->\t").append(v).append("\n");
        });

        return sb.toString();
    }
}
