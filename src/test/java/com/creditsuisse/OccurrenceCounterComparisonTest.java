package com.creditsuisse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.creditsuisse.RandomData.randomInput;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class OccurrenceCounterComparisonTest {
    @Parameterized.Parameter(0)
    public Integer inputSize;

    private BinarySearchOccurrenceCounter binarySearchCounter = new BinarySearchOccurrenceCounter();
    private TrivialOccurrenceCounter trivialCounter = new TrivialOccurrenceCounter();

    @Parameterized.Parameters(name = "with input size of {0}")
    public static Collection<Object[]> inputSizes() {
        return Arrays.asList(new Object[][]{{1_000_000}, {10_000_000}, {100_000_000}});
    }

    @Test
    public void binary_search_counter_should_outperform_trivial_counter() {
        RandomData.CounterInput input = randomInput(inputSize);

        long startTime = System.nanoTime();
        trivialCounter.countOccurrences(input.sequence);
        long trivialCounterRunTime = System.nanoTime() - startTime;
        binarySearchCounter.countOccurrences(input.sequence);
        long binaryCounterStopTime = System.nanoTime() - startTime - trivialCounterRunTime;

        System.out.println(format("Trivial counter processed input of size %s in %s micros", inputSize, NANOSECONDS.toMicros(trivialCounterRunTime)));
        System.out.println(format("Binary search counter processed input of size %s in %s micros", inputSize, NANOSECONDS.toMicros(binaryCounterStopTime)));

        assertThat(trivialCounterRunTime, is(greaterThan(binaryCounterStopTime)));
    }
}
