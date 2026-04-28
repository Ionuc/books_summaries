.. _java-development-data-structures-string-string-label:

String
======

Methods
-------
- lenght() => return the size of the string
- contains(<value>) => return true of it will contains the sequance of characters
- isEmpty() => return true in case of empty string
- toUpperCase() => returns a new String with all chars to upper case
- toLowerCase() => returns a new String with all chars to lower case
- startsWith(<value>) => return true if the String starts with the sequence of characters
- endsWith(<value>) => return true if the String ends with the sequence of characters
- replace(<value1>, <value2>) => is replacing all <values1> chars with <values2> chars 
- trim():
    - remove leading and trailing spaces
- substring(<startIndex>, <endIndex>)
    - retrieve the substring including startIndex, but excluding <endIndex>
- getBytes()
    - return the array of bytes
- toCharArray()
    - return the array of characters
- charAt(<index>)
    - return the character for the given index
- split(<regularExpression>)
    - return to an array of String which are between each position of <regularExpression> founded in the String 
- intern():
    - put the value in the String pool and return the corresponding reference

Comparing Strings
-----------------
- comparing using "=="
    -  result in true in case the Strings are created as literals:
        - because of the pool of Strings


    .. code-block:: python
           :linenos:

            String s1 = "home";
            String s2 = "home";

            System.out.println(s1 == s2); // true


    -  at least on of them is created with Constructor, and intern() method is not used "==" will return false


    .. code-block:: python
           :linenos:

            String s1 = "home";
            String s2 = new String(home);

            System.out.println(s1 == s2); // false

    - at least on of them is created with Constructr and intern() method is used, will return true



    .. code-block:: python
           :linenos:

            String s1 = "home";
            String s2 = new String(home);

            System.out.println(s1 == s2.intern()); // true


- comparing using "equals":
    - will compare the value inside it


    .. code-block:: python
           :linenos:

            String s1 = "home";
            String s2 = new String(home);

            System.out.println(s1.equals(s2)); // true

- comparing using "equalsIgnoringCase":
    - will compare the value inside it with ignoring case


    .. code-block:: python
           :linenos:

            String s1 = "home";
            String s2 = "Home"

            System.out.println(s1.equals(s2)); // false
            System.out.println(s1.equalsIgnoringCase(s2)); // true


Escape sequence
---------------
- in java, a backslash "\" combine with another character is called escape sequence
- escape sequence:
    - \t - tab.
    - \b - backspace (a step backward in the text or deletion of a single character).
    - \n - new line.
    - \r - carriage return. ()
    - \f - form feed.
    - \' single quote.
    - \" double quote.
    - \\ backslash.


    .. code-block:: python
           :linenos:

            System.out.println("My favourite book is \""Test\" by Ionut"); // My favourite book is "Test" by Ionut



Java 11 improvements
--------------------
- new methods were added to String: iBlank(), lines(), strip(), stipLeading(), stipTrailing() and repeat()
    - strip()
       - remove leading and trailing spaces
       - is unicode whitespace aware

    .. code-block:: python
           :linenos:

            String multilineString = "Baeldung helps \n \n developers \n explore Java.";
            List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
            assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");


Java 12 improvements
--------------------
- new methods were added to String: indent() & transform()

- String.indent
    - adjusts the indentation of each line based on the integer parameter.
    - if the parameter is greater than zero, new spaces will be inserted at the beginning of each line
    - if the parameter is less than zero, it removes spaces from the begging of each line. If a given line does not contain sufficient white space, then all leading white space characters are removed

    .. code-block:: python
           :linenos:

            String text = "Hello Baeldung!\nThis is Java 12 article.";

            text = text.indent(4);
            System.out.println(text);

            text = text.indent(-10);
            System.out.println(text);


    - Output:


    .. code-block:: python
           :linenos:

                Hello Baeldung!
                This is Java 12 article.

            Hello Baeldung!
            This is Java 12 article.


- String.transform
    - It accepts a single argument function as a parameter that will be applied to the string.


    .. code-block:: python
           :linenos:

            @Test
            public void givenString_thenRevertValue() {
                String text = "Baeldung";
                String transformed = text.transform(value ->
                    new StringBuilder(value).reverse().toString()
                );

                assertEquals("gnudleaB", transformed);
            }


:ref:`Go Back <java-development-data-structures-string-label>`.
