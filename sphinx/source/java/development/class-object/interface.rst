.. _java-development-class-object-interface:

Interface
=========


Constants
---------
- constants can be defined in interface. These are: public, static, final



Interface vs Abstract class
---------------------------
- interface:
    - "implements" keyoword
    - all fields are constants
    - can be implemented together with other interfaces
- abstract class:
    - "extends" keyword
    - can have fields with all possible modifiers
    - can't be extended simultaneosly with other classes



Java 8 features
---------------
- Default Methods
    - are methods defined in interface which are having a body
    - these methods can be overriden from child classes, but it is mandatory
    - in case a class is extending multiple interface which are having default methods with the same signature, a compilation error will be thrown in the concret class in order to specify which default implementation should be use
    - Diamond Problem
        - is the problem when 2 interfaces declare the same signature for a default methods and a third interface extends both of them: 
            - the problem is that the third interface does not know which implementation to take for the default method and a is thrown a compilation error
            - solution: third interface must override the default method and provide the implementation


- static methods
    - static methods can be defined on interface
    - they are public

- with Java 7:
    - in Java 7 or earlier, an interface can have only 2 kinds of things:
        - constant variables
        - abstract methods
    - the only way to use abstract methods and non-abstract methods is to use abstract class

    .. code-block:: python
        :linenos:
 
        public interface DBLogging{
            String MONGO_DB_NAME = "ABC_Mongo_Datastore";
            String NEO4J_DB_NAME = "ABC_Neo4J_Datastore";
            String CASSANDRA_DB_NAME = "ABC_Cassandra_Datastore";

            void logInfo(String message);
            void logWarn(String message);
            void logError(String message);
            void logFatal(String message);
        }

- with Java 8
    - it was introduced default public methods and static public methods, this means we can have:
        - constant variables
        - abstract methods
        - default public methods
        - static public methods

    .. code-block:: python
        :linenos:
    
        public interface DBLogging{
            String MONGO_DB_NAME = "ABC_Mongo_Datastore";
            String NEO4J_DB_NAME = "ABC_Neo4J_Datastore";
            String CASSANDRA_DB_NAME = "ABC_Cassandra_Datastore";

            // abstract method example
            void logInfo(String message);

            // default method example
            default void logWarn(String message){
                // Step 1: Connect to DataStore
                // Step 2: Log Warn Message
                // Step 3: Close the DataStore connection
            }
            default void logError(String message){
                // Step 1: Connect to DataStore
                // Step 2: Log Error Message
                // Step 3: Close the DataStore connection
            }
            default void logFatal(String message){
                // Step 1: Connect to DataStore
                // Step 2: Log Fatal Message
                // Step 3: Close the DataStore connection  
            }
            // static method example
            static boolean isNull(String str) {
                System.out.println("Interface Null Check");
                return str == null ? true : "".equals(str) ? true : false;
            }
            // Any other abstract, default, static methods
        }


- FunctionalInterface
    - was introduced to support lambda expression and method reference
    - is an interface which has only one abstract method
    - it can have multiple default methods
    - it is recommanded to add annotation @FunctionalInterface to force a compilation error in case multiple abstract methods are added


Java 9 features
---------------
- private methods:
    - it was introduced private methods and private static methods, this means we can have:
        - constant variables
        - abstract methods
        - default public methods
        - static public methods
        - private methods
        - private static methods
    - cannot be override or access from other classes


    .. code-block:: python
        :linenos:


        public interface DBLogging {
            String MONGO_DB_NAME = "ABC_Mongo_Datastore";
            String NEO4J_DB_NAME = "ABC_Neo4J_Datastore";
            String CASSANDRA_DB_NAME = "ABC_Cassandra_Datastore";

            default void logInfo(String message) {
                log(message, "INFO");
            }

            default void logWarn(String message) {
                log(message, "WARN");
            }

            default void logError(String message) {
                log(message, "ERROR");
            }

            default void logFatal(String message) {
                log(message, "FATAL");
            }

            private void log(String message, String msgPrefix) {
                // Step 1: Connect to DataStore
                // Step 2: Log Message with Prefix and styles etc.
                // Step 3: Close the DataStore connection
            }
            // Any other abstract, static, default methods
        }


- Rules to define private methods
    - no private and abstract modifiers together => will result in compile error
    - private methods must contain body

- Why do we need private methods in Interface ?
    - no need to write duplicate code
    - we got the choice to expose only our intended methods implementations to clients

:ref:`Go Back <java-development-class-object-label>`.