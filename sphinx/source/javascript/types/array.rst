.. _javascript-array-label:

Array
=====
    - One of the 2 build-in data structure (besides Object)
    - Is an indexed collection

    .. code-block:: python
        :linenos:

        let arr = new Array();
        let arr = [];
        let fruits = ["Apple", "Orange", "Plum"];
        alert( fruits[0] ); // Apple

    - Can store anything

    .. code-block:: python
        :linenos:

        // mix of values
        let arr = [ 'Apple', { name: 'John' }, true, function() { alert('hello'); } ];

        // get the object at index 1 and then show its name
        alert( arr[1].name ); // John

        // get the function at index 3 and run it
        arr[3](); // hello

Methods pop/push, shift/unshift
-------------------------------
    - a queue is one of most common uses of an array, this means an ordered collection of element which supports 2 operations:
        - push -> append an elemetn ot the end
        - shift -> get an element from the beginning, advancing the queue, so that the 2nd element becomes the 1st
    - arrays support both operations
    - another case for arrays are stack which supports 2 operations:
        - push -> adds an element to the end
        - pop -> takes an elemetn from the end
    - You can use push() & pop() methods to behave like a stack

Internals
---------
    - in the end they are still an Object, with "length" property, and other helper methods, where to access of a property is done by 
      using "[]"

    - the engine tries to store its elements in the contiguous memory area
    - there are other optimizations done by the engine, but they are not applyied if the array is used as a regular object

    .. code-block:: python
        :linenos:

        let fruits = []; // make an array

        fruits[99999] = 5; // assign a property with the index far greater than its length

        fruits.age = 25; // create a property with an arbitrary name

    - the ways to misuse an array:
        - add a non-numeric property like arr.test = 5
        - make holes , like arr arr[0] and then arr[1000]
        - fill the array in revers order, like arr[100], arr[99], ...

Performance
-----------
    - methods push/pop run fast, while shift/unshit are slow
    -is faster to work with the end of an array and not to the beginning of an array because working with begining of array implies:
        - to remove the element with the index 0
        - moving all elements to the left, renumbering their indexes
        - updating the length property

Loops
-----
    - one of the oldest ways to cycle array items is the "for" loop:

    .. code-block:: python
        :linenos:

        let arr = ["Apple", "Orange", "Pear"];

        for (let i = 0; i < arr.length; i++) {
            alert( arr[i] );
        }

    - you can use also "for..off":

    .. code-block:: python
        :linenos:

        let fruits = ["Apple", "Orange", "Plum"];

        // iterates over array elements
        for (let fruit of fruits) {
            alert( fruit );
        }

    - using "for of" will not give the index, so you could use also "for in":

    .. code-block:: python
        :linenos:

        let fruits = ["Apple", "Orange", "Plum"];

        for (let key in fruits) {
            alert( arr[key] ); // Apple, Orange, Pear
        }

    - this will make some other potential problems:
        - the lop "for in" iterates over all properties, not only the numeric one:
            - there are so-called "array-like" objects in the browser which are having hte length property, an indexes properties,
              but contains also other extra properties which can be of any other type

        - the for..in loop is optimized for generic objects, not arrays, and thus is 10-100 times slower

The lenght property
-------------------
    - the length property automatically ypdates when we modify the array
    - is not the number inside the array, bu the last index + 1

    .. code-block:: python
        :linenos:

        let fruits = [];
        fruits[123] = "Apple";

        alert( fruits.length ); // 124

    - you can change the value of length which will remove the rest of elements from array

    .. code-block:: python
        :linenos:

        let arr = [1, 2, 3, 4, 5];

        arr.length = 2; // truncate to 2 elements
        alert( arr ); // [1, 2]

        arr.length = 5; // return length back
        alert( arr[3] ); // undefined: the values do not return

new Array()
-----------
    - you can use the syntax "new Array()", but is prefered the one with "[]" as is shorter
    - passing only one number to the new syntax will create an array of that dimension and not array with one element(the number)

    .. code-block:: python
        :linenos:

        let arr = new Array(2); // will it create an array of [2] ?
        alert( arr[0] ); // undefined! no elements.
        alert( arr.length ); // length 2

:ref:`Go Back <javascript-types-label>`.