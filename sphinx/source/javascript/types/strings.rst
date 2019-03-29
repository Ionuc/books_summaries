.. _javascript-strings-label:

String
======
    - Is a primitive type representing an ordered set of characters
    - in JavaScript, the textual data is stored as strings
    - there is no separate type for single character
    - the internal format for strings is always UTF-16, it is not tied to the page encoding

    .. code-block:: python
        :linenos:

        let single = 'single-quoted';
        let double = "double-quoted";
        let backticks = `backticks`;

    - single and double quetes are esentially the same
    - backticks allow us to embed any expression into the stirng, including function calls

    .. code-block:: python
        :linenos:

        function sum(a, b) {
          return a + b;
        }

        alert(`1 + 2 = ${sum(1, 2)}.`); // 1 + 2 = 3.

Special characters
------------------
    - special characters are:
        - \b  -> Backspace
        - \f  -> Form feed
        - \n  -> New line
        - \r  -> Carriage return
        - \t  -> Tab
        - \uNNNN -> a unicode symbol with the hex code NNNN
        - \u{NNNNNNNN} -> some rare characters are encoded with two unicode symbols, taking 4 bytes

String length
-------------
    - the length property has the string length
    - as you can see, it is a property and not a method

    .. code-block:: python
        :linenos:

        alert( `My\n`.length ); // 3

    - the previous example has length 3 because special chracters are considered as single characters

Accessing characters
--------------------
    - you can access a character by using square brackets, or call method str.charAt(pos)

    .. code-block:: python
        :linenos:

        let str = `Hello`;

        // the first character
        alert( str[0] ); // H
        alert( str.charAt(0) ); // H

        // the last character
        alert( str[str.length - 1] ); // o

    - differences between square brackets and charAt() is that if no character found, square brackets return undefined, while 
      charAt() returns empty string

    - we can also iterate over characters using for..of:

    .. code-block:: python
        :linenos:

        for (let char of "Hello") {
            alert(char); // H,e,l,l,o (char becomes "H", then "e", then "l" etc)
        }

Strings are immutable
---------------------
    - strings can't be changed in JavaScript
    - if you want to change a string, then you should create a new one

    .. code-block:: python
        :linenos:

        let str = 'Hi';

        str[0] = 'h'; // error
        alert( str[0] ); // doesn't work

Searching for substring
-----------------------
    - there are multiple methods to do that:
        - str.indexOf(substr, pos):
            - it looks for the substr in str starting from the given position pos
            - returns the position where the match was found or -1 if nothing can be found

            .. code-block:: python
                :linenos:

                let str = 'Widget with id';

                alert( str.indexOf('id', 2) ) // 12

        - str.lastIndexOf(substr, position):
            - it starts searching from the end, doing the same as indexOf() method
        - str.includes(substr, pos):
            - returns true of false depending on wheter str contains substr within
        - str.startWith(str)
        - str.endsWith(str)

Getting a substring
-------------------
    - there are 3 methods to get a substring: substring, substr, slice
    - str.slice(start [, end]):
        - returns the part of the string from start to (but not including) end

        .. code-block:: python
            :linenos:

            let str = "stringify";
            alert( str.slice(0, 5) ); // 'strin', the substring from 0 to 5 (not including 5)
            alert( str.slice(0, 1) ); // 's', from 0 to 1, but not including 1, so only character at 0

        - if there is not second argument, the slice goes till the end of the string
        - negative values for start/end are also possible. They mean the position is counted from the string end

        .. code-block:: python
            :linenos:

            let str = "stringify";

            // start at the 4th position from the right, end at the 1st from the right
            alert( str.slice(-4, -1) ); // gif

    - str.substring(start [, end]):
        - returns the part of the string between start and end
        - this is almost the same as slice, but it alows start to be greater than end
        - negative values are not supported, they are threated as 0

        .. code-block:: python
            :linenos:

            let str = "stringify";

            // these are same for substring
            alert( str.substring(2, 6) ); // "ring"
            alert( str.substring(6, 2) ); // "ring"

            // ...but not for slice:
            alert( str.slice(2, 6) ); // "ring" (the same)
            alert( str.slice(6, 2) ); // "" (an empty string)

    - str.substr(start [, length]):
        - returns the part of the string from start, with the given length

        .. code-block:: python
            :linenos:

            let str1 = "stringify";
            alert( str1.substr(2, 4) ); // ring, from the 2nd position get 4 characters
            let str2 = "stringify";
            alert( str2.substr(-4, 2) ); // gi, from the 4th position get 2 characters

Comparing strings
-----------------
    - strings are compared character by character in alphabetical order:
        - lowercase letter is always greater than the uppercase
        - letters with diacritical marks are out of order

    .. code-block:: python
        :linenos:

        alert( 'a' > 'Z' ); // true
        alert( 'Österreich' > 'Zealand' ); // true

    - as strings are encoded using UTF-16, each character has a numeric code
    - you can access the numeric code or create a character by its numeric code

    .. code-block:: python
        :linenos:

        // different case letters have different codes
        alert( "z".codePointAt(0) ); // 122
        alert( "Z".codePointAt(0) ); // 
        alert( String.fromCodePoint(90) ); // Z
        // 90 is 5a in hexadecimal system
        alert( '\u005a' ); // Z

    - because alphabets are different for different languages, the same looking letter may be located differently in different
      alphabets

    - so the browser needs to know the language to compare
    - all browsers support internationalization standard ECMA 402 which provides a method str.localeCompare(str2) which:
        - returns 1 if str is greater than str2 according to the language rules.
        - returns -1 if str is less than str2.
        - returns 0 if they are equal.

Surrogate pairs
---------------
    - most symbols have a 2-byte code
    - but 2 bytes only allow 65536 combinations which is not enough
    - so rare symbols are encoded with a pair of 2-byte characters called "a surrogate pair"
    - the lenght of such symbols is 2

    .. code-block:: python
        :linenos:

        alert( '𝒳'.length ); // 2, MATHEMATICAL SCRIPT CAPITAL X
        alert( '😂'.length ); // 2, FACE WITH TEARS OF JOY
        alert( '𩷶'.length ); // 2, a rare chinese hieroglyph

    - String.fromCodePoint and str.codePointAt are few rar methods that deal with surrogate pairs right
    - technically, surrogate pairs are also detectable by their codes:
        - if a character has the code in the interval of 0xd800..0xdbff, is the first part of the surrogate
        - the next character (second part) must have the code in the interval 0xdc00..0xdfff

Diacritics marks and normalization
----------------------------------
    - in manny languages there are symbols tha are composed of the base character with a mark above/under it
    - to support arbitrary composition, UTF-16 allows us to use several unicode characteres. The base character
      and one or many "mark" character that "decorate" it

    .. code-block:: python
        :linenos:

        alert( 'S\u0307' ); // Ṡ
        alert( 'S\u0307\u0323' ); // Ṩ

    - this provides a great flexibility, but also an interesting problem: 2 characters may visually look the same, but be
      represented with different unicode compositions:

    .. code-block:: python
        :linenos:

        alert( 'S\u0307\u0323' ); // Ṩ, S + dot above + dot below
        alert( 'S\u0323\u0307' ); // Ṩ, S + dot below + dot above

        alert( 'S\u0307\u0323' == 'S\u0323\u0307' ); // false

    - to solve this, there is a unicode normalization algorithm that brings each string to the single "normal" form
    - it is implemented by str.normalize()

    .. code-block:: python
        :linenos:

        alert( "S\u0307\u0323".normalize() == "S\u0323\u0307".normalize() ); // true

:ref:`Go Back <javascript-types-label>`.