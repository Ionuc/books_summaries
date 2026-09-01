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


Java 9 improvements
-------------------
- compact Strings
    - aims to minimize the memory usage of String objects
    - prior to JAva 8, String used a char[] to stire characters, which could be inefficient for Strings with limited character set
    - Compact String optimizes memory for string primarily composed of Latin-1 characters (ASCII range 0-127)
    - there are 2 representations:
        - Latin-1: 
            - Strings with only Latin-1 characters use a more memory-efficient byte[] representation
            - covers the first 256 Unicode code points
            - 1 byte per character
        - UTF-16:
            - String with non-Latin-1 or supplementary characters maintain the existing UTF-16 representation using char
            - 2 or 4 bytes per character
    - the runtime dynamically selects the represnetation based on the actual content of the String
    - is backward compabitility with older version
    - is enabled by default
    - how is working
        - there is only byte[] field together with compactString of type boolean
        - for Latin-1 representation, byte[] holds directly byte values
        - there are 2 coding schemes:
            - Latin-1 coding scheme: direct yte representation of characters in the latin-1 character set
            - UTF-16 coding scheme: used for non-Latin-1 characters or suplementary charactes


Java 11 improvements
--------------------
- new methods were added to String: iBlank(), lines(), strip(), stripLeading(), stipTrailing() and repeat()
    - repeat()
        - create a new String message by repeating the argument by the specified number
        - in case count is 0, then empty string is return


    .. code-block:: python
           :linenos:


    - isBlank():
        - checks if String is empty or contains only whitespace characters
        - comparing to isEmpty(), isEmpty() checks only the size of string

    .. code-block:: python
           :linenos:

        String emptyString = "";
        String whitespaceString = "   ";
        String nonEmptyString = "Java";

        System.out.println(emptyString.isBlank()); // Output: true
        System.out.println(whitespaceString.isBlank()); // Output: true
        System.out.println(nonEmptyString.isBlank()); // Output: false
        System.out.println(whitespaceString.isEmpty()); // Output: false


    - strip()
       - remove leading and trailing spaces
       - is unicode whitespace aware
       - comparing with trim(), trim() will remove leading and trailing ASCII spaces (code with Unicode value less or equal to U20)

    - stripLeading()
        - remove only leading whitespaces, including Unicode chars
    - stripTrailing()
        - remove only trailing whitespaces, including Unicode chars


    .. code-block:: python
           :linenos:

        String stringWithSpaces = "   Java 11   ";
        String strippedString = stringWithSpaces.strip();
        System.out.println(strippedString); // Output: Java 11
        
        String stringWithNonBreakingSpace = "\u2000 abc \u2000";
        String strippedString2 = stringWithNonBreakingSpace.strip();
        String trimmedString = stringWithNonBreakingSpace.trim();

        System.out.println("Original String: '" + stringWithNonBreakingSpace + "'");    => "  abc  "
        System.out.println("Stripped String: '" + strippedString2 + "'");               => "abc"
        System.out.println("Trimmed String: '" + trimmedString + "'");                  => "  abc  "


    - lines()
        - returns a stream of lines from the original String, breking it at line terminator


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
    - how is work:
        - step 1: the orignal string is split into lines
        - step 2: each line is adjusted based on the integer argument
            - if argument is greter than 0, then spaces are added at the beginning of each line
            - if argument is lower then 0, whitespaces are removed from the beginning of each line
        - step 3: resulting lines are concatenated and returned as a new String


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

Java 15 improvements
--------------------
- new methods were added to String: 
    - formatted()
        - is an enhancement to the existing format() method
        - you can inject direct values in the corresponding template

    .. code-block:: python
           :linenos:

        System.out.println("***formatted():");
        String formattedString = "Hello %s! Today is %s.".formatted("User", "Monday");
        System.out.println(formattedString);


    - stripIndent()
        - removes whitespaces from beginning and ending

    .. code-block:: python
           :linenos:


        System.out.println("***stripIndent():");
        String indentedString = "   This is a string with indentation   ";
        String strippedString = indentedString.stripIndent();
        System.out.println(indentedString);
        System.out.println(strippedString);


    - translateExcapes()
        - is used to handle escape sequences in a string
        - it interprets escape sequences within the string and replaces them with their actual characters


    .. code-block:: python
           :linenos:

        System.out.println("***translateEscapes():");
        String escapedString = "This is a string with \\n new line.";
        String translatedString = escapedString.translateEscapes();
        System.out.println(escapedString);      // will print "this is a string with \n new line"
        System.out.println(translatedString);   // \n is converted into new line separator

:ref:`Go Back <java-development-data-structures-string-label>`.
