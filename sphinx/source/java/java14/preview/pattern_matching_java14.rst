.. _java14_pattern_matching:

Pattern Matching
================

    - it was introduced in Java 13
    - text blocks now have 2 new escape sequances:
        - \: to indicate the end of the line, so that a new line character is not introduced
        - \s: to indicate a single space

    .. code-block:: python
           :linenos:

            // Old way
            String multiline = "A quick brown fox jumps over a lazy dog; the lazy dog howls loudly.";

            // New way
            String multiline = """
                A quick brown fox jumps over a lazy dog; \
                the lazy dog howls loudly.""";


    - it does not add a new line after dog;.

:ref:`Go Back <java14-features-caried-label>`.