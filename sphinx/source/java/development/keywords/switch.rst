.. _java-development-keywords-switch-label:

Switch keyword
==============

Java 12 Improvement (preview)
-----------------------------
    - are more compact and readable
    - remove the need for break statements. The code execution will not fall through after the first match
    - we can assign a switch statement directly to the variable.
    - it’s also possible to execute code in switch expressions without returning any value:
    - old way of using switch:

    .. code-block:: python
           :linenos:

            DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
            String typeOfDay = "";
            switch (dayOfWeek) {
                case MONDAY:
                case TUESDAY:
                case WEDNESDAY:
                case THURSDAY:
                case FRIDAY:
                    typeOfDay = "Working Day";
                    break;
                case SATURDAY:
                case SUNDAY:
                    typeOfDay = "Day Off";
            }


    - new way of using switch:


    .. code-block:: python
           :linenos:

            typeOfDay = switch (dayOfWeek) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
                case SATURDAY, SUNDAY -> "Day Off";
            };


Java 13 Improvements
--------------------

 - using yield, we can now effectively return values from a switch expression

    .. code-block:: python
           :linenos:

            @Test
            @SuppressWarnings("preview")
            public void whenSwitchingOnOperationSquareMe_thenWillReturnSquare() {
                var me = 4;
                var operation = "squareMe";
                var result = switch (operation) {
                    case "doubleMe" -> {
                        yield me * 2;
                    }
                    case "squareMe" -> {
                        yield me * me;
                    }
                    default -> me;
                };

                assertEquals(16, result);
            }

Java 14 Improvements
--------------------
- switch expressions have been standardized so that they are part and parcel of the development kit.
- What this effectively means is that this feature can now be used in production code, and not just in the preview mode to be experimented with by developers.

Java 17 improvements
--------------------
- until Java 17, switch could be used only on few types: numeric types, enum types, and String
- now, it is possible to use switch also with pattern matching and Object classes:


    .. code-block:: python
           :linenos:

            static String formatterPatternSwitch(Object o) {
                return switch (o) {
                    case Integer i -> String.format("int %d", i);
                    case Long l    -> String.format("long %d", l);
                    case Double d  -> String.format("double %f", d);
                    case String s  -> String.format("String %s", s);
                    default        -> o.toString();
                };
            }

- also, null check is improved as a new switch clause:
    - to be backword compatible, the default label does not match a null selector.


    .. code-block:: python
           :linenos:

           // before Java 17:
           static void testFooBar(String s) {
                if (s == null) {
                    System.out.println("oops!");
                    return;
                }
                switch (s) {
                    case "Foo", "Bar" -> System.out.println("Great");
                    default           -> System.out.println("Ok");
                }
            }

            // after Java 17:
            static void testFooBar(String s) {
                switch (s) {
                    case null         -> System.out.println("Oops");
                    case "Foo", "Bar" -> System.out.println("Great");
                    default           -> System.out.println("Ok");
                }
            }


- you can use also condition with Objects:


    .. code-block:: python
           :linenos:

            static void testTriangle(Shape s) {
                switch (s) {
                    case Triangle t && (t.calculateArea() > 100) ->
                        System.out.println("Large triangle");
                    default ->
                        System.out.println("A shape, possibly a small triangle");
                }
            }


-  four major design issues
    1. Enhanced type checking
        - Selector expression typing:
            - new types are now allowed:


    .. code-block:: python
           :linenos:

            record Point(int i, int j) {}
            enum Color { RED, GREEN, BLUE; }

            static void typeTester(Object o) {
                switch (o) {
                    case null     -> System.out.println("null");
                    case String s -> System.out.println("String");
                    case Color c  -> System.out.println("Color with " + Color.values().length + " values");
                    case Point p  -> System.out.println("Record class: " + p.toString());
                    case int[] ia -> System.out.println("Array of ints of length" + ia.length);
                    default       -> System.out.println("Something else");
                }
            }


        - Dominance of pattern labels:
            - It is possible for the selector expression to match multiple labels in a switch block.
            - is a compile-time error if a pattern label in a switch block is dominated by an earlier pattern label in that switch block

    .. code-block:: python
           :linenos:

            static void error(Object o) {
                switch(o) {
                    case CharSequence cs ->
                        System.out.println("A sequence of length " + cs.length());
                    case String s ->    // Error - pattern is dominated by previous pattern
                        System.out.println("A string: " + s);
                    default -> {
                        break;
                    }
                }
            }

    2. Completeness of switch expressions and statements
        - A switch expression requires that all possible values of the selector expression are handled in the switch block
        - This maintains the property that successful evaluation of a switch expression will always yield a value
        - this rule apply also in conjucation with the type of the value from swtich(it takes into account if the class is primitive, Object, a specific class, or a sealed class)
    3. Scope of pattern variable declarations
    4. Dealing with null
        - null clause was introduced to no throgh anymore NullPointerException
        - in case of missing, the new behavior around null is as if the compiler automatically enriches the switch block with a case null whose body throws NullPointerException.

- 1. Enhanced type checking
    -


:ref:`Go Back <java-development-keywords-label>`.