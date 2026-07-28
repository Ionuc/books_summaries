.. _java-development-class-object-class-label:

Class
=====

Types of classes
----------------
- concrete classes
- nested classes (Inner classes, static classes)
- final classes
- POJO classes
- abstract classes
- anonymous classes


Java 9 improvements
-------------------
- getPackageName() was added on the Class


    .. code-block:: python
        :linenos:


        System.out.println("===== this.getClass().getPackageName() =====");
        System.out.println(Java9.class.getPackageName());


Java 11 improvements
-------------------
- Nest-Based Access Control:
    - before Java 11:
        - access control relied on the class file strucgture and relationships with packages, posing security concerns with prifileged access for certain classes like inner classes
        - inner class could have privileged access to the private members of their enclosing classes, leading to potential security concerns
    - with Java 11:
        - it is introduced a nest, a new access control context grouping related classes and interfaces with shared access control checks
        - the nest host attribute:
            -  is added to the class file structure
            - within a nest, one class or interface serves as the nest host
            - the nest host is the primary member used for access control checks
        - the nest members attribute:
            is added to each class that is member of the nest
            - all other classes and interfaces in the nest are considered nest members
            - nest memebres can access each other's private members wihtout additional access control checks

- 2 new methods were introduced:
    - getNestHost()
    - getNestMembers()


    .. code-block:: python
        :linenos:

        private static void getNestInformation(Class<?> clazz) {
            System.out.println("Class: " + clazz.getName());

            // Get the nest host
            Class<?> nestHost = clazz.getNestHost();
            System.out.println("Nest Host: " + (nestHost != null ? nestHost.getName() : "None"));

            // Get the nest members
            Class<?>[] nestMembers = clazz.getNestMembers();
            System.out.println("Nest Members: " + Arrays.toString(nestMembers));

            System.out.println();
        }

:ref:`Go Back <java-development-class-object-label>`.