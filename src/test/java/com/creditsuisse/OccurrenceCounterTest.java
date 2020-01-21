package com.creditsuisse;

import com.creditsuisse.RandomData.CounterInput;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.creditsuisse.RandomData.randomInput;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class OccurrenceCounterTest {
    @Parameterized.Parameter(0)
    public OccurrenceCounter counter;


    @Parameterized.Parameters(name = "with {0}")
    public static Collection<Object[]> counters() {
        return Arrays.asList(new Object[][]{{new TrivialOccurrenceCounter()}, {new BinarySearchOccurrenceCounter()}});
    }

    @Test
    public void should_report_no_occurrences_on_null_input() {
        assertThat(counter.countOccurrences(null), is(emptyMap()));
    }

    @Test
    public void should_report_no_occurrences_on_empty_input() {
        assertThat(counter.countOccurrences(""), is(emptyMap()));
    }

    @Test
    public void should_report_one_occurrence_on_single_char_input() {
        assertThat(counter.countOccurrences("a"), is(singletonMap('a', 1)));
    }

    @Test
    public void should_report_one_occurrence_per_input_char() {
        assertThat(counter.countOccurrences("abc"), is(ImmutableMap.of(
                'a', 1,
                'b', 1,
                'c', 1)));
    }

    @Test
    public void should_report_multiple_occurrences_on_complex_input() {
        assertThat(counter.countOccurrences("aacccccgeeeeeeeoo"), is(ImmutableMap.of(
                'a', 2,
                'c', 5,
                'g', 1,
                'e', 7,
                'o', 2)));
    }

    @Test
    public void should_report_occurrences_on_random_input() {
        CounterInput input = randomInput(1000);
        assertThat(counter.countOccurrences(input.sequence), is(input.occurrences));
    }
}