.. _javascript-functions-rest-params-spread-label:


Rest parameters and spread operator
===================================
Rest parameters
---------------
    - a function can be valled with any number of arguments, no matter how it is defined
    - you can have specific parameters as arguments, but also you can use the extra parameter provided to the function
    - the rest parameters must be at the end

    .. code-block:: python
       :linenos:

        function showName(firstName, lastName, ...titles) {
            alert( firstName + ' ' + lastName ); // Julius Caesar

            // the rest go into titles array
            // i.e. titles = ["Consul", "Imperator"]
            alert( titles[0] ); // Consul
            alert( titles[1] ); // Imperator
            alert( titles.length ); // 2
        }

        showName("Julius", "Caesar", "Consul", "Imperator");

The "arguments" variable
------------------------
    - Contains the functions parameters
    - Indexed like an array
    - Has a length property
    - you can access the arguments from a special array-like object named "arguments"
    - the arguments does not support array methods, like .map()
    - arraw- function do not have "arguments". In case it is used, it will take the arguments from the our "nomarl" function

    .. code-block:: python
       :linenos:

        function showName() {
            alert( arguments.length );
            alert( arguments[0] );
            alert( arguments[1] );

            // it's iterable
            // for(let arg of arguments) alert(arg);
        }

        // shows: 2, Julius, Caesar
        showName("Julius", "Caesar");

        // shows: 1, Ilya, undefined (no second argument)
        showName("Ilya");

Spread operator
---------------
    - we sow how we can get an array from list of parameters, but sometimes we need the revers: how to get a list from an array
    - it is used with 3 dots '...'

    .. code-block:: python
       :linenos:

        alert( Math.max(3, 5, 1) ); // 5
        let arr = [3, 5, 1];
        alert( Math.max(arr) ); // NaN
        alert( Math.max(...arr) ); // 5 (spread turns array into a list of arguments)

    - spread operator can be used also with normal values

    .. code-block:: python
       :linenos:

        let arr1 = [1, -2, 3, 4];
        let arr2 = [8, 3, -8, 1];

        alert( Math.max(1, ...arr1, 2, ...arr2, 25) ); // 25

    - spread operator can be used to merge arrays:

    .. code-block:: python
       :linenos:

        let arr = [3, 5, 1];
        let arr2 = [8, 9, 15];

        let merged = [0, ...arr, 2, ...arr2];

        alert(merged); // 0,3,5,1,2,8,9,15 (0, then arr, then 2, then arr2)

    - the spread operator operates only on iterables.


:ref:`Go Back <javascript-functions-label>`.