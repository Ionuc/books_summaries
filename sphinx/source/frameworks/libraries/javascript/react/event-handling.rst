.. _react-event-handling-label:

Handling Events
===============
    - handling events with React elements is very similar to handling events on DOM elements
    - React events are named using camelCase, rather than lowercase
    - with JSX you pass a function as the event handler, rather than a string

    - for example, the HTML

    .. code-block:: python
        :linenos:

        <button onclick="activateLasers()">
            Activate Lasers
        </button>

    - is slightly different in React:

    .. code-block:: python
        :linenos:

        <button onClick={activateLasers}>
            Activate Lasers
        </button>

    - another difference is that you cannot return false to prevent default behavior in React. You must call preventDefault() method explicitly
        - for example in HMTL you can have:

        .. code-block:: python
            :linenos:

            <a href="#" onclick="console.log('The link was clicked.'); return false">
                Click me
            </a>

        - in react you need to call it explicitly

        .. code-block:: python
            :linenos:

            function ActionLink() {
                function handleClick(e) {
                    e.preventDefault();
                    console.log('The link was clicked.');
                }

            return (
                <a href="#" onClick={handleClick}>
                    Click me
                </a>
            );}

    - then using React, you should generally not need to call addEventListener to add listeners to a DOM element after it is created.
      Insteaad, just provide a listener when the element is initially rendered
    - when you define a component using ES6 class, a common pattern is for an event handler to be a method on the class:
        - you need to bind the method to the class in the constructor in order to for "this" to not be "undefined" when the function
          is called

Binding methods
---------------
    - it is part of how functions wirk in JavaScript, not necesasry specific to React
    - generally, if you refer to a method without "()" after it, such as "onClick={this.handleClick}", you should bind thi method
    - you can use:
        - class fields to correctly bind callbacks:

        .. code-block:: python
            :linenos:

            class LoggingButton extends React.Component {
              // This syntax ensures `this` is bound within handleClick.
              // Warning: this is *experimental* syntax.
              handleClick = () => {
                console.log('this is:', this);
              }

              render() {
                return (
                  <button onClick={this.handleClick}>
                    Click me
                  </button>
                );
              }
            }

        - arraw function in the callback:
            - the problem is different callback is created each time the LoggingButton renderes.
            - if this callback is passed as a prop to lower components, those components might do an extra re-rendering

        .. code-block:: python
            :linenos:

            class LoggingButton extends React.Component {
              handleClick() {
                console.log('this is:', this);
              }

              render() {
                // This syntax ensures `this` is bound within handleClick
                return (
                  <button onClick={(e) => this.handleClick(e)}>
                    Click me
                  </button>
                );
              }
            }

Passing arguments to Event Handlers
-----------------------------------
    - if you want to pass an extra paramenter to an event handler (like and id), you can use:
        - arraw functions:
            - e represent the React event

        .. code-block:: python
            :linenos:

            <button onClick={(e) => this.deleteRow(id, e)}>Delete Row</button>

        - Function.prototype.bind:
            - e represent the React event and it will be passed as the second parameter
            - any further arguments are automatically forwarded

        .. code-block:: python
            :linenos:

            <button onClick={this.deleteRow.bind(this, id)}>Delete Row</button>


:ref:`Go Back <react-label>`.