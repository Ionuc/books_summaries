.. _java-tools-jvm-jlink-label:

Jlink
=====
- introduced with Java 9
- jlink is a tool that generates a custom Java runtime image that contains only the platform modules that are required for a given application.
- Such a runtime image acts exactly like the JRE but contains only the modules we picked and the dependencies they need to function


How is working
---------------
- let's have a simple Hello Word example:


    .. code-block:: python
           :linenos:

            public class HelloWorld {
                private static final Logger LOG = Logger.getLogger(HelloWorld.class.getName());
                public static void main(String[] args) {
                    LOG.info("Hello World!");
                }
            }

            module jlinkModule {
                requires java.logging;
            }

- To run this program, we only need HelloWorld, String, Logger, and Object classes.
- Even though this program needs only four classes to run, all the predefined classes in the JRE also get executed, even if our program doesn’t require them.
- Therefore, to run a small program, we have to maintain a complete JRE, which is simply a waste of memory.

- With jlink, we can create our own, small JRE that contains only the relevant classes that we want to use, without wasting memory, and as a result, we’ll see increased performance.


Building Custom Java Runtime Images
-----------------------------------
- steps:
    1. Compiling a Module:
        - let’s compile the program mentioned above from the command line


    .. code-block:: python
           :linenos:

            javac -d out module-info.java
            javac -d out --module-path out com\baeldung\jlink\HelloWorld.java


        - let’s run the program:


    .. code-block:: python
           :linenos:

            java --module-path out --module jlinkModule/com.baeldung.jlink.HelloWorld
            // output :
            // Mar 13, 2019 10:15:40 AM com.baeldung.jlink.HelloWorld main
            // INFO: Hello World!


    2. Using jdeps to List the Dependent Modules
        - in order to use jlink, we need to know the list of the JDK modules that the application uses and that we should include in our custom JRE.
        - Let’s use the jdeps command to get the dependent modules used in the application


    .. code-block:: python
           :linenos:

            jdeps --module-path out -s --module jlinkModule
            // output
            // jlinkModule -> java.base
            // jlinkModule -> java.logging
            

    3. Creating a Custom JRE with jlink
        - To create a custom JRE for a module-based application, we can use the jlink command.
        - the value after the –add-modules parameter tells jlink which module to include in the JRE.


    .. code-block:: python
           :linenos:

            jlink --module-path "%JAVA_HOME%\jmods";out
              --add-modules jlinkModule
              --output customjre


    4. Running an Application with the Generated Image
        - To test our JRE, let’s try to run our module by navigating inside the bin folder of our customjre directory and run the command below:


    .. code-block:: python
           :linenos:

            java --module jlinkModule/com.baeldung.jlink.HelloWorld

Java 14 improvement
-------------------
- Creating Custom JRE with Launcher Scripts
    - we can also create a custom JRE with executable launcher scripts.
    - For this, we need to run the jlink command that has an extra –launcher parameter to create our launcher with our module and main class:


    .. code-block:: python
           :linenos:

            jlink --launcher customjrelauncher=jlinkModule/com.baeldung.jlink.HelloWorld
              --module-path "%JAVA_HOME%\jmods";out
              --add-modules jlinkModule
              --output customjre


    - This will generate two scripts: customjrelauncher.bat and customjrelauncher inside our customjre/bin directory.
    - running customjrelauncher.bat will execute the application


    .. code-block:: python
           :linenos:

            customjrelauncher.bat
            // output
            // Mar 18, 2019 12:34:21 AM com.baeldung.jlink.HelloWorld main
            // INFO: Hello World!

:ref:`Go Back <java-tools-jvm-label>`.