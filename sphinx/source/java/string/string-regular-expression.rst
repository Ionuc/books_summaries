.. _string-regular-expression:

String Regular Expression
=========================
    - String provides some methods which are supporting regular expression like:
        - replaceFirst()
            - returns new updating String based on existing String
            - pattern identifies which parts to change
        - replaceAll()
            - returns new updating String based on existing String
            - pattern identifies which part to change
        - split()
            - Splits String into an array
            - Pattern is the separator between values
        - match()
            - identifies if String matches the pattern

    .. code-block:: python
       :linenos:

       StringJoiner sj = new StringJoiner(", ", "{", "}");
       sj.add("alpha");
         .add("beta");
         .add("gama");
       sj.toString() => "{alpha, betha, gama}"

Regular expression considerations
---------------------------------
    - compilation of a regular expression can be very processing intensive
    - String methods repeat compilation on every use
    - because of this there is Pattern & Matcher class
    - Pattern:
        - compiles a regular expression
        - Factory for MAtcher class instances
    - Matcher class:
        - Applies compiled expression to a String

:ref:`Go Back <java-string-label>`.