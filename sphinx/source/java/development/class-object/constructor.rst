.. _java-development-class-object-constructor-label:

Constructors
============
- are used to create new objects of a class
- in case parent class has defined constructors, these constructors needs to be called in the child class
- order of executions when creating new object for the first time:
    - static initialization of parent class
    - static initialization of child class
    - object initialization of parent class
    - constructor of parent class
    - object initialization of child class
    - constructor of child class

- order of executions when creating new object for the second time (almost the same as the first time, without static initialization:
    - object initialization of parent class
    - constructor of parent class
    - object initialization of child class
    - constructor of child class


    .. code-block:: python
        :linenos:
    
        class Parent {
            static {
                System.out.println("Static init block parent");
            }

            {
                System.out.println("Init block parent");
            }

            public Parent() {
                System.out.println("Parent constructor");
            }
        }

        class Child extends Parent {
            static {
                System.out.println("Static init block child");
            }

            {
                System.out.println("Init child parent");
            }

            public Parent() {
                System.out.println("Child constructor");
            }
        }

        public static final main(String[] args) {
            Parent p1 = new Child();
            Parent p2 = new Child() 
        }

        //output for p1:
        Static init block parent
        Static init block child
        Init block parent
        Parent constructor
        Init block child
        Child constructor

        // output for p2
        Init block parent
        Parent constructor
        Init block child
        Child constructor


:ref:`Go Back <java-development-class-object-label>`.