.. _python-packages-label:

Packages
========
    - is a module which can contain other modules, including other packages
    - packages are generally represented by directories in the file system while modules are represented by single files
    - to create a package you need to create a directory <package_name> and inside it to add __init__py file
    - __init__py file is executed when the package is imported

Located file source
-------------------
    - Python is checking the path attribute of the standard sys module commonly referred to as sys.path
    - this value is a list of directories
    - when you ask to import a module, it starts with teh first directory in sys.path and checks for an appropriate file:
        - if no match found in the first directory, it checks the next until a match is found or an import error is raised if not found
        - a match is considered as a directory with the given name having file __init__py or a file with the name
    - the PYTHONPATH environment variable is a list of paths that are added to sys.path when Python starts

Relative imports
----------------
    - relative imports allow us to use shortened paths to modules and packages
    - use "." do refer to which parrent directory are you referring: one "." is referring current dir, ".." is referring the parrent and so on

Controlling imports
-------------------
    - you can set __all__ attributed to the list of strings to which the package should import
    - it is useful when you don't want to expose some modules

Namespace PSckages
------------------
    - there are cases when you want to split a packages across multiple directories
    - a namespace package is a package which is spread over several directories with each directory tree contributing to a single logical package from the programmer's point of view
    - namespace packages does not have __init__.py file
    - this means namespace package can't have package level initialization code => nothing will be executed by the package when it's imported

Executable Directories
----------------------
    - let you specify a main entry point which is run when the package is executed by Python
    - you can put a special module named __main__.py in a directory and Python will execute it

Singleton with modules
----------------------
    - the simple way to create a singleton is by using modules

:ref:`Go Back <python-label>`.
