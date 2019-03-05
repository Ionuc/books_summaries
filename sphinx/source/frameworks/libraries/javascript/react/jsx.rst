.. _frameworks-libraries-javascript-jest-label:

JSX
=====
    - is a syntax extension to JavaScript
    - produces React "elements"
    - instead of artificially separating technologies by putting markup and logic in separate files,
      React separates concerns with loosely coupled units called "components" that contain both.

Embedding Expressions in JSX
----------------------------
    - it can be places anny valid JavaScript expression inside the curly braces in JSX

    .. code-block:: python
           :linenos:

           const name = 'Josh Perez';
           const element = <h1>Hello, {name}</h1>;


JSX is an Expression too
------------------------
    - after compilation, JSX expressions become regular JavaScript functions calls and evaluate to JavaScript objects.
      This means you can use JSX inside of if statements and for loops, assign it to variables, accept it as arguments and return it from functions

    .. code-block:: python
           :linenos:

           function getGreeting(user) {
               if (user) {
                  return <h1>Hello, {formatName(user)}!</h1>;
               }
               return <h1>Hello, Stranger.</h1>;
           }

Specifying Children with JSX
----------------------------
    - if a tag is empty, you may close it immediately with />, like XML

    .. code-block:: python
           :linenos:

           const element = <img src={user.avatarUrl} />;

    - JSX tags may contain children:

     .. code-block:: python
           :linenos:

           const element = (
               <div>
                   <h1>Hello!</h1>
                   <h2>Good to see you here.</h2>
               </div>
           );

JSX prevents injection Attacks
------------------------------
    - it is safe to embed user input in JSX
    - by default, React DOM excapes any values embedded in JSX before rendering them
    - everythinh is converted to a string before being rendered. This helps prevent XSS(cross-site-scripting) attacks

JSX Represents Objects
----------------------
    - Babel compiles JSX down to React.createElements() calls
    - these 2 examples are the same:

         .. code-block:: python
           :linenos:

           const element = (
               <h1 className="greeting">
                   Hello, world!
               </h1>
           );


         .. code-block:: python
           :linenos:

           const element = React.createElement(
               'h1',
               {className: 'greeting'},
               'Hello, world!'
           );

    - in the end, it is created an object likes this :


         .. code-block:: python
           :linenos:

           // Note: this structure is simplified
           const element = {
               type: 'h1',
               props: {
                   className: 'greeting',
                   children: 'Hello, world!'
               }
           };

    - these elements are called "React elements"
    - react reads these objects and uses them to construct the DOM and keep it up to date 

:ref:`Go Back <react-label>`.