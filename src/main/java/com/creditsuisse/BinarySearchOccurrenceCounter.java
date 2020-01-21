package com.creditsuisse;

import java.util.HashMap;
import java.util.Map;

public class BinarySearchOccurrenceCounter implements OccurrenceCounter {
    @Override
    public Map<Character, Integer> countOccurrences(CharSequence input) {
        Map<Character, Integer> occurrences = new HashMap<>();
        if (input == null) {
            return occurrences;
        }

        int currentCharPos = 0;
        while (currentCharPos < input.length()) {
            int nextCharPos = nextCharPosition(input, currentCharPos);
            occurrences.put(input.charAt(currentCharPos), nextCharPos - currentCharPos);
            currentCharPos = nextCharPos;
        }
        return occurrences;
    }

    private int nextCharPosition(CharSequence input, int currentCharPos) {
        if (input.charAt(currentCharPos) == input.charAt(input.length() - 1)) {
            return input.length();
        }
        return binSearchNextCharPosition(input, currentCharPos, input.length() - 1);
    }

    private int binSearchNextCharPosition(CharSequence input, int leftPos, int rightPos) {
        if (rightPos - leftPos == 1) {
            return rightPos;
        }
        int midPos = leftPos + (rightPos - leftPos) / 2;

        return input.charAt(leftPos) == input.charAt(midPos) ?
                binSearchNextCharPosition(input, midPos, rightPos) : binSearchNextCharPosition(input, leftPos, midPos);
    }
}
