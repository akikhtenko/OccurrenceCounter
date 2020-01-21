package com.creditsuisse;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class RandomData {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static Random RND = new Random();

    public static CounterInput randomInput(int size) {
        Map<Character, Integer> occurrences = Stream.generate(() -> LETTERS.charAt(RND.nextInt(LETTERS.length())))
                .limit(size)
                .collect(groupingBy(identity(), reducing(0, e -> 1, Integer::sum)));

        return new CounterInput(inputFromOccurrences(occurrences), occurrences);
    }

    private static String inputFromOccurrences(Map<Character, Integer> occurrences) {
        return occurrences.entrySet().stream()
                .flatMap(e -> Stream.generate(e::getKey).limit(e.getValue())).collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    public static class CounterInput {
        public final CharSequence sequence;
        public final Map<Character, Integer> occurrences;

        public CounterInput(CharSequence sequence, Map<Character, Integer> occurrences) {
            this.sequence = sequence;
            this.occurrences = occurrences;
        }
    }
}
