.. _java-development-number-random-generator-label:

Random Generator
================
- random number can e generated using old java.util.Random, SplittableRandom and SecureRandom classes

Java 17 improvement
-------------------
- Make it easier to use various PRNG algorithms interchangeably in applications.
- Better support stream-based programming by providing streams of pseudorandom number generators (PRNG) objects.
- Eliminate code duplication in existing PRNG classes.
- Carefully preserve existing behavior of class java.util.Random.
- it’s easier to use different algorithms interchangeably, and it also offers better support for stream-based programming:


- new interfaces were introduced:
    - RandomGenerator => the base interface with methods: ints, longs, doubles, nextBoolean, nextInt, nextLong, nextDouble, and nextFloat
    - SplittableRandomGenerator:
        - extends RandomGenerator
        - Splittability allows the user to spawn a new RandomGenerator from an existing RandomGenerator that will generally produce statistically independent results.
        - provides split() & splits()
    - JumpableRandomGenerator:
        - extends RandomGenerator
        - Jumpability allows a user to jump ahead a moderate number of draws.
        - provides jump() & jumps()
    - LeapableRandomGenerator:
        - extends RandomGenerator
        - Leapability allows a user to jump ahead a large number of draws.
        - provides leap() & leaps()
    - ArbitrarilyJumpableRandomGenerator:
        - extends LeapableRandomGenerator
        - provides additional variations of jump and jumps that allow an arbitrary jump distance to be specified

    .. code-block:: python
           :linenos:

            public IntStream getPseudoInts(String algorithm, int streamSize) {
                // returns an IntStream with size @streamSize of random numbers generated using the @algorithm
                // where the lower bound is 0 and the upper is 100 (exclusive)
                return RandomGeneratorFactory.of(algorithm)
                        .create()
                        .ints(streamSize, 0,100);
            }


- Legacy random classes, such as java.util.Random, SplittableRandom and SecureRandom now extend the new RandomGenerator interface


:ref:`Go Back <java-development-number-label>`.