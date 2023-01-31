.. _java10_unmodifiable_collections:

Unmodifiable collections
=========================

copyOf()
--------

    - List, Map & Set have a new method : copyOf() which returnes unmodifiable copy of the given collection


    - untile Java 9, the type of the variable must be explcitily set, else the compiler will not validate the code

    .. code-block:: python
           :linenos:

           @Test(expected = UnsupportedOperationException.class)
           public void whenModifyCopyOfList_thenThrowsException() {
              List<Integer> copyList = List.copyOf(someIntList);
              copyList.add(4);
           }

toUnmodifiable()
-----------------

    - java.util.stream.Collectors has a new method to collect a Stream into an unmodifiable collection

    .. code-block:: python
           :linenos:

           @Test(expected = UnsupportedOperationException.class)
           public void whenModifyToUnmodifiableList_thenThrowsException() {
              List<Integer> evenList = someIntList.stream()
                    .filter(i -> i % 2 == 0)
                    .collect(Collectors.toUnmodifiableList());
              evenList.add(4);
           }



:ref:`Go Back <java10-label>`.