#### Problem statement

To write an algorithm that would count the number of occurrences of each distinct character in a sorted character sequence.
Two solutions are sought - one that would do the calculation in O(N) time and another that would outperform the first solution.

#### Implementation details

The two solutions mentioned above are implementations of ```OccurrenceCounter``` interface.

The O(N) algorithm ```TrivialOccurrenceCounter``` iterates over the input sequence holding the starting position of each individual distinct char (```currentCharStartPos```) until it reach a char of a different value. At that point the current char occurrence is determined as ```currentCharStartPos - nextCharPos``` and is saved to the resultant map, ```currentCharStartPos``` is reset to the value of ```nextCharPos``` and the process is repeated until it reaches the end of the input sequence.

An improved algorithm ```BinarySearchOccurrenceCounter``` is as the name suggests based on a modified binary search algorithm. Starting at sequence head on each iteration it picks the first char of the current working range and attempts to find the position of the first occurrence of a char value that is different from the first char of the working range. It does so by means of a binary search. Once such position is determined, the number of occurrences of the first char of the working range is saved to the resultant map and the starting position of the working range is moved to the newly determined position of a next distinct char. The process repeats until it reaches the input sequence end.

The improved algorithm runs in M*log(N) time where M is the number of distinct sequence chars. Since M is a rather limited number given standard character sets the algorithm would be characterised as a log(N) algorithm given sufficiently large N which can be seen in test output below. The algorithm performs worse than the trivial one when N is small (reaches M or lower) but this can be mitigated by a hybrid algorithm that would select one implementation or the other based on input size and anticipated M.

#### Test results

```OccurrenceCounterComparisonTest``` can be used to see how the two algorithms compare as the input sequences size increases from 1M to 100M chars. A sample run on my laptop gives me this output:

  Trivial counter processed input of size 1000000 in 18152 micros
  
  Binary search counter processed input of size 1000000 in 141 micros
  
  Trivial counter processed input of size 10000000 in 73074 micros
  
  Binary search counter processed input of size 10000000 in 93 micros
  
  Trivial counter processed input of size 100000000 in 204931 micros
  
  Binary search counter processed input of size 100000000 in 78 micros