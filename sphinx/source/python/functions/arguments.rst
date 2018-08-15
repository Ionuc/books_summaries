.. _python-functions-arguments-label:

Arguments
=========
Default Arguments
-----------------
    - default arguments are used when no value is set for the corresponding argument
    - default arguments must be at the list part of the argument list
    - default arguments values are evaluated when def is evaluated, so default arguments are evaluated only once

        .. code-block:: python
       :linenos:

       def add_smap(menu=[]):
          menu.append("spam")
          return menu
       add_spam() => ["spam"]
       add_spam() => ["spam", "spam"] #because menu is not changed to [], is referring the old list ["spam"]

        .. code-block:: python
       :linenos:

       def add_smap2(menu=None):
        if menu is None
            menu = []
        menu.append("spam")
        add_spam() => ["spam"]
        add_spam() => ["spam"]

Positional and keyword argument
-------------------------------
    - you can call a method and specify the corresponding parameter1 = value1, parameter2 = value2, etc
    - positional arguments used in a call are associated with the formal arguments used in the definition in order
    - keyword argument use the name of the actual argument at the call site to associate with the name of the formal argument in the definition and can be 
      provided in any order so long as they follow any positional arguments

Extending Formal Argument
-------------------------
    - you can have methods which you can pass any number of parameters
    - the rules applies to regular functions, lambdas and other callables
    - positional extra arguments:
        - you can have positional extra arguments by adding "*" before the argument name
        - the positional extra arguments will be passed as tuple

            .. code-block:: python
           :linenos:

           def hypervolume(*lengths):
               i = iter(lengths)
               v = next(i)
               for lenght in i :
                   v *= length
               return v
           hypervolume(2, 4) => 8
           hypervolume(2, 4, 6) => 48

        - you can have normal arguments first, and then the extra arguments:

            .. code-block:: python
           :linenos:

           def hypervolume(lenght, *lengths):
               v = lenght
               for lenght in i :
                   v *= length
               return v

        - adding nromal arguments after positinal extra arguments must pe set by name:

            .. code-block:: python
           :linenos:

           def print_args(arg1, *args, kwarg1):
               ...
           print_args(1, 2, 3, 4, ,5 , kwarg1 = 6)

    - arbitrary extra argument:
        - you can have also arbitrary extra arguments by adding "**" before the argument name
        - the arbitrary extra argument will be passed as dicitonary
        - arbitrary extra arguments must be the last in the argument list

            .. code-block:: python
           :linenos:

           def tag(name, **attributes):
               result = '<' + name
               for key, value in attributes.items()
                   result += ' {k}="{v}"'.format(k = key, v =str(value))
               return result += '>'

            .. code-block:: python
           :linenos:

           def print_args(arg1, *args, kwarg1, **kwargs):
               .....
           print_args(1, 2, 3, kwarg1 = 6, kwarg2 = 9, kwarg2 = 7)

Extended Call syntax
--------------------
    - extended call syntax allows us to use iterable series such as tuple to populate positional arguments and any mapping type cush as dictionary has
      string keys to populate keyword arguments

        .. code-block:: python
         linenos:

         def print_args(arg1, arg2, *args):
           ...
         t = (11, 12, 13, 14)
         print_args(*t) => (13, 14) will be passed as argument for *args

        .. code-block:: python
         linenos:

         def color(red, green, blue, **kwargs):
           ...
         t = {'red' : 21, 'green' : 68, 'blue' : 120, 'alpha' : 52}
         print_args(**t) => {'alpha' : 52} will be passed as argument for **kwargs

   
:ref:`Go Back <python-functions-label>`.
