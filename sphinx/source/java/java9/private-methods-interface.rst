.. _java9-private-methods-interface:

Private Methods in Interface
============================

    - with java 8 we can provide method implementation in Interface using default and static methods
    - private methods were introduced to avoid redundant code and more re-usability
    - it was introduced private method and private static methods.

Java 7 interface
----------------
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

Java 8 interface
----------------
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

Java 9
------
    - it was introduced private methods and private static methods, this means we can have:
        - constant variables
        - abstract methods
        - default public methods
        - static public methods
        - private methods
        - private static methods

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

Rules to define private methods
--------------------------------
    - no private and abstract modifiers together => will result in compile error
    - private methods must contain body

Why do we need private methods in Interface ?
---------------------------------------------
    - no need to write duplicate code
    - we got the choice to expose only our intended methods implementations to clients

:ref:`Go Back <java9-label>`.