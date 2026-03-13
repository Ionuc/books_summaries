.. _java-development-datastructure-optional-label:

Optional
========
- it is introduced with Java 8
- The purpose of the class is to provide a type-level solution for representing optional values instead of null references.

Creating Optional
-----------------
- Optional.empty

    .. code-block:: python
            :linenos:

            @Test
            public void whenCreatesEmptyOptional_thenCorrect() {
                Optional<String> empty = Optional.empty();
                assertFalse(empty.isPresent());
            }


- Optional.of(<value>)
    - the value is expected to e a non NULL value.
    - in case value is null, a NullPointerException is thrown

    .. code-block:: python
            :linenos:

            @Test
            public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
                String name = "baeldung";
                Optional<String> opt = Optional.of(name);
                assertTrue(opt.isPresent());
            }

            @Test(expected = NullPointerException.class)
            public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
                String name = null;
                Optional.of(name);
            }

- Optional.ofNullable()
    - used to create an Optional from a value which can e NULL

    .. code-block:: python
            :linenos:

            @Test
            public void givenNonNull_whenCreatesNullable_thenCorrect() {
                String name = "baeldung";
                Optional<String> opt = Optional.ofNullable(name);
                assertTrue(opt.isPresent());
            }

            @Test
            public void givenNull_whenCreatesNullable_thenCorrect() {
                String name = null;
                Optional<String> opt = Optional.ofNullable(name);
                assertFalse(opt.isPresent());
            }


Checking Value Presence
-----------------------
- isPresent()
    - return true if the value from Optional is not NULL

    .. code-block:: python
            :linenos:

            @Test
            public void givenOptional_whenIsPresentWorks_thenCorrect() {
                Optional<String> opt = Optional.of("Baeldung");
                assertTrue(opt.isPresent());

                opt = Optional.ofNullable(null);
                assertFalse(opt.isPresent());
            }

Conditional Action With ifPresent()
-----------------------------------
- The ifPresent() method enables us to run some code on the wrapped value if it’s found to be non-null


    .. code-block:: python
            :linenos:

            // Before Optional
            if(name != null) {
                System.out.println(name.length());
            }

            // with Optional
            @Test
            public void givenOptional_whenIfPresentWorks_thenCorrect() {
                Optional<String> opt = Optional.of("baeldung");
                opt.ifPresent(name -> System.out.println(name.length()));
            }

Default Value
--------------
    - The orElse() method:
        - returns the wrapped value if it’s present, and its argument otherwise
        - the value must be already computed

    .. code-block:: python
            :linenos:

            @Test
            public void whenOrElseWorks_thenCorrect() {
                String nullName = null;
                String name = Optional.ofNullable(nullName).orElse("john");
                assertEquals("john", name);
            }


    - orElseGet() methods:
        - returns the wrapped value if it's present, and it takes a supplier functional interface, which is invoked and returns the value of the invocation
        - the functional interface is not invoked in case the value is present


    .. code-block:: python
            :linenos:

            @Test
            public void whenOrElseGetWorks_thenCorrect() {
                String nullName = null;
                String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
                assertEquals("john", name);
            }


    - difference between orElse() and orElseGet():
        - orElse() needs to compute the default value even if the Optional has a non NULL value, while the orElseGet() will not compute value if the Optional has a non NULL value
        - this default value can be constly in case a request is made to compute it


Exceptions With orElseThrow
---------------------------
- orElseThrow():
    - Instead of returning a default value when the wrapped value is not present, it throws an exception:


    .. code-block:: python
            :linenos:

            @Test(expected = IllegalArgumentException.class)
            public void whenOrElseThrowWorks_thenCorrect() {
                String nullName = null;
                String name = Optional.ofNullable(nullName).orElseThrow(
                  IllegalArgumentException::new);
            }

Returning value
---------------
- The final approach for retrieving the wrapped value is the get() method:

    .. code-block:: python
            :linenos:

            @Test
            public void givenOptional_whenGetsValue_thenCorrect() {
                Optional<String> opt = Optional.of("baeldung");
                String name = opt.get();
                assertEquals("baeldung", name);
            }


-  get() can only return a value if the wrapped object is not null; otherwise, it throws a no such element exception


    .. code-block:: python
            :linenos:

            @Test(expected = NoSuchElementException.class)
            public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
                Optional<String> opt = Optional.ofNullable(null);
                String name = opt.get();
            }


Condition return with filter
----------------------------
- We can run an inline test on our wrapped value with the filter method. It takes a predicate as an argument and returns an Optional object
- If the wrapped value passes testing by the predicate, then the Optional is returned as-is.However, if the predicate returns false, then it will return an empty Optional:


    .. code-block:: python
            :linenos:

            @Test
            public void whenOptionalFilterWorks_thenCorrect() {
                Integer year = 2016;
                Optional<Integer> yearOptional = Optional.of(year);
                boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
                assertTrue(is2016);
                boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
                assertFalse(is2017);
            }

Transforming Value
------------------
- with map()
    - In the previous section, we looked at how to reject or accept a value based on a filter.
    - We can use a similar syntax to transform the Optional value with the map() method
    - this operation does not modify the original value.


    .. code-block:: python
            :linenos:

            @Test
            public void givenOptional_whenMapWorks_thenCorrect() {
                List<String> companyNames = Arrays.asList(
                  "paypal", "oracle", "", "microsoft", "", "apple");
                Optional<List<String>> listOptional = Optional.of(companyNames);

                int size = listOptional
                  .map(List::size)
                  .orElse(0);
                assertEquals(6, size);
            }


- with flatMap()
    - Just like the map() method, we also have the flatMap() method as an alternative for transforming values
    - The difference is that map transforms values only when they are unwrapped whereas flatMap takes a wrapped value and unwraps it before transforming it.

    .. code-block:: python
            :linenos:

            @Test
            public class Person {
                private String name;
                    public Optional<String> getName() {
                    return Optional.ofNullable(name);
                }
                // normal constructors and setters
            }

            @Test
            public void givenOptional_whenFlatMapWorks_thenCorrect2() {
                Person person = new Person("john", 26);
                Optional<Person> personOptional = Optional.of(person);

                // with map()
                Optional<Optional<String>> nameOptionalWrapper  
                  = personOptional.map(Person::getName);
                Optional<String> nameOptional  
                  = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
                String name1 = nameOptional.orElse("");
                assertEquals("john", name1);

                // with flatMap()
                String name = personOptional
                    .flatMap(Person::getName)
                    .orElse("");
                assertEquals("john", name);
            }


Chaining Optionals
------------------
- there is no method as orElseOptional() in Java 8
- solution 1 is to use a Stream<Optional<String>>:
    - The downside of this approach is that all of our get methods are always executed, regardless of where a non-empty Optional appears in the Stream.


    .. code-block:: python
            :linenos:

            @Test
            public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
                Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .findFirst();
                
                assertEquals(getHello(), found);
            }

- solution 2 is to use a Stream.<Supplier<Optional<String>>>:
    - The downside of this approach is that all of our get methods are always executed, regardless of where a non-empty Optional appears in the Stream.



    .. code-block:: python
            :linenos:

            @Test
            public void givenTwoOptionalsReturnedByOneArgMethod_whenChaining_thenFirstNonEmptyIsReturned() {
                Optional<String> found = Stream.<Supplier<Optional<String>>>of(
                  () -> createOptional("empty"),
                  () -> createOptional("hello")
                )
                  .map(Supplier::get)
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .findFirst();

                assertEquals(createOptional("hello"), found);
            }

Misuse of Optionals
-------------------
- passing an Optional parameter to a method:
    - The intent of Java when releasing Optional was to use it as a return type, thus indicating that a method could return an empty value.
    - solution: create two overloaded methods, one with the parameter having value, another without the parameter


Java 9 Improvements   
--------------------
- new methods to the Optional API:
    - or() method for providing a supplier that creates an alternative Optional

    .. code-block:: python
            :linenos:

            @Test
            public void givenOptional_whenEmpty_thenShouldTakeAValueFromOr() {
                // given
                String defaultString = "default";
                Optional<String> value = Optional.empty();
                Optional<String> defaultValue = Optional.of(defaultString);

                // when
                Optional<String> result1 = value.or(() -> defaultValue);

                // then
                assertThat(result.get()).isEqualTo(defaultString);
            }

    - ifPresentOrElse() method that allows executing an action if the Optional is present or another action if not

    .. code-block:: python
            :linenos:

            @Test
            public void givenOptional_whenNotPresent_thenShouldExecuteProperCallback() {
                // given
                Optional<String> value = Optional.empty();
                AtomicInteger successCounter = new AtomicInteger(0);
                AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

                // when
                value.ifPresentOrElse(
                  v -> successCounter.incrementAndGet(), 
                  onEmptyOptionalCounter::incrementAndGet);

                // then
                assertThat(successCounter.get()).isEqualTo(0);
                assertThat(onEmptyOptionalCounter.get()).isEqualTo(1);
            }

    - stream() method for converting an Optional to a Stream

    .. code-block:: python
            :linenos:

            @Test
            public void givenOptionalOfSome_whenToStream_thenShouldTreatItAsOneElementStream() {
                // given
                Optional<String> value = Optional.of("a");

                // when
                List<String> collect = value.stream().map(String::toUpperCase).collect(Collectors.toList());

                // then
                assertThat(collect).hasSameElementsAs(List.of("A"));
            }

            @Test
            public void givenOptionalOfNone_whenToStream_thenShouldTreatItAsZeroElementStream() {
                // given
                Optional<String> value = Optional.empty();

                // when
                List<String> collect = value.stream()
                  .map(String::toUpperCase)
                  .collect(Collectors.toList());

                // then
                assertThat(collect).isEmpty();
            }


Java 10 Improvements   
--------------------

- java.util.Optional, java.util.OptionalDouble, java.util.OptionalInt and java.util.OptionalLong has a new method
      orElseThrow() which is throwing NoSuchElementException if value is not present

    .. code-block:: python
           :linenos:

            @Test(expected = NoSuchElementException.class)
            public void whenNoArgOrElseThrowWorks_thenCorrect() {
                String nullName = null;
                String name = Optional.ofNullable(nullName).orElseThrow();
            }


Java 11 Improvements
--------------------
- isEmpty()
    - return true if the value from Optional is NULL

    .. code-block:: python
            :linenos:

            @Test
            public void givenAnEmptyOptional_thenIsEmptyBehavesAsExpected() {
                Optional<String> opt = Optional.of("Baeldung");
                assertFalse(opt.isEmpty());

                opt = Optional.ofNullable(null);
                assertTrue(opt.isEmpty());
            }



:ref:`Go Back <java-development-data-structures-label>`.