.. _java-development-reflection-reflection-label:

Reflection
==========
- reflection is the ability to examine or modify the runtime behavior of applications running in the JVM
- reflection allows us to inspect or modify attributes of classes, interfaces, fields and methods during the program execution at a runtime, that is basically after compilation process
- we can instantiate new bjects, invoe methods and get or set field values using reflection, even if they are private fields and there are no getters or setters
- it is used for:
    - debugging:
        - when you are in debug mode, you can see the values for private fields
    - test tools:
        - mockito, power mockito are using reflection to mock methods and values
    - extensibility features:
    - virtual development environments

- all classes are in package java.lang.reflect

Class
-----
- there is an object of type Class for each type:
    - this object contains type, definition and API to interact with it


    .. code-block:: python
           :linenos:

            Class userClass = new User().getClass();
            System.out.println("Get class name: " + userClass.getName());
            Field[] fields = userClass.getDeclaredFields();


- methods from Class:
    - getName():
        - return the full class name
    - getPackageName()
        - return the package name
    - getDeclaredFields(): -> Field[]
        - return an array of Field with all fields from class: object fields or static fields
        - excludes inherited fields
    - getFields()
        - return only the public fields
    - getDeclaredField(<name>)
        - return the field with the given name
        - can throw 2 exception:
            - NoSuchFieldException -> in case the field with specific parameters does not exists
            - SecurityException -> can be thrown by SecurityManager to indicate a security violation
    - getSuperClass()
        - return the super class or Object(if the current class is not extending any class)
    - getInterfaces() -> Class[]:
        - return an array of Class with the corresponding interfaces
    - getDeclaredConstructors() -> Constructor<T>[]
        - returns an array of Constructor<> with all declared constructors, including private ones
    - getConstructors() -> Constructor<T>[]
        - returns an array of Constructor<> with all only public declared constructors
    - getConstructor(Class<?>... parameterTypes) -> Constructor<T>
        - return the constructor with the given parameters
        - can throw 2 exception:
            - NoSuchMethodException -> in case the constructor with specific parameters does not exists
            - SecurityException -> can be thrown by SecurityManager to indicate a security violation
    - getDeclaredMethods -> Method[]:
        - get an array with all methods
    - getMethods -> Method[]
        - get an array with only the pulic methods
    - getMethod(<name>, <arguments>) -> Method
        - return the Method for the given name and arguments
        - can throw 2 exception:
            - NoSuchMethodException -> in case the method with specific parameters does not exists
            - SecurityException -> can be thrown by SecurityManager to indicate a security violation

- Creating instances of Class:
    - by running the getClass() method from an object


    .. code-block:: python
           :linenos:

            Class userClass = new User().getClass();


    - by loading the class with full name:
        - can be thrown ClassNotFoundException


    .. code-block:: python
           :linenos:

            userClass = Class.forName("com.itbulls.learnit.javacore.reflection.Demo$User");


Constructor
-----------
- contains information about all the constructors defined in the class
- methods:
    - getName()
        - return the name
- fields:
    - lenght:
        - returns the number of declared constructors


    .. code-block:: python
            :linenos:

            System.out.println("===== Get Constructors =====");
            Constructor<User>[] constructors = userClass.getDeclaredConstructors();
            System.out.println("Number of constructors: " + constructors.length);
            Arrays.stream(constructors).forEach(constructor -> System.out.println(constructor.getName()));
            
            System.out.println("===== Get Private Constructor =====");
            Constructor<User> privateConstructor = userClass.getDeclaredConstructor(String.class);
            System.out.println(privateConstructor);


- invocation of constructors:


    .. code-block:: python
            :linenos:

            System.out.println("===== New Instance =====");
            Constructor<User> defaultConstructor = userClass.getConstructor();
            User newInstance = defaultConstructor.newInstance();
            System.out.println(newInstance);
            System.out.println(privateConstructor.newInstance("newdefault@email.com"));


Field
-----
- is a type that contains information about the field of a single class or interface

- methods:
    - getName():
        - return the field name
    - getGenericType():
        - return the Type of the field
    - getModifiers() ->
        - return an int which should be used with class Modifier to convert the int value to a human readable value


    .. code-block:: python
            :linenos:

            Field[] fields = userClass.getDeclaredFields();
        
            System.out.println("===== Get Fields Names =====");
            Arrays.stream(fields).forEach(field -> System.out.println(field.getName()));
        
            System.out.println("===== Get Fields Type =====");
            Arrays.stream(fields).forEach(field -> System.out.println(field.getGenericType()));
            
            System.out.println("===== Does Field have private modifier? =====");
            Arrays.stream(fields).forEach(field -> 
                    System.out.println(Modifier.isPrivate(field.getModifiers())));


- invocation of fields:
    - to access private fields, it is needed to be set the accessible mode to true
    - setting a new value for a fields needs to concrete instante of the parent object
    - for static fields, you can pass a NULL value as the parent object instance
    - for primitive types, there is a set of setInt, etc methods


    .. code-block:: python
            :linenos:

            System.out.println("===== Field initalization =====");
            Field firstNameField = userClass.getDeclaredField("firstName");
            firstNameField.setAccessible(true);
            firstNameField.set(newInstance, "Sergey");
            System.out.println(newInstance);
            
            System.out.println("===== Static Field initalization =====");
            Field idField = userClass.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(null, 20);
            System.out.println(newInstance);


- reading value:
    - the process is the same as setting value
        - it is needed to be set the accessible mode to true
        - reading a new value for a fields needs to concrete instante of the parent object
        - for static fields, you can pass a NULL value as the parent object instance
        - for primitive types, there is a set of getInt, etc methods


    .. code-block:: python
            :linenos:

            System.out.println("===== Read Value from Field +====");
            System.out.println(firstNameField.get(newInstance));
            System.out.println(idField.get(null));


Method
------
- provides information regarding methods
- in reflection, in case exception hs been thrown on the invoked methods, the exception is wrapped into InvocationTargetException
- methods:
    - getName()
        - return the method name


    .. code-block:: python
            :linenos:

            System.out.println("===== Get Methods =====");
            Method[] methods = userClass.getDeclaredMethods();
            Arrays.stream(methods).forEach(method -> System.out.println(method.getName()));
            
            System.out.println("===== Get Method =====");
            Method method = userClass.getMethod("mergeTwoUserAccounts", User.class);
            System.out.println(method);


- invocation of methods:
    - to access private method, it is needed to be set the accessible mode to true
    - invoking a method for an object needs to concrete instante of the parent object
    - for static methods, you can pass a NULL value as the parent object instance


    .. code-block:: python
            :linenos:

            System.out.println("===== Method invocation =====");
            Method privateMethod = userClass.getDeclaredMethod("doSomething", String.class);
            privateMethod.setAccessible(true);
            privateMethod.invoke(newInstance, "Test String");
            
            System.out.println("===== Static Method invocation =====");
            Method privateStaticMethod = userClass.getDeclaredMethod("doSomethingStatic", String.class);
            privateMethod.setAccessible(true);
            privateStaticMethod.invoke(null, "Test String");


:ref:`Go Back <java-development-reflection-label>`.