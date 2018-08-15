.. _string-joiner:

String Joiner
=============
    - simplify composing a string comprised of a sequence of values
    - it can be passed inside constructor the separator, start/end strings and then create the String
    - you can chain methods

    .. code-block:: python
       :linenos:

       StringJoiner sj = new StringJoiner(", ", "{", "}");
       sj.add("alpha");
         .add("beta");
         .add("gama");
       sj.toString() => "{alpha, betha, gama}"

    - it handle also edge cases like:
        - adding only one value
        - adding no values
    - can specify a special string for empty case. String joiner consider empty only if .add() method was not called

:ref:`Go Back <java-string-label>`.