package com.epam.rd.autotasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> entries = new HashMap<>();
        Stream<String> noPunctStream = lines.stream().map(line -> line.replaceAll("[:,!;.?'\"“—’)(\\-”‘/*]", " "));
        Stream<String> singleWhitespaceStream = noPunctStream.map(line -> line.replaceAll("\\s+", " "));
        Stream<String> wordsStream = singleWhitespaceStream.flatMap(line -> Stream.of(line.split(" ")));
        Stream<String> lowerCasedWordsStream = wordsStream.map(String::toLowerCase);
        Stream<String> fourPlusLengthWordsStream = lowerCasedWordsStream.filter(el -> el.length() >= 4);
        Map<String, Long> map = fourPlusLengthWordsStream.sorted().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        String result = map.entrySet().stream().
                filter(entry -> entry.getValue() >= 10).
                sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed().thenComparing(Map.Entry<String, Long>::getKey))
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return result;
    }
}
