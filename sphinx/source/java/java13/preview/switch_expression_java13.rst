.. _java13_switch_expression:

Switch Expressions (Preview)
============================

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



:ref:`Go Back <java13-preview-label>`.