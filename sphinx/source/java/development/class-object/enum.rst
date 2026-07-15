.. _java-development-class-object-enum:

Enum type
=============
- is a special data type that enables for a variable to be set of predefined constants
- the value of enum type always must be equal to one of the values that have been predefined for it
- are used to express a set of values which are finit at compile time
- it can be used in switch block
- you can use valueOf() method to create an instante based on the String:
    - the String must match the Enum name values
- oridinal field is the index of the Enum specified in enum declaration

    .. code-block:: python
       :linenos:

        public num Priority{
            HIGH, MEDIUM, LOW;
        }

        public static void main(String[] args) {
            Priority priority = Priority.valueOf("HIGH");
            Priority priority = Priority.valueOf("high"); // => will be thrown RuntimeException
        }


- each enum value can have its own fields and methods


:ref:`Go Back <java-development-class-object-label>`.