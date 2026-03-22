.. _java-development-reflection-strong-encapsulation-label:

Strong Encapsulation
====================
- implemented with Java 9 as relaxed strong encapsulation

Goal
----
- Improve the security and maintainability of the JDK, which is one of the primary goals of Project Jigsaw.
- Encourage developers to migrate from using internal elements to using standard APIs, so that both they and their users can upgrade without fuss to future Java releases.

Motivation
----------
- using reflaction, you can access:
    - any fields (private, protected, default package, public) of any class (private, protected, public)
    - any method (private, protected, default package, public) of any class (private, protected, public)

- there are classes designed to be used only internally by JVM and not to be used externally, by any developer
    - acessing fields or methods from these classes should be formidden, even if the access modifier of them is private, package, package protectd or public


Description
-----------
- the strong encapsulation of some of the JDK's packages is relaxed by default
- This relaxation is controlled at run time by a new launcher option, --illegal-access which can have values:
    - --illegal-access=permit:
        - opens each package in each module in the run-time image to code in all unnamed modules, i.e., to code on the class path, if that package existed in JDK 8
        - This enables both static access, i.e., by compiled bytecode, and deep reflective access, via the platform's various reflection APIs.
        - The first reflective-access operation to any such package causes a warning to be issued, but no warnings are issued after that point
        - This mode is the default in JDK 9
    - --illegal-access=warn:
        - is identical to permit except that a warning message is issued for each illegal reflective-access operation.
    - -illegal-access=debug:
        - is identical to warn except both a warning message and a stack trace are issued for each illegal reflective-access operation.
    - --illegal-access=deny:
        - disables all illegal-access operations except for those enabled by other command-line options, e.g., --add-opens.
        - This mode will become the default in a future release

- example of warning message:


    .. code-block:: python
           :linenos:

            java -jar jython-standalone-2.7.0.jar
            WARNING: An illegal reflective access operation has occurred
            WARNING: Illegal reflective access by jnr.posix.JavaLibCHelper (file:/tmp/jython-standalone-2.7.0.jar) to method sun.nio.ch.SelChImpl.getFD()
            WARNING: Please consider reporting this to the maintainers of jnr.posix.JavaLibCHelper
            WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
            WARNING: All illegal access operations will be denied in a future release
            Jython 2.7.0 (default:9987c746f838, Apr 29 2015, 02:25:11) 
            [OpenJDK 64-Bit Server VM (Oracle Corporation)] on java9
            Type "help", "copyright", "credits" or "license" for more information.


Java 16 improvements
--------------------
- change default value for --illegal-access=deny
- Strong encapsulation applies at both compile time and run time, including when compiled code attempts to access elements via reflection at run time.
- The non-public elements of exported packages, and all elements of unexported packages, are said to be strongly encapsulated.
- in order to provide posibility for classes to access private fields or methods, you should update how module is exported

    .. code-block:: python
           :linenos:

            module java.base{
                export java.security
            }
            // => will NOT ALLOW to use setAccessible(true) for private or public fields or methods


            module java.base{
                export private com.myapp.lib
            }
            // => will ALLOW to use setAccessible(true) for private or public fields or methods




:ref:`Go Back <java-development-reflection-label>`.