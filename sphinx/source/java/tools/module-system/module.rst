.. _java-tools-module-system-module-label:

Module
======
- added with Java 9
- is a uniquely named, reusale group of related packages, as well as resourecs and a module descriptor
- module descriptor contains:
    - module's name
    - module's dependencies
    - the package it explicitly makes available to other modules
    - the services it offers
    - the services it consumes
    - to what other modules it allows reflection
- a java module is a java mechanism that enables you to package a Java aplication or Java API as a separate Java module
- a java module is package as a jar file
- a java module must specify which other Java modules are required to do its job
- from Java 9, Java application must be packaged as Java modules
    - the JVM can check the whole module dependency graph from the application module when the JVM starts up.
    - if any required modules are not found startup, the JVM reports the missing module and shuts down
- before Java 9, missing classes from a missing Jar file would not be detected until the application actually tried to use the missing class, at runtime
- you can have only one module per jar file

Benefits
--------
- reliale configuration:
    - modularity provies mechanism for explicitly declaring dependencies which are recognized both at compile time and execution time
- strong encapsulation:
    - the packages in the module are accessible to other modules only if the module explicitly exports them
    - another modules can't use those packages unless it explicitly states that it requires the other module capabilities
- scalable Java Platform:
    - prevous, the Java platform was a mnolith, consisting of a massive number of packages
    - the platform is modularized into multiple modules
    - you can create custom runtimes consistig of only modules you need for your apps
- greater platform integrity
    - before java 9, you could use many classes in the platform that were not meant for use by abd apps' classes
    - with strong encapsulation, these internal APIs are truly encapsulated and hidden from apps using the platform
- use only classes you need
- improved performance

Modules Types
-------------
- there are 4 types of modules:
    - System modules:
        - these are the modules listed when we run Java list modules command
        - they include Java SE and JDK modules
    - application modules:
        - are modules created with the classes from the application
        - are named and defined in the compie mdules info class file included in the assemble jar
    - automatic modules:
        - you can include unofficial modules by adding existing Jar files to the module path
        - the name of the module would be derived from the name of the jar
        - automatic modules would have full read access to every other module loaded by the path
    - unnamed modules:
        - when a class or jar is loaded into classpath ut not the module path, it's automaticall added to the unnamed module
        - is used for backward compatibility with previously written Java code
        - is similar to the default package

Module descriptors
------------------
- a module descriptor is a compiled version of module declaration that's defined in a file named Module-info.java:
- this file should be located in the source root directory
- each module declaration begins with the keyword module followed by a unique module name and the module body
    - you should not use underscores in module names, package named, class named, methods names
- compiling the module declaration creates the module descriptor, which is stored in a file named Module info class in the modules root folder
- directives:
    - requires:
        - declare module dependencies
        - in example below, the module has both a runtime and a compile time dependency on some module that yo should refer by module name
        - each module must explicitly states its dependencies
        - all public types exported from a dependency are accessible by our module when we use this directive
        - it is not allow to have circular dependencies betweem modules
    - requires static:
        - this directive will create a compile time only dependency
        - is used to include an optional dependency
    - requires transitive:
        - used to force any donwstream consumers to read this required dependenciesm which requires transitive directive
    - exports:
        - is used to expose all public members of the named package
        - by default, a module doesn't expose any of its API to other modules
        - only the listed package itself is exported and not the subpackages
        - to export also a subpackage, it must be declared explicitly in exports directive
        - the same java package can only e exported by a single Java module at runtime
        - having to submodule exporting the same package is reffered to as a split package: total content of classes of the package is split between multiple modules => this is NOT allowed
    - exports to:
        - in case we want to limit access to our API and expose it only to some packages
        - we need to need to declare the modules which are allowed to import this package as requires
    - uses:
        - specifies a service used by this module, making the module a service consumer
        - A services is an object of a class that implements the interface, or extends the abstract class specified in the user's directive
        - note that the class name we use is either the interface or abstract class of the service, not the implementation class, because service in this context means an implementation of a specific interface or astract class can be consumed by other classes
        - difference between uses & requires:
            - we might require a module that provides a service we want to consume, but the service implements an interface from one of tis transitive depdendencies
            - instead of forcing our module to require all transitive dependencies jst in case, we use the USES directive to add the required interface to the module path
    - provides .. with ...:
        - a module can also be a service provder that other modules can consume
        - we should write PRODIVDES keyword first and specifies the interface or abstract class name of our service. After this, we specify the implementation
    - opens:
        - indicates that a specific package public types and then neste public and protected types are accessible to code in other modules at runtime only
        - also all the types in the specified package and all of the types members are accessible via reflection
        - by default, a type in module is not accessible to other modules unless it's a public type and you exports its package
    - opens ... to ...:
        - is the same as opens, but will allow reflection access only to provided modules


    .. image:: ../../../images/java/tools/module-system/module/module-descriptors.png
        :align: center



Command line options
--------------------
- module-path:
    - used to specify the module path. This is a list of one or more directories that contain our modules
- add-reads:
    - it can be used the command line equivalent of the required directive
- add-exports:
    - used for exports directive
- add-opens:
    - used for open clause in the module declaration
- add-modules:
    - adds the list of modules into the default set of modules
- list-modules:
    - prints a list of modules and their version strings
- patch-module
    - add or override classes in modules
- illegal-acccess-permit|warn|deny
    - either relax strong encapsulation by showing a single gloal warning, showing every warning or fails with erros. The default is permit


Migration algoritm
------------------
- create reliable suite of tests:
    - integration tests
    -
- identify goals of our migration and measure them:
    - possible goals:
        - maybe better performance
        - maybe better security
        - maybe better code design in order to be able to scale application faster
    - make measurements of current system
- download the udated JDK version
- run application with new JDK
- update third-party dependencies if needed
- identigy dependencies inside the project:
    - run jdeps against your project:
        - identifies dependencies between classes modules packages in the app
        - it is a analyzer tool
- identify deprecated API:
    - run jdepscan against your project
        - is a static analysis tool that scans a JAr file or some other aggregation of class files for usages of deprecated API elements
- select Migration Strategy
- migration execution
- regression tests
- measurment the result to confirm that goals of migration are achieved


Migration Strategies
--------------------
- Bottom-Up migration strategy:
    - in this strategy, initially, all jar files that are part of the application will be located at its classpath
    - then all of them can be migrated one by one to the module path
    - steps:
        - the first package to migrate is the package with the lowest position in dependencies hierachy that has not yet been migrated to module:
            - after that, add the module-info.java file to the project, with corresponding required statements
        - repeat the process with the next lowest position in dependency hierarchy


    .. image:: ../../../images/java/tools/module-system/module/bottom-up-migration-strategy.png
        :align: center


    - it works the best when we have ability to modify any jar that has has not yet been converted to module

- Top-Down migration strategy:
    - in this strategy, initially, all jar files that are part of the application will be located at its module path
    - all non migrated projects are treated as automatic modules
    - steps:
        - choose the high level project in the depdendency hierarchy that has not yet beent migrated to module
        - add module info java file to the project to transform it from automatic module to named module
        - add necessary exprts statements to make current module packages available to other modules
        - add necessary required statements
        - repeat the process to the next highest level component


    .. image:: ../../../images/java/tools/module-system/module/top-down-migration-strategy.png
        :align: center



    - it works the best when you don't have the possibility of modulerizing all the dependencies of the application, wheter temporarily or definitively

:ref:`Go Back <java-tools-module-system-label>`.