package com.creditsuisse;

import java.util.Map;

public interface OccurrenceCounter {
    Map<Character, Integer> countOccurrences(CharSequence input);
}
