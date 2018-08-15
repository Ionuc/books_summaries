.. _format-specifiers:

Format Specifiers
=================
    - focus is on describing the desired result and not how we construct to appear in that way
    - can control many aspects of appearance:
        - position
        - decimal places
        - representation
    - some method supporting format specifiers:
        - String.format
        - System.out.printf
        - Formatter.format

Concatenation vs Fromatting
---------------------------

    .. code-block:: python
       :linenos:

       int david =13, dawson = 11, dillon = 4, gordon = 2;
       String s1 = "My nephes are " + david + ", " + dawson + ", " + dillon + ", and " + gordon + " years old";
       String s2 = String.format("My nephews are %d, %d, %d, and %d years old", david, dawson, dillon, gordon);

    - It can also be used to format:

    .. code-block:: python
       :linenos:

       int david =13, dawson = 11, dillon = 4, gordon = 2;
       double avgDiff = ((david - dawson) + (dawson - dillon) + (dillown-gordon))/ 3.0d
       String s1 = "The average age between each is " + avgDiff + " years"; 
       // will print-> The average age between each is 3.6666666666665 years
       String s2 = String.format("The average age between each is %.1f years", avgDiff);
       // will print-> The average age between each is 3.7 years

        - adding only one value
        - adding no values
    - can specify a special string for empty case. String joiner consider empty only if .add() method was not called

Parts of Format Specifier
-------------------------
    - are containing:
        - format specifier start (mandatory) -> %
        - precision (optional) -> ex : ".1" -> number of decimal to display
        - width (optional) -> ex : ".1" -> minimum character to display
        - flags (optional) ->
        - argument index (optional) -> link one format specifier with any value from list of values
        - conversion (mandatory) -> ex : "f", "d"

Writing formatted content to a Stream
-------------------------------------
    - The class Formatter provides formatting capabilities
    - It writes content to any type that implements Appendable interface
        - StringBuilder
        - Writer -> we can write formatted string directly to any subclass of Writer class
    - automatically closes whatever it's wrapping long as that class implements the closable interface

    .. code-block:: python
       :linenos:

       void doWrite(int david, int dawson, int dillon, int gordon, double avgDiff) throws IOExceptions{
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("myFile.txt"));
            try(Formatter f = new Formatter(writer)){
               f.format("My nephews are %d, %d, %d, and %d years old", david, dawson, dillon, gordon);
               f.format("The average age between each is %.1f years", avgDiff);
            }
       }

:ref:`Go Back <java-string-label>`.