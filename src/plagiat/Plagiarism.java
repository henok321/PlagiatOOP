package plagiat;

import plagiat.filter.CharacterFilter;
import plagiat.filter.IrrelevantWordsFilter;
import plagiat.filter.WordSeparator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Plagiarism {

    private Map<String, Integer> map;
    private File file;

    public Plagiarism(File file) throws IOException {
        this.file = file;
        this.map = new HashMap<>();
        init();
    }

    private void init() throws IOException {

        BufferedReader inputReader = new BufferedReader(new FileReader(file));
        FilterReader filterChain = new IrrelevantWordsFilter(new WordSeparator(new CharacterFilter(inputReader)));

        char[] buffer = new char[256];
        int c = -1;

        while ((c = filterChain.read(buffer)) != -1) {
            if (c != 0) {
                String s = new String(buffer, 0, c);
                map.compute(s, (k, v) -> (v == null) ? 1 : ++v);
            }
        }
    }

    public int checksum() {
        return map.keySet().stream().mapToInt(s -> digitSum(s) * map.get(s)).sum();
    }

    private int digitSum(String s) {
        return s.chars().sum();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
