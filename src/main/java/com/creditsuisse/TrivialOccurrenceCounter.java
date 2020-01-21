package com.creditsuisse;

import java.util.HashMap;
import java.util.Map;

public class TrivialOccurrenceCounter implements OccurrenceCounter {
    @Override
    public Map<Character, Integer> countOccurrences(CharSequence input) {
        Map<Character, Integer> occurrences = new HashMap<>();
        if (input == null || input.length() == 0) {
            return occurrences;
        }

        int currentCharStartPos = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != input.charAt(currentCharStartPos)) {
                occurrences.put(input.charAt(currentCharStartPos), i - currentCharStartPos);
                currentCharStartPos = i;
            }
        }
        occurrences.put(input.charAt(currentCharStartPos), input.length() - currentCharStartPos);

        return occurrences;
    }
}
