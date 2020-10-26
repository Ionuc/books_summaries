.. _java9-anonymous-class-diamond-operator:

Anonymous Class Diamond Operator
==================================

    - diamond operator was introduced in Java 7
    - but it cannot be used with anonymous class
    - this was fixed in java 9

    .. code-block:: python
        :linenos:

        public List getEmployee(String empid){
    ​       // Code to get Employee details from Data Store
    ​        return new List(emp){ };
 ​      }

:ref:`Go Back <java9-label>`.