package com.epam.rd.autotasks;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Words {

    static class ValueComparator implements Comparator<String> {
        Map<String, Integer> values;

        public ValueComparator(Map<String, Integer> values) {
            this.values = values;
        }


        public int compare(String a, String b) {
            if (values.get(a) >= values.get(b)) {
                int compare = values.get(b).compareTo(values.get(a));
                if (compare == 0) {
                    return a.compareTo(b); 
                } else {
                    return compare;
                }

            } else {
                return 1;
            }

        }


    }


    private static final String WORD_REGEX = "([a-zA-Zа-яА-Яáíóýêéúëèïüö0-9]+)";


    public String countWords(List<String> lines) {
        Pattern pattern = Pattern.compile(WORD_REGEX);


        Map<String, Integer> entries = new HashMap<>();
        for (String s : lines) {
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String word = matcher.group(1);
                word = word.toLowerCase();
                if (word.length() >= 4 && !Character.isUpperCase(word.charAt(0))) {

                    Integer wordCount = entries.get(word);
                    wordCount = wordCount == null ? 0 : wordCount;
                    entries.put(word, wordCount + 1);

                }

            }
        }

        ValueComparator valueComparator = new ValueComparator(entries);


        TreeMap<String, Integer> sortedWords = new TreeMap<>(valueComparator);
        sortedWords.putAll(entries);

        StringBuilder sb = new StringBuilder();


        Set<Map.Entry<String, Integer>> valuesSet = sortedWords.entrySet();
        for (Map.Entry<String, Integer> entry : valuesSet) {

            int value = entry.getValue();
            String key = entry.getKey();
            if (value >= 10) {

                sb.append(key).append(" - ").append(value).append("\n");


            }

        }


        String result = sb.toString();
        result = result.substring(0, result.length() - 1);

        return result;
    }
}

