.. _react-refs-and-DOM-label:

Refs and the DOM
================
    - refs provide a way to access DOM nodes or React elements created in the render method
    - normally, props are the only way that parent components interact with their chilren: to modify a child, you re-render it
      with new props

    - there are a few cases where you need to imperatively modify a child outside of the typical dataflow

    - refs should be use on:
        - managing focus, text selection or media playback
        - trigerring imperative animations
        - integrating with third-party DOM libraries

Creating refs
-------------
    - refs are created using "React.createRef()" and attached to React elements via the "ref" attribute
    - refs are commonly assigned to an instance property when a component is constructed so that can be referenced
      throughout the component

    .. code-block:: python
        :linenos:

        class MyComponent extends React.Component {
            constructor(props) {
                super(props);
                this.myRef = React.createRef();
            }
            render() {
                return <div ref={this.myRef} />;
            }
        }

Accessing Refs
--------------
    - when a ref is passed to an element in render, a reference to the node becomes accessible at the current "attribute" of 
      the ref

    .. code-block:: python
        :linenos:

        const node = this.myRef.current;

    - the value of the ref differes dependeing on the type of the node:
        - when ref attribute is used on HTML element, ref created with "React.createRef()" receives the underlying DOM element
          as its "current" property

        .. code-block:: python
            :linenos:

            class CustomTextInput extends React.Component {
                constructor(props) {
                    super(props);
                    // create a ref to store the textInput DOM element
                    this.textInput = React.createRef();
                    this.focusTextInput = this.focusTextInput.bind(this);
                }

                focusTextInput() {
                    // Explicitly focus the text input using the raw DOM API
                    // Note: we're accessing "current" to get the DOM node
                    this.textInput.current.focus();
                }

                render() {
                    // tell React that we want to associate the <input> ref
                    // with the `textInput` that we created in the constructor
                    return (
                        <div>
                            <input
                                type="text"
                                ref={this.textInput} />

                            <input
                                type="button"
                                value="Focus the text input"
                                onClick={this.focusTextInput}
                            />
                        </div>
                    );
                }
            }


        - when ref attrivute is used on custom class component, the ref object receives the mounted instance of the component
          as its "current" property

        .. code-block:: python
            :linenos:

            class AutoFocusTextInput extends React.Component {
                constructor(props) {
                    super(props);
                    this.textInput = React.createRef();
                }

                componentDidMount() {
                    this.textInput.current.focusTextInput();
                }

                render() {
                    return (
                        <CustomTextInput ref={this.textInput} />
                    );
                }
            }

    - you may not use the ref attribute on function components because they don't have instances

    .. code-block:: python
        :linenos:

        //This is wrong
        function MyFunctionComponent() {
            return <input />;
        }

        class Parent extends React.Component {
            constructor(props) {
                super(props);
                this.textInput = React.createRef();
            }
            render() {
                // This will *not* work!
                return (
                    <MyFunctionComponent ref={this.textInput} />
                );
            }
        }

    - you may use ref attribute inside a function component as long as you refer to a DOM element or a class component

    .. code-block:: python
        :linenos:

        function CustomTextInput(props) {
            // textInput must be declared here so the ref can refer to it
            let textInput = React.createRef();

            function handleClick() {
                textInput.current.focus();
            }

            return (
                <div>
                    <input
                        type="text"
                        ref={textInput} />

                    <input
                        type="button"
                        value="Focus the text input"
                        onClick={handleClick}
                    />
                </div>
            );
        }

Exposing DOM Refs to Parent Components
--------------------------------------
    - in rare case, you might want to have access to a child's DOM node from a parent component
    - while you can add a ref to the child component, this is not an ideal solution as you would only get a component instance
      rather than a DOM node

    - prefered solution is with ref forwarding

Callback Refs
-------------
    - React also supports another way t set refs called "callback refs"
    - instead of passing a "ref" attribute created by "createRef()", you pass a function
    - the function received the React component instance or HTML DOM element as its argument which can be stored and
      accessed elsewhere

    .. code-block:: python
        :linenos:

        class CustomTextInput extends React.Component {
            constructor(props) {
                super(props);

                this.textInput = null;

                this.setTextInputRef = element => {
                    this.textInput = element;
                };

                this.focusTextInput = () => {
                    // Focus the text input using the raw DOM API
                    if (this.textInput) this.textInput.focus();
                };
          }

            componentDidMount() {
                // autofocus the input on mount
                this.focusTextInput();
            }

            render() {
                // Use the `ref` callback to store a reference to the text input DOM
                // element in an instance field (for example, this.textInput).
                return (
                    <div>
                        <input
                            type="text"
                            ref={this.setTextInputRef}
                        />
                        <input
                            type="button"
                            value="Focus the text input"
                            onClick={this.focusTextInput}
                        />
                    </div>
                );
            }
        }

    - in the example above, React will call the ref callback with the DOM element when the component mounts and
      call it with null when it unmounts

    - refs are guarenteed to be up-to-date before componentdidMount() or componentDidUpdate() fires
    - you can pass callback refs between components like you can with object refs that were created with "React.createRef()"

    .. code-block:: python
        :linenos:

        function CustomTextInput(props) {
            return (
                <div>
                    <input ref={props.inputRef} />
                </div>
            );
        }

        class Parent extends React.Component {
            render() {
                return (
                    <CustomTextInput
                        inputRef={el => this.inputElement = el}
                    />
                );
            }
        }

Caveats with callback refs
--------------------------
    - if the ref callback is defined as an inline function, it will get called twice during updates, first with null and then
      again with the DOM element

    - this is because a new instance of the function is created with each render, so React needs to clear the old ref and set
      up the new one
:ref:`Go Back <react-label>`.