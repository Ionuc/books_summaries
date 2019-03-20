.. _react-context-label:

Context
=======
    - in a typical React application, data is passed tow-down (parent to child) via props
    - this can lead to propagate the same props more layers down
    - context provides a way to share values between components without having to explicitly pass a props through
      every level of the tree

When to use context
-------------------
    - context is designed to share data that can be considered "global" for a tree of React components such as
      current authenticated user, theme, or preferred language

    .. code-block:: python
        :linenos:

        // example without Context
        class App extends React.Component {
            render() {
                return <Toolbar theme="dark" />;
            }
        }

        function Toolbar(props) {
            // The Toolbar component must take an extra "theme" prop
            // and pass it to the ThemedButton. This can become painful
            // if every single button in the app needs to know the theme
            // because it would have to be passed through all components.
            return (
                <div>
                    <ThemedButton theme={props.theme} />
                </div>
            );
        }

        class ThemedButton extends React.Component {
            render() {
                return <Button theme={this.props.theme} />;
            }
        }


    .. code-block:: python
        :linenos:

        // Context lets us pass a value deep into the component tree
        // without explicitly threading it through every component.
        // Create a context for the current theme (with "light" as the default).
        const ThemeContext = React.createContext('light');

        class App extends React.Component {
            render() {
                // Use a Provider to pass the current theme to the tree below.
                // Any component can read it, no matter how deep it is.
                // In this example, we're passing "dark" as the current value.
                return (
                    <ThemeContext.Provider value="dark">
                        <Toolbar />
                    </ThemeContext.Provider>
                );
            }
        }

        // A component in the middle doesn't have to
        // pass the theme down explicitly anymore.
        function Toolbar(props) {
              return (
                    <div>
                        <ThemedButton />
                    </div>
              );
        }

        class ThemedButton extends React.Component {
              // Assign a contextType to read the current theme context.
              // React will find the closest theme Provider above and use its value.
              // In this example, the current theme is "dark".
              static contextType = ThemeContext;
              render() {
                    return <Button theme={this.context} />;
              }
        }

Before you Use Context
----------------------
    - context is primarily used when some data needs to be accessible by many components at different nesting levels
    - this can lead to components difficult to reuse
    - if you only want to avoid passing some props thorugh many levels, component composition is often a simple solution
      than context (for example: instead of passing the props, pass the entire React element which uses the props)


API
---
    - React.createContext:
        - creates a context object
        - when React renders a component that subscribes to this Context object it will read the current context value from
          closest matching Provider above it in the tree

        - a defaultValue argument is only used then a component does not have a matching Provider above it in the tree

        .. code-block:: python
            :linenos:

            const MyContext = React.createContext(defaultValue);

    - Context.Provider
        - every Context object comes with a Provider React component that allows consuming componenents to subscribe to
          context changes

        - accepts a "value" prop to be passed to consuming components
        - one provider can be connected to many consumers
        - providers can be nested to provider override values deeper within the tree
        - all consumers that are descendants of a Provider will re-render whenever the Provider's value prop changes
        - propagation from Provider to its consumers is not subject to the "shouldComponentUpdate()" method, so the
          consumer is updated even when an ancestor component bails out of the update

        - changes are determined by comparing the new value to the old using Object.is:
            - this can cause issues when passing objects as value

        .. code-block:: python
            :linenos:

            MyContext.Provider value={/* some value */}>

    - Class.contextType
        - the contextType property pn a class can be assigned a Context object created by React.createContext()
        - this lets you consume the neares current alue of the Context type using "this.context"
        - you can reference this in any of the lifecycle including the render function
        - you can only subscribe to a single context using this API

        .. code-block:: python
            :linenos:

            class MyClass extends React.Component {
                componentDidMount() {
                    let value = this.context;
                    /* perform a side-effect at mount using the value of MyContext */
                }
              componentDidUpdate() {
                    let value = this.context;
                    /* ... */
              }
              componentWillUnmount() {
                    let value = this.context;
                    /* ... */
              }
              render() {
                    let value = this.context;
                    /* render something based on the value of MyContext */
              }
            }
            MyClass.contextType = MyContext;

    - Context.Consumer
        - a React component that subscribes to context changes
        - this lets you subscribe to a context within a function component
        - requires a function as a child which received the current context value and returns a React node.

        .. code-block:: python
            :linenos:

            <MyContext.Consumer>
                {value => /* render something based on the context value */}
            </MyContext.Consumer>

Updating Context from a Nested Component
----------------------------------------
    - you can pass a function down throught the context to allow consumers to update the context

    .. code-block:: python
        :linenos:

        export const ThemeContext = React.createContext({
            theme: themes.dark,
            toggleTheme: () => {},
        });

    .. code-block:: python
        :linenos:
       
        import {ThemeContext} from './theme-context';

        function ThemeTogglerButton() {
            // The Theme Toggler Button receives not only the theme
            // but also a toggleTheme function from the context
            return (
                <ThemeContext.Consumer>
                    {({theme, toggleTheme}) => (
                        <button
                            onClick={toggleTheme}
                            style={{backgroundColor: theme.background}}>
                            Toggle Theme
                            </button>
                    )}
                </ThemeContext.Consumer>
            );
        }

        export default ThemeTogglerButton;

Consuming Multiple Contexts
---------------------------

    .. code-block:: python
        :linenos:
       
        // Theme context, default to light theme
        const ThemeContext = React.createContext('light');

        // Signed-in user context
        const UserContext = React.createContext({
            name: 'Guest',
        });

        class App extends React.Component {
            render() {
                const {signedInUser, theme} = this.props;

                // App component that provides initial context values
                return (
                    <ThemeContext.Provider value={theme}>
                        <UserContext.Provider value={signedInUser}>
                            <Layout />
                        </UserContext.Provider>
                    </ThemeContext.Provider>
                );
            }
        }

        function Layout() {
            return (
                <div>
                    <Sidebar />
                    <Content />
                </div>
            );
        }

        // A component may consume multiple contexts
        function Content() {
            return (
                <ThemeContext.Consumer>
                    {theme => (
                        <UserContext.Consumer>
                            {user => (
                                <ProfilePage user={user} theme={theme} />
                            )}
                    </UserContext.Consumer>
                )}
                </ThemeContext.Consumer>
            );
        }

Caveats
-------
    - context uses reference identity to determine when to re-render
    - this might imply to unintentional renders in consumers when a provider's parent renders

    .. code-block:: python
        :linenos:

        // This will re-render all consumers every time the Provider re-renders because a new object is always
        // created for "value"
        class App extends React.Component {
            render() {
                return (
                    <Provider value={{something: 'something'}}>
                        <Toolbar />
                    </Provider>
                );
            }
        }

    .. code-block:: python
        :linenos:

        class App extends React.Component {
            constructor(props) {
                super(props);
                this.state = {
                    value: {something: 'something'},
                };
            }

            render() {
                return (
                    <Provider value={this.state.value}>
                        <Toolbar />
                    </Provider>
                );
            }
        }

:ref:`Go Back <react-label>`.