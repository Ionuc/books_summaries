.. _javascript-this-label:

This
====
    - the value of "this" is evaluated during the run-time and it can be anything
    - the same function may have different "this" when called from different objects

    .. code-block:: python
        :linenos:

        let user = { name: "John" };
        let admin = { name: "Admin" };

        function sayHi() {
          alert( this.name );
        }

        // use the same functions in two objects
        user.f = sayHi;
        admin.f = sayHi;

        // these calls have different this
        // "this" inside the function is the object "before the dot"
        user.f(); // John  (this == user)
        admin.f(); // Admin  (this == admin)

        admin['f'](); // Admin (dot or square brackets access the method – doesn't matter)

    - in JavaScript, "this" is "free", its value is evaluated at call-time and does not depend on where the method was
      declared, but rather on what's the object "before the dot"

    - the concept of run-time evaluated "this" has bot pluses and minuses:
        - a function can be reused for different objects
        - greater flexibility opens a place for mistakes

Internals: Reference Type
-------------------------
    - an intricate method call can lose "this"

    .. code-block:: python
        :linenos:

        let user = {
            name: "John",
            hi() { alert(this.name); },
            bye() { alert("Bye"); }
        };

        user.hi(); // John (the simple call works)

        // now let's call user.hi or user.bye depending on the name
        (user.name == "John" ? user.hi : user.bye)(); // Error! this is undefined

    - the last line is not working because "this" is undefined
    - the last line is translated to :

    .. code-block:: python
        :linenos:

        let user = {
            name: "John",
            hi() { alert(this.name); }
        }

        // split getting and calling the method in two lines
        let hi = user.hi;
        hi(); // Error, because this is undefined

    - the user.hi function is stored in a variable, and on the last line it is completely standalone, and so there's no "this"
    - to make "user.hi()" work, JavaScript uses a trick - the dot '.' returns not a function, but a value of the special 
      Rerefence Type

    - a reference type is a special type
    - we cannot use use it explicitly, but it is used internally by the language
    - a reference type is a three-value combination (base, name, strict), where:
        - base is the object
        - name is the property
        - strict is true if "use strict" is in effect
    - the result of a property access "user.hi" is not a function, but a value of Refenrece Type
    - for "user.hi" on stric mode it is : "(user, "hi", true)"
    - when parentheses "()" are called on the REference Type, they receive the full information about the object and its 
      method and can set the rigth "this=user" (in previous case)

    - any other operation like assignment "hi = user.hi" discards the reference type as a whole, takes the value of "user.h1"
      (function) and passes it on. So any further operation loses "this"

    - so the value of "this" is only passed right if the function is called directly using dot : "obj.method()" or square
      brackets : "obj['method']"

Arrow functions have no "this"
------------------------------
    - they don't have their own this
    - if we reference "this" from such a function, it's taken from the outer "normal" function

    .. code-block:: python
        :linenos:

        let user = {
            firstName: "Ilya",
            sayHi() {
                let arrow = () => alert(this.firstName);
                arrow();
            }
        };

        user.sayHi(); // Ilya

:ref:`Go Back <javascript-label>`.