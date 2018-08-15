.. _python-exceptions-label:

Exception Handling
==================
    - exception handling is a mechanism for stopping "normal" programm flow and cotninuing at some surrounding context or code block

    .. code-block:: python
       :linenos:

       import sys
       def convert(s):
       '''Convert to an integer.'''
       try:
           x = int(s)
       except (ValueError, TypeError) as e:
           print('Conversion error: {}'.format(str)e)), file = sys.stderr)
           x = -1
       return x

    - exceptions for programmer errors:
        - IndentationError
        - SyntaxError
        - NameError

Pass
----
    - is a special statement which does precisely nothing
    - is a NOOP
    - its only purpose is to allow us to construct syntactically permissible block which are sementically empty
    - in case you don't want to do something on error, but the next line would be return, you have to use the "pass" statement

    .. code-block:: python
       :linenos:

       def convert(s):
       '''Convert to an integer.'''
       try:
           x = int(s)
       except (ValueError, TypeError):
           pass
       return x

Raise
-----
    - raise statement will re-raise the thrown exception

    .. code-block:: python
       :linenos:

       import sys
       def convert(s):
       '''Convert to an integer.'''
       try:
           x = int(s)
       except (ValueError, TypeError) as e:
          raise e

EAFP vs LBYL
------------
    - there are 2 aproaches for code you know it might fail:
        1) Look before You Leap (LBYL):
            - check that all the preconditions for a failure-prone operation are met in advanced of attempting the operation
        2) It's Easier to Ask Forgiveness than Permission (EAFP):
            - to blindly hope for the best, but be prepared to deal the consequences if it doesn't work out
    - in Python is prefered EAFP because:
        - it puts primary logic for the happy path in its most readable form with deviations from the normal flow handled separately rather than interspersed
          with the main flow

Resource Cleanup
----------------
    - it can be done with try .. finally
    - it can be done using context managers which are the modern solution to this common situtation

:ref:`Go Back <python-label>`.