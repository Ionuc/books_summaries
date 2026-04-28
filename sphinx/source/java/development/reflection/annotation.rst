.. _java-development-reflection-annotation-label:

Annotation
==========
- is a form of metadata, provides data about a program that is not part of the program itself
- has no direct effect on the operation of the code they annotate
- metadata is a data that provides information about other data
- annotations are nothing more than just an information and what you can do knowing this information (this logic is programmed in a separate place)
- in programmin, we can annotate:
    - types
    - classes
    - interfaces
    - methods
    - parameters
    - constructors
    - packages
    - modules
    - other annotations
    - others

- there might be cases when you need to provide meta informaton about your code and use this information on different stages:
    - during the build time by compilation and discarded from class
    - on the class level, but discarded during the runtime when class is uploaded into JVM
    - during runtime

- usecases:
    - information for Compiler
        - annotations can be used by the compiler to detect errors or suppress warnings, like @Override
    - deployment time processing:
        - software tools can process annotation information to generate cde, XML files, etc, like Lombock
    - runtime processing:
        - some annotations are available to be examined at runtime

- each annotation is started with @ followed by name
- annotation can include :
    - elements which can be named
    - their values for those elements


    .. code-block:: python
           :linenos:

           @SuppressWarnings(value = "rawtypes")


- in case annotation has only one element, you can ommit the element key and put only element value


    .. code-block:: python
           :linenos:

           @SuppressWarnings("rawtypes")


- you can put multiple elements or have repeatable annotation (starting with Java 8)


    .. code-block:: python
           :linenos:

            @Override
            @Author(name = "Vladymyr Vysotskiy")
            @Author(name = "Konstantin Simonov")
            public String toString() {
                return "Demo [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                        + "]";
            }


Custom annotation
-----------------
- above is an example of a custom Test annotation for running unit tests:
    - Target annotation:
        - indicates the context in which an annotation type is applicable, or where you can use these annotations
    - Retantion annotation:
        - indicates how long annotations with the annotated tpe are to be retained

- to declared element in annotation, we need to declare a method with the name of the element itself:
    - this method can't throw any exception
    - there is a restriction on return type. You can return only:
        - primitives
        - string
        - class
        - enums
        - annotations
        - array of above types
    - these methods have default values which cannot be NULL


    .. code-block:: python
           :linenos:

        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Test {
            
            Class<? extends Throwable> expected() default None.class;
            
            String name() default "";
            
            /**
             * Default empty exception.
             */
            static class None extends Throwable {
                private None() {
                }
            }

        }


Running custom annotation
-------------------------
- running custom annotation logics needs to be put on other class
- in the above example:
    - TestRunner -> will performe the logic for processing Test annotations
    - TestSample -> provides methods having the Test annotation
    - TestAnnotationDemo -> run the TestRunner with the TestSample



    .. code-block:: python
           :linenos:

            public class TestRunner {
                
                public void runTests(Class clazz) {
                    Method[] methods = clazz.getDeclaredMethods();
                    Arrays.stream(methods)
                            .filter(method -> method.getAnnotation(Test.class) != null)     // with our annotation
                            .filter(method -> !Modifier.isStatic(method.getModifiers()))    // non-static methods only
                            .forEach(method -> {
                                try {
                                    var obj = clazz.getConstructor().newInstance();
                                    var testAnnotation = method.getAnnotation(Test.class);
                                    if (!testAnnotation.expected().equals(Test.None.class)) {
                                        try {
                                            method.invoke(obj);
                                        } catch (InvocationTargetException t) {
                                            if (!t.getCause().getClass().equals(testAnnotation.expected())) {
                                                System.out.println("Test with name \"" 
                                                        + testAnnotation.name() + "\" is failed.");
                                                return;
                                            }
                                        }
                                    } else {
                                        method.invoke(obj); 
                                    }
                                    System.out.println("Test with name \"" 
                                            + testAnnotation.name() + "\" is passed successfully.");
                                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                                    e.printStackTrace();
                                }
                            });;
                }
            }


    .. code-block:: python
           :linenos:

            public class TestSamples {
                
                @Test(name = "test without exception")
                public void shouldNotThrowException() {
                    // method that doesn't throw exception
                }
                
                @Test(name = "test with exception", expected = RuntimeException.class)
                public void shouldThrowException() {
                    throw new RuntimeException();
                }

            }


    .. code-block:: python
           :linenos:

            public class TestAnnotationDemo {
                
                public static void main(String[] args) {
                    TestRunner runner = new TestRunner();
                    runner.runTests(TestSamples.class);
                }

            }


Java 8 improvements
-------------------
- Repeatable annotations
    - Annotation needs to have @Repeatable annotation (in example above: Author)
    - create the container annotation (in example above: Authors):
        - repeating annotations are stored in a container annotation because of compatibility reasons
        - must have elment value that returns an array with objects of repeatable annotations

    .. code-block:: python
           :linenos:


            @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
            @Retention(RetentionPolicy.RUNTIME)
            @Repeatable(Authors.class)
            public @interface Author {
                
                String name();

            }

            @Override
            @Author(name = "Vladymyr Vysotskiy")
            @Author(name = "Konstantin Simonov")
            public String toString() {
                return "Demo [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                        + "]";
            }


:ref:`Go Back <java-development-reflection-label>`.