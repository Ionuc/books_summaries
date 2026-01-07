.. _java11_nest_based_access:

Nest Based Access Control
=========================

    - java 11 introduces the notion of nestmates and the associated access rules within the JVM.
    - a nest of classes in Java implies both the outer/main class and all its nested classes:



    .. code-block:: python
           :linenos:

            assertThat(MainClass.class.isNestmateOf(MainClass.NestedClass.class)).isTrue();


    - nested classes are linked to the NestMembers attribute, while the outer class is linked to the NestHost attribute:


    .. code-block:: python
           :linenos:

            assertThat(MainClass.NestedClass.class.getNestHost()).isEqualTo(MainClass.class);


    - JVM access rules allow access to private members between nestmates; however, in previous Java versions, the reflection API 
      denied the same access.
    - java 11 fixes this issue and provides means to query the new class file attributes using the reflection API:


    .. code-block:: python
           :linenos:

            Set<String> nestedMembers = Arrays.stream(MainClass.NestedClass.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toSet());
            assertThat(nestedMembers).contains(MainClass.class.getName(), MainClass.NestedClass.class.getName());


:ref:`Go Back <java11-label>`.