.. _react-components-label:

Components & Props
==================
    - components let you split the UI into independent, reusable pieces
    - makes you think about each piece in insolation
    - components are like JavaScript functions: they accept arbitrary inputs (called "props") and return React element
    - you can create components:
        - as a simple JavaScript function, called "function components"

        .. code-block:: python
            :linenos:

            function Welcome(props) {
                return <h1>Hello, {props.name}</h1>;
            }

        - using ES6 class:

        .. code-block:: python
            :linenos:

            class Welcome extends React.Component {
                render() {
                    return <h1>Hello, {this.props.name}</h1>;
                }
            }

    - elements can also represent user-defined components:

    .. code-block:: python
        :linenos:

        const element = <Welcome name="Sara" />;

Composing Components
--------------------
    - components can refer to other components in their output
    - This lets us use the same component abstraction for any level of detail

    .. code-block:: python
        :linenos:

        function Welcome(props) {
          return <h1>Hello, {props.name}</h1>;
        }

        function App() {
          return (
            <div>
              <Welcome name="Sara" />
              <Welcome name="Cahal" />
              <Welcome name="Edite" />
            </div>
          );
        }

        ReactDOM.render(
          <App />,
          document.getElementById('root')
        );

Props are Read-Only
-------------------
    - whether you declare a component as a function or a class, it must never modify its own props
    - All React components must act like pure functions with respect to their props.

:ref:`Go Back <react-label>`.