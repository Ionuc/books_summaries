.. _javascript-general-label:

General
=======

Objects
-------
    - Everything in Javascript is Object except string, number, boolean, null and undefined
    - Objects are collections of properties
    - Declared with the object literal notation
    - There is not class construct

    .. code-block:: python
       :linenos:

       var empty = {};
       write(empty.toString())

       var tim = new Object();
       tim.name = "Tim Bob";
       write(tim.name);

       // object literal
       var john = {
         name: "John Blaine",
         age: 21
       };

       write(john.name);
       write(john.age);
       write(john.toString());

       write([1,2,3].toString());
       write(/\d/.toString()); //output is the regular expresion
       write(false.toString());
       write(write.toString()); // output is the content of the function
       john.toString = function(){
         return "Name: " + this.name + " ,Age: " + this.age;
       }
       write(john.toString())

       // nested objects
       var person = {
         address: {
           number : 1,
           street : 'brunswick'
         }
       }

       write(person.address)
       write(person.address.number)


    .. code-block:: python
       :linenos:

       Output
       [object Object]
       Tim Bob
       John Blaine
       21
       [object Object]
       1,2,3
       /\d/
       false
       function write(message) { document.getElementById('message').innerHTML += message + '
       '; }
       Name: John Blaine ,Age: 21
       [object Object]
       1

Equality
--------
    - Objects are only equal to themself
    - Primitives are equals if the values match
    - Two sets of equality operators (== and ===)
        - The different is type coercion
        - == attempt to coerce the 2 operands into the same type before comparing
        - Is very bad thing to use == or != but you should use === or !== which is not
          doing type coercion because it might have the same value, but different type


    .. code-block:: python
       :linenos:

       // objecst are equal to themself
       var joe = {name: "Joeseph"};
       write("joe equals joe : " + (joe === joe));

       // objects are not equal to other objects with the same values
       var joe2 = {name: "Joeseph"};
       write("joe equals joe2: " + (joe === joe2))

       //pritives types are equal of their values match
       write(" apple == apple: "+ ("apple" == "apple"))
       write(" apple === apple: "+ ("apple" === "apple"))

       write(" apple == cat: "+ ("apple" == "cat"))
       write(" apple === cat: "+ ("apple" === "cat"))

       //inequality 
       write ("1 != 2: " + (1 != 2))
       write ("1 !== 2: " + (1 !== 2))

       // the ==operator
       write('1 == "1": '+ (1 =="1"))
       write('1 === "1": '+ (1 ==="1"))

       write('"" == 0: ' + ("" == 0))
       write('"" === 0: ' + ("" === 0))

    .. code-block:: python
       :linenos:

       Output:
       joe equals joe : true
       joe equals joe2: false
       apple == apple: true
       apple === apple: true
       apple == cat: false
       apple === cat: false
       1 != 2: true
       1 !== 2: true
       1 == "1": true
       1 === "1": false
       "" == 0: true
       "" === 0: false


:ref:`Go Back <javascript-label>`.