.. _react-jsx-label:

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

Specifying the React Element Type
---------------------------------
    - the first part of a JSX tag determines the type of the React element
    - capitalized types indicate that the JSX is referring a React component, compiles to React.createelement, and correspond to a
      component defined or imported in your JavaScript file

    - lowercase types refers to built-in component like <div>, <span> and results in a string 'div' or 'span'
    - these tags get compiled intoa direct reference to the named variable, so if you use <Foo />, then Foo must be in scope
    - because JSX compiles into React.createElements() it means it is needed also the import for React

    .. code-block:: python
        :linenos:

        import React from 'react';
        import CustomButton from './CustomButton';

        function WarningButton() {
            // return React.createElement(CustomButton, {color: 'red'}, null);
            return <CustomButton color="red" />;
        }

    - you can refer to a React component using dot-notation from within JSX
    - it is convenient if you have a single module that exports many React components

    .. code-block:: python
        :linenos:

        import React from 'react';

        const MyComponents = {
            DatePicker: function DatePicker(props) {
                return <div>Imagine a {props.color} datepicker here.</div>;
            }
        }

        function BlueDatePicker() {
            return <MyComponents.DatePicker color="blue" />;
        }

    - using lowercase for React component will not run as expected:

    .. code-block:: python
        :linenos:

        import React from 'react';

        // Wrong! This is a component and should have been capitalized:
        function hello(props) {
            // Correct! This use of <div> is legitimate because div is a valid HTML tag:
            return <div>Hello {props.toWhat}</div>;
        }

        function HelloWorld() {
            // Wrong! React thinks <hello /> is an HTML tag because it's not capitalized:
            return <hello toWhat="World" />;
        }

    - to fix this, you should rename "hello" to "Hello":

    .. code-block:: python
        :linenos:

        import React from 'react';

        // Correct! This is a component and should be capitalized:
        function Hello(props) {
            // Correct! This use of <div> is legitimate because div is a valid HTML tag:
            return <div>Hello {props.toWhat}</div>;
        }

        function HelloWorld() {
            // Correct! React knows <Hello /> is a component because it's capitalized.
            return <Hello toWhat="World" />;
        }

Choosing the Type are Runtime
-----------------------------
    - you cannot use a general expression as the React element type

    .. code-block:: python
        :linenos:

        import React from 'react';
        import { PhotoStory, VideoStory } from './stories';

        const components = {
            photo: PhotoStory,
            video: VideoStory
        };

        function Story(props) {
            // Wrong! JSX type can't be an expression.
            return <components[props.storyType] story={props.story} />;
        }

    - in case you want to use a general expression, you can assign it to ca capitalized variable:

    .. code-block:: python
        :linenos:

        import React from 'react';
        import { PhotoStory, VideoStory } from './stories';

        const components = {
            photo: PhotoStory,
            video: VideoStory
        };

        function Story(props) {
            // Correct! JSX type can be a capitalized variable.
            const SpecificStory = components[props.storyType];
            return <SpecificStory story={props.story} />;
        }

Props in JSX
------------
    - you can pass any JavaSCript expression as a prop, by surrounding it with {}

    .. code-block:: python
        :linenos:

        <MyComponent foo={1 + 2 + 3 + 4} />

    - "if" and "for" statements cannot be used directly in JSX props, but you can use them in surrounding code

    .. code-block:: python
        :linenos:

        function NumberDescriber(props) {
            let description;
            if (props.number % 2 == 0) {
                description = <strong>even</strong>;
            } else {
                description = <i>odd</i>;
            }
            return <div>{props.number} is an {description} number</div>;
        }

    - you can pass a string literal as a prop. These 2 JSX expressions are equivalent

    .. code-block:: python
        :linenos:

        <MyComponent message="hello world" />

        <MyComponent message={'hello world'} />

    - if you pass no value for a prop, it defaults to "true". These 2 JSX expresssions are equivalent

    .. code-block:: python
        :linenos:

        <MyTextBox autocomplete />

        <MyTextBox autocomplete={true} />

    - it is not recommended using this because it can be confused with the ES8 object shortand "{foo}" which is short for "{foo: foo}"
    - if you have a props as an object, you can pass it in JSX by using "...". These 2 JSX expressions are equivalent

    .. code-block:: python
        :linenos:

        function App1() {
            return <Greeting firstName="Ben" lastName="Hector" />;
        }

        function App2() {
          const props = {firstName: 'Ben', lastName: 'Hector'};
          return <Greeting {...props} />;
        }

    - you can pick specific props that your component will consume while passing all other props:. This makes it easy to pass unnecessary props to
      components that don't care about them or to pass invalid HTML attributes to the DOM

    .. code-block:: python
        :linenos:

        const Button = props => {
            const { kind, ...other } = props;
            const className = kind === "primary" ? "PrimaryButton" : "SecondaryButton";
            return <button className={className} {...other} />;
        };

        const App = () => {
            return (
                <div>
                    <Button kind="primary" onClick={() => console.log("clicked!")}>
                        Hello World!
                    </Button>
                </div>
            );
        };

Children in JSX
---------------
    - in JSX expressions that contain both an opening tag and a closing tag, the content between those tags is passed a special
      prop: "props.children"

    - there are several ways to pass a children:
        - String literals:
            - you can put a string between the opening and closing tags and props.children will just be that string

            .. code-block:: python
                :linenos:

                <MyComponent>Hello world!</MyComponent>
                <div>This is valid HTML &amp; JSX at the same time.</div>

            - JSX removes whitespace at the beginning and ending of a line
            - JSX removed blank lines
        - JSX children
            - you can provide more JSX elements as the children
            - is useful for displaying nested components

            .. code-block:: python
                :linenos:

                <MyContainer>
                    <MyFirstComponent />
                    <MySecondComponent />
                </MyContainer>

    - you may mix together different types of children

    .. code-block:: python
        :linenos:

        <div>
            Here is a list:
            <ul>
                <li>Item 1</li>
                <li>Item 2</li>
            </ul>
        </div>

    - a React component can also return an array of elements:

    .. code-block:: python
        :linenos:

        render() {
            // No need to wrap list items in an extra element!
            return [
                // Don't forget the keys :)
                <li key="A">First item</li>,
                <li key="B">Second item</li>,
                <li key="C">Third item</li>,
            ];
        }

JavaScript Expressions as Children
----------------------------------
    - you can pass any JavaScript expression as children by enclosing it with "{}". Thee 2 JSX expressions are equivalent

    .. code-block:: python
        :linenos:

        <MyComponent>foo</MyComponent>

        <MyComponent>{'foo'}</MyComponent>

    - this is useful for rendering a list of JSX expression of arbitrary length

    .. code-block:: python
        :linenos:

        function Item(props) {
            return <li>{props.message}</li>;
        }

        function TodoList() {
            const todos = ['finish doc', 'submit pr', 'nag dan to review'];
            return (
                <ul>
                    {todos.map((message) => <Item key={message} message={message} />)}
                </ul>
            );
        }

    - JavaScript expressions can be mixed with other type of children

    .. code-block:: python
        :linenos:

        function Hello(props) {
            return <div>Hello {props.addressee}!</div>;
        }

Functions as children
---------------------
    - normally, JavaScript expressions inserted in JSX will evaluate to a string, a React element or a list of those things
    - however, props. children works just like any other prop, it can take a callback:

    .. code-block:: python
        :linenos:

        // Calls the children callback numTimes to produce a repeated component
        function Repeat(props) {
            let items = [];
            for (let i = 0; i < props.numTimes; i++) {
                items.push(props.children(i));
            }
            return <div>{items}</div>;
        }

        function ListOfTenThings() {
            return (
                <Repeat numTimes={10}>
                    {(index) => <div key={index}>This is item {index} in the list</div>}
                </Repeat>
            );
        }

    - chilren passed to a custom component can be anything, as long as that component transforms them into something React can understand
      before rendering

Booleans, Null and Undefined are ignored
----------------------------------------
    - false, null, undefined and true are valid children.
    - they simply don't render
    - these expressions will render the same thing

    .. code-block:: python
        :linenos:

        <div />
        <div></div>
        <div>{false}</div>
        <div>{null}</div>
        <div>{undefined}</div>
        <div>{true}</div>

    - this can be useful to conditionally render React elements

    .. code-block:: python
        :linenos:

        <div>
            {showHeader && <Header />} // Header will be render if showHeader is true
            <Content />
        </div>

    - one caveat is that "falsy" values, such as 0 number are still rendered by React
    - if you want a value like false, true, null or undefiend to appear in the output, you have to convert it to string

    .. code-block:: python
        :linenos:

        <div>
            My JavaScript variable is {String(myVariable)}.
        </div>

:ref:`Go Back <react-label>`.