.. _java-development-iteration-statements-label:

Iteration statements
====================

While
-----
- perform the statements from the block until the condition is true
- in case the conditions is already false, it will not execute the block

Do-While
--------
- it will execute the block until the condition is true
- in case the condition is false, the block will be executed at least once

For
---
- contains 3 parts:
    - initialization expression:
        - is executed only once
        - is used to initialize the counter
        - the variables can be initializes also outside the for loop
    - condition
    - iteration:
        - can be performe inside the for loop block statements

   .. code-block:: python
        :linenos:

        for (int i = 0; i < 5; i++){
            System.out.println("counter: " + i);
        }

        // counter j is initialized outside of foor loop and incremented inside the block
        int j = 0;
        for (; j < 5;){
            System.out.println("counter: " + j++);
        }

        // infinit loop
        for (;;;){
        }


- multiple variables can be initialized and incremented inside for-loop

   .. code-block:: python
        :linenos:

        for (int i = 0, j = 10; i <= j; i++, j--){
            System.out.println("counter: i = " + i + ",j = " + j);
        }


For each
--------
- is used to iterate a collection of elements
- you can't change iteration in foreach loop

   .. code-block:: python
        :linenos:

        int[] arr = {1,2,3};
        int sum = 0;
        for (int number : arr){
            sum += number;
        }

Nested loops
------------
- are for-loop declaration inside another for-loop

Continue
--------
- is a jump statement, which can transfer control to another part of your program
- in case of for-loop, the "continue" statement will jump to the next iteration of the loop

Break
-----
- you can exit the loop earlier with "break" statement in case it is not needed to let the for-loop finish all iterations
- it breaks the closest loop

Labels
------
- "Goto" statement allows you to pass control of execution to any line in your code without any condition
- the jump to lines of code are usually identified by labels
- labels are used to break the loop marked with specific label or to continue another loop

   .. code-block:: python
        :linenos:

        System.out.println("=========== labels");
        loop1: for (int i = 0; i < 5; i++) {
                System.out.println("counter i: " + i);
            loop2: for (int j = 0; j < 5; j++) {
                    System.out.println("counter j: " + j);
                        if (j >= 0 && j < 3) {
                            System.out.println("continue loop2");
                            continue loop2;
                        } else {
                            System.out.println("break loop1");
                            break loop1;
                        }
                    }
        }

- labels with break statements in JAva is more structure form of goto statement
- in case you want to break the main loop from the nested loop, you can use the labels

:ref:`Go Back <java-development-label>`.
