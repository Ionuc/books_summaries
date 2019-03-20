.. _react-error-boundaries-label:

Error Boundaries
================
    - error boundaries are React components that catch JavaScript errors anywhere in their child component tree,
      log those errors and display a fallback UI instead of the component tree that crashed.

    - error boundaries catch errors during rendering, in lifecycle methods and in constructors of the whole tree below them
    - do not catch errors for:
        - event handlers
        - asynchronous code ( setTimeout or requestAnimationFrame callbacks)
        - server side rendering
        - errors thrown in the error boundary itself (rather than its children)
    - a class component becomes an error boundary if it defines either:
        - of the lifecycle methods "static getDerrivedSateFromError()":
            - use this method to render a fallback UI after an error hos been thrown
        - or of the lifecycle method : "componentDidCatch()":
            - use this to log error information

    .. code-block:: python
        :linenos:

        class ErrorBoundary extends React.Component {
            constructor(props) {
                super(props);
                this.state = { hasError: false };
            }

            static getDerivedStateFromError(error) {
                // Update state so the next render will show the fallback UI.
                return { hasError: true };
            }

            componentDidCatch(error, info) {
                // You can also log the error to an error reporting service
                logErrorToMyService(error, info);
            }

            render() {
                if (this.state.hasError) {
                    // You can render any custom fallback UI
                    return <h1>Something went wrong.</h1>;
                }

                return this.props.children; 
            }
        }

        // To be used:
        <ErrorBoundary>
            <MyWidget />
        </ErrorBoundary>

    - error boundaries work like a JavaScript "catch {}" block, but for components
    - only class components can be error boundaries
    - error boundaries only catch errors in the components below them in the tree
    - if an error boundaries fails trying to render the error message, the error will propagate to the closes error
      boundary above it

New Behavior for Uncaught Errors
--------------------------------
    - as of React 16, errors that were not caught by any error boundary will result in unmounting of the whole React
      component tree.

How abount try/catch ?
----------------------
    - try / catch is great but it only works for imperative code
    - however, React components are declarative and specify what should be rendered: "<Button />"

    .. code-block:: python
        :linenos:

        try {
            showButton();
        } catch (error) {
            // ...
        }

How Abount Event Handlers ?
---------------------------
    - error boundaries do not catch errors inside event handlers
    - react does not need to recover from errors in event handlers because event handlers don't happen during rendering. So
      if they throw, React still knows what to display on the screen.

    - if you need to catch an error inside event handler, use the regular JavaSCript "try / catch" statement

    .. code-block:: python
        :linenos:

        class MyComponent extends React.Component {
            constructor(props) {
                super(props);
                this.state = { error: null };
                this.handleClick = this.handleClick.bind(this);
            }

            handleClick() {
                try {
                // Do something that could throw
                } catch (error) {
                    this.setState({ error });
                }
            }

            render() {
                if (this.state.error) {
                    return <h1>Caught an error.</h1>
                }
                return <div onClick={this.handleClick}>Click Me</div>
            }
        }


:ref:`Go Back <react-label>`.