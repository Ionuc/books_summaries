.. _javascript-type-objects-iterables-label:

Iterables
=========
    - is a generalization of arrays
    - is a concept of allowing any object to be used in a "for..of" loop
    - not only arrays are iterable, but also other types like string

Symbol.iterator
---------------
    - to make any object iterable we need to add a method to the object named "Symbol.iterator"
    - when for..of starts:
        - it calls that method once (or errors if not found)
        - the method must return an iterator - an object with the methdo next
        - when for..of want the next value, it calls "next()" on that object
        - the result of next() must have the form {done: Boolean, value: any}, where done=true means 
          that the iteration is finished, otherwise value must be the new value

    .. code-block:: python
        :linenos:

        let range = {
            from: 1,
            to: 5
            };

        // 1. call to for..of initially calls this
        range[Symbol.iterator] = function() {

            // ...it returns the iterator object:
            // 2. Onward, for..of works only with this iterator, asking it for next values
            return {
                current: this.from,
                last: this.to,

                // 3. next() is called on each iteration by the for..of loop
                next() {
                    // 4. it should return the value as an object {done:.., value :...}
                    if (this.current <= this.last) {
                        return { done: false, value: this.current++ };
                    } else {
                        return { done: true };
                    }
                }
            };
        };

        // now it works!
        for (let num of range) {
            alert(num); // 1, then 2, 3, 4, 5
        }

    - from the above implementation it can be see that separation of concerns:
        - the range itself does not have the next() method
        - instead, another object, a so-called "iterator" is created by the call of range[Symbol.iterator]() and it
          handles the whole iteration

    - so the iteration object is separte frrom the object it iterates over
    - if we want to change the range object, we can do it like:

    .. code-block:: python
        :linenos:

        let range = {
            from: 1,
            to: 5,

            [Symbol.iterator]() {
                this.current = this.from;
                return this;
            },

            next() {
                if (this.current <= this.to) {
                    return { done: false, value: this.current++ };
                } else {
                    return { done: true };
                }
            }
        };

        for (let num of range) {
            alert(num); // 1, then 2, 3, 4, 5
        }

String as iterable
------------------
    - for a string, for..of loops over its characters

    .. code-block:: python
        :linenos:

        for (let char of "test") {
            // triggers 4 times: once for each character
            alert( char ); // t, then e, then s, then t
        }

    - it works also for surrogate pairs

    .. code-block:: python
        :linenos:

        let str = '𝒳😂';
        for (let char of str) {
            alert( char ); // 𝒳, and then 😂
        }

Calling an iterator explicitly
------------------------------
    - normally this hidden and the user will use it by for..of
    - but if you want to call it directly, it can be done:

    .. code-block:: python
        :linenos:

        let str = "Hello";

        // does the same as
        // for (let char of str) alert(char);

        let iterator = str[Symbol.iterator]();

        while (true) {
            let result = iterator.next();
            if (result.done) break;
            alert(result.value); // outputs characters one by one
        }

Iterables and array-like
------------------------
    - it must be done a clear distinction between:
        - iterables -> which are objects that implement the Symbol.iterator method
        - array-likes -> which are objects that have indexes and length, so they look like arrays
    - objects can be one of these 2, or both, or none

Arrays.from
-----------
    - this method converts an iterable or array-like to a real array

:ref:`Go Back <javascript-types-objects-label>`.