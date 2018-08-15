.. _class-loaders:

Class Loaders
=============
    - The Java ClassLoader is a part of the Java Runtime Environment that dynamically loads Java classes into the Java Virtual Machine
    - Usually classes are only loaded on demand
    - The Java run time system does not need to know about files and file systems because of classloaders
    - Delegation in an important concept to understand when learning about classloaders.

    - The class loader is responsible for locating libraries, reading their content, and loading the classes contained within the libraries
    - This loading is done “on demand”, in that it does not occur until the class is called by the program
    - A class with a given name can only be loaded once by a given classloader.
    - Each Java class must be loaded by a class loader.

    - In java, a class will have its code contained in a .class file
    - In Java runtime, each class will have its code also available in the form of a first-class Java object, which is an instance of java.lang.Class.
    - Whenever we compile any Java file, the compiler will embed a public, static, final field named class, of the type java.lang.Class in
      the emitted byte code

    - So we can access

    .. code-block:: python
       :linenos:

       java.lang.Class klass = Myclass.class

Class Identifier
----------------
    - Once a class is loaded into a JVM, the same class will not be loaded again
    - A class loaded in a JVM has a specific identify.
    - This identify is a combination of class name Cl, of the package Pg, and the instance kl1 of the class loader KlassLoader : (cl, Pg, kl1)
    - This means that 2 class loader instances (cl, Pg, kl1) and (cl, Pg, kl2) are different

Class loaders used by JVM
-------------------------
    - When the JVM is started, 3 class loaders are used
        - Bootstrap class loader -> loads the core Java libraries located in the <JAVA_HOME>/jre/lib directory. This class loader,
          which is part of the core JVM is written in native code

        - Extensions class loader -> loads the code in the extensions directories (<JAVA_HOME/jre/lib/ext, or another directory
          specified by the java.ext.dirs system property)

        - System class loader -> loads code found on java.class.path, which maps to the CLASSPATH environment variable
    - Whenever a new JVM is started by typing java MyMainClass, the bootstrap class loader is loading the core java classes

JAR hell
--------
    - JAR Hell is a term used to describe all the various ways in which the classloading process can end up not working
    - Three ways JAR hell can occur are:
        - Accidental presence of 2 different versions of a library installed on a system. This will not be considered an error by the system.
          The system will load classes from one or another library
        - Multiple libraries require different version of library foo. If versions of library foo use the same class names, there is no
          way to load the versions of library foo with the same classloader.
        - The most complex JAR hell problems arise in circumstances that take advantage of the full complexity of the classloading system.
          A Java program is required to use multiple classloaders and the class loaded may interact in complex ways.

    - The OSGI Alliance specified a modularity framework that aims to solve JAR hell for current and future VMs
    - Using metadata in the JAR manifest, JAR files (called bundles) are wired on a per-package basis
    - Bundle can export packages, import packages, and keep packages private.

Default Class Loading
---------------------
   - By default, Java searches current directory
        - Classes must be in .class files
        - classes must be under package directories

    .. code-block:: python
       :linenos:

       // read to a file
       package com.imesaros.training;
       import com.imesaros.support
       public class Main{
           public static void main{
               Student s = new Student();
               Other o = new Other();
          }
       }

       package com.imesaros.training;
       public class Student{...}

       package com.imesaros.support;
       public class Other{...}

    - if I run the Main method in a directory mydir (C:\mydir> java com.imesaros.training.Main), this should be structured like:
    - mydir
        - com
            - imesaros
                - training
                    - Main.class
                    - Student.class
                - support
                    - Other.class

Specify Class Path
------------------
    - we can provide the list of paths to search
    - it is search in the order from the list
    - the current directory is no longer used
    - there are 2 options for sepcifying class path:
        - Environment variable
        - Java command option

    - Environment variable
        - variable name is CLASSPATH
        - it is used by all programs that don't provide a specific path
        - changing it for one application, will change for all applications

    - Java command option
        - it can be used options : -cp or -jar
        - using -cp:
            - the separator depends on the OS: Windows is ";", Unix is ":"
            - if we referece a .class file, we put the path to folder containing package root
            - if the .class is in a .jar file, the value we put is the path to the jar file, including the jar file name
            - if we have this example:

    .. code-block:: python
       :linenos:

       - psdir -> com -> imesaros -> training  -> Main.class
                                               -> Student.class
       - libdir -> com -> im -> support -> Other.class
       - mydir -> the command will be : java -cp \psdir;\libdir com.imesaros.training.Main

    .. code-block:: python
       :linenos:

       - psdir -> training.jar ->  com -> imesaros -> training  -> Main.class
                                                                -> Student.class
       - libdir -> com -> im -> support -> Other.class
       - mydir -> the command will be : java -cp \psdir\training.jar;\libdir com.imesaros.training.Main

        - using -jar
            - we can access .class inside a .jar file
            - it locks down the class loading:
                - class loading totally controlled by jar file
                - no otehr class loading source is used
            - if you have : mydir -> ourapp.jar, the command will be : java -jar ourapp.jar
            - the .jar file can have a manifest, where it identifies the Main entry point under key : "Main-Class"

How Class Loaders Work
----------------------
    - All class loaders expect the bootstrap class loader have a parent class loader.
    - All class loaders are of the type java.lang.Classloader.
    - The most important aspect is to correctly set the parent class loader.
    - The parent class loader for any class is the class loader instance that loaded that class loader ( Remember, a class loader is itself a class).
    - A class is requested out of a class using loadClass() method

:ref:`Go Back <java-jvm-label>`.
