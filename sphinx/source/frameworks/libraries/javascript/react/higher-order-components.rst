.. _react-higher-order-components-label:

Higher Order Components
=======================
    - a higher-order component (HOC) is an advanced technique in react for reusing component logic.
    - a HOC is a function that takes a component and returns a new component

    .. code-block:: python
        :linenos:

        const EnhancedComponent = higherOrderComponent(WrappedComponent);

    - whereas a component transforms props in UI, a higher-order component transforms a component into another component

Use HOCs for Cross-Cutting Concerns
-----------------------------------
    - let's say we have a CommentList component that subscribes to an external data source to render a list of comments

    .. code-block:: python
        :linenos:

        class CommentList extends React.Component {
            constructor(props) {
                super(props);
                this.handleChange = this.handleChange.bind(this);
                this.state = {
                    // "DataSource" is some global data source
                    comments: DataSource.getComments()
                };
          }

            componentDidMount() {
                // Subscribe to changes
                DataSource.addChangeListener(this.handleChange);
            }

            componentWillUnmount() {
                // Clean up listener
                DataSource.removeChangeListener(this.handleChange);
            }

            handleChange() {
                // Update component state whenever the data source changes
                this.setState({
                    comments: DataSource.getComments()
                });
            }

            render() {
                return (
                    <div>
                        {this.state.comments.map((comment) => (
                            <Comment comment={comment} key={comment.id} />
                        ))}
                    </div>
                );
            }
        }

    - and later you will create a new component for subscribing toa single blog post, which follows a similar pattern:

    .. code-block:: python
        :linenos:

        class BlogPost extends React.Component {
            constructor(props) {
                super(props);
                this.handleChange = this.handleChange.bind(this);
                this.state = {
                    blogPost: DataSource.getBlogPost(props.id)
                };
            }

            componentDidMount() {
                DataSource.addChangeListener(this.handleChange);
            }

            componentWillUnmount() {
                DataSource.removeChangeListener(this.handleChange);
            }

            handleChange() {
                this.setState({
                    blogPost: DataSource.getBlogPost(this.props.id)
                });
            }

            render() {
                return <TextBlock text={this.state.blogPost} />;
            }
        }

    - Componentlist and BlogPost aren't identicall: they call different methods on DataSource, and they render different
      output. But much of their implementaton is the same:

        - on mount, add a change listener to DataSource
        - inside the listener, call setState when the data source changes
        - on unmount, remove the change listener
    - you can reuse code with HOC like:

    .. code-block:: python
        :linenos:

        const CommentListWithSubscription = withSubscription(
            CommentList,
            (DataSource) => DataSource.getComments()
        );

        const BlogPostWithSubscription = withSubscription(
            BlogPost,
            (DataSource, props) => DataSource.getBlogPost(props.id)
        );

        // This function takes a component...
        function withSubscription(WrappedComponent, selectData) {
            // ...and returns another component...
            return class extends React.Component {
                constructor(props) {
                    super(props);
                    this.handleChange = this.handleChange.bind(this);
                    this.state = {
                        data: selectData(DataSource, props)
                    };
                }

                componentDidMount() {
                    // ... that takes care of the subscription...
                    DataSource.addChangeListener(this.handleChange);
                }

                componentWillUnmount() {
                    DataSource.removeChangeListener(this.handleChange);
                }

                handleChange() {
                    this.setState({
                        data: selectData(DataSource, this.props)
                    });
                }

                render() {
                    // ... and renders the wrapped component with the fresh data!
                    // Notice that we pass through any additional props
                    return <WrappedComponent data={this.state.data} {...this.props} />;
                }
            };
        }

    - the wrapped component recieved all the props of the container, along with a new prop "data", which is used to render
      its output

    - the HOC isn't concerned with how or why the data is used
    - the wrapped component isn't concerned with where the data come from

Don't Mutate the original Component. Use Composition
----------------------------------------------------

    .. code-block:: python
        :linenos:

        // Wrong way
        function logProps(InputComponent) {
            InputComponent.prototype.componentWillReceiveProps = function(nextProps) {
                console.log('Current props: ', this.props);
                console.log('Next props: ', nextProps);
            };
            // The fact that we're returning the original input is a hint that it has
            // been mutated.
            return InputComponent;
        }

        // EnhancedComponent will log whenever props are received
        const EnhancedComponent = logProps(InputComponent);

    - instead of mutation, HOC should use composition ny wrapping the input component in a container component

    .. code-block:: python
        :linenos:

        function logProps(WrappedComponent) {
            return class extends React.Component {
                componentWillReceiveProps(nextProps) {
                    console.log('Current props: ', this.props);
                    console.log('Next props: ', nextProps);
                }
                render() {
                    // Wraps the input component in a container, without mutating it. Good!
                    return <WrappedComponent {...this.props} />;
                }
            }
        }

    - you may noticed similarities between HOCs and a pattern called "container components"
    - container components are part of a strategy of separating responsibility between high-level and low-level concerns
    - containers manage things like subscriptions and state, and pas props to components that handle things like rendering UI
    - HOCs use containers as part of their implementation
    - you can think of HOCs as parameterized container component definitions

Convention: Pass Unrelated Props Through to the Wrapped Component
-----------------------------------------------------------------
    - HOCs add features to a component
    - they shouldn't drastically alter its contract
    - it's expected that the component returned from a HOC has a similar interface to the wrapped component
    - HOCs should pass through props that are unrelated to its pecific concern:

    .. code-block:: python
        :linenos:

        render() {
            // Filter out extra props that are specific to this HOC and shouldn't be
            // passed through
            const { extraProp, ...passThroughProps } = this.props;

            // Inject props into the wrapped component. These are usually state values or
            // instance methods.
            const injectedProp = someStateOrInstanceMethod;

            // Pass props to wrapped component
            return (
                <WrappedComponent
                    injectedProp={injectedProp}
                    {...passThroughProps}
                />
            );
        }

Convention: Maximizing Composability
------------------------------------
    - not all the HOCs look the same
    - sometimes they accept only a single argument, the wrapped component:

    .. code-block:: python
        :linenos:

        const NavbarWithRouter = withRouter(Navbar);

    - Usuallym HOCs accept additional arguments:

    .. code-block:: python
        :linenos:

        const CommentWithRelay = Relay.createContainer(Comment, config);

    - The most common signature for HOOCs looks like:

    .. code-block:: python
        :linenos:

        // React Redux's `connect`
        const ConnectedComment = connect(commentSelector, commentActions)(CommentList);

    - this is breaked apart into :

    .. code-block:: python
        :linenos:

        // connect is a function that returns another function
        const enhance = connect(commentListSelector, commentListActions);
        // The returned function is a HOC, which returns a component that is connected
        // to the Redux store
        const ConnectedComment = enhance(CommentList);

    - functions whose output type is the same as its input type are really easy to compose together

Convention: Wrap the Display Name for Easy Debugging
----------------------------------------------------
    - to easy debugging, choose a display name that communicates that it's the result of a HOOC
    - the most common technique is to wrap the display name of the wrapped component
    - if the HOC component is "withSubscription" and wrapped component is "CommentList", use the display name
      "WithSubscription(CommentList)"

    .. code-block:: python
        :linenos:

        function withSubscription(WrappedComponent) {
            class WithSubscription extends React.Component {/* ... */}
            WithSubscription.displayName = `WithSubscription(${getDisplayName(WrappedComponent)})`;
            return WithSubscription;
        }

        function getDisplayName(WrappedComponent) {
            return WrappedComponent.displayName || WrappedComponent.name || 'Component';
        }

Caveats
-------
    - Don't use HOCs inside the render method:
        - React's diffing algorithm (named reconciliation) uses compopnent identiy to determine whether it should
          update the existing dubtree or thrown it away and mount a new one

        - if the component returned from "render" is identical (===) to the component from the previous render, React
          recursively updates the subtree by deffing it with the new one. If they are not equal, the previous subtree is 
          unmounted completely

        .. code-block:: python
            :linenos:

            render() {
                // A new version of EnhancedComponent is created on every render
                // EnhancedComponent1 !== EnhancedComponent2
                const EnhancedComponent = enhance(MyComponent);
                // That causes the entire subtree to unmount/remount each time!
                return <EnhancedComponent />;
            }

        - the problem here isn;t jsut about performance, but remounting a compoment causes the state of that
          component and all of its children to be lost

        - instead, apply HOCs outside the component definition so that the resulting component is created only once
        - in case where you need to apply a HOC dynamically, you can do it inside a component's lifecycle methods or
          its constructor

    - static method must be copied over
        - when you apply a HOC to a component, the original component is wrapped with a container component. That means
          the new component does not have any of the static methods of the original component

        .. code-block:: python
            :linenos:

            // Define a static method
            WrappedComponent.staticMethod = function() {/*...*/}
            // Now apply a HOC
            const EnhancedComponent = enhance(WrappedComponent);

            // The enhanced component has no static method
            typeof EnhancedComponent.staticMethod === 'undefined' // true

        - to solve this, you could copy the methods onto the container before returning it:
            - either one by one

            .. code-block:: python
                :linenos:

                function enhance(WrappedComponent) {
                    class Enhance extends React.Component {/*...*/}
                    // Must know exactly which method(s) to copy :(
                    Enhance.staticMethod = WrappedComponent.staticMethod;
                    return Enhance;
                }

            - or predefined hoistNonReactStatic
            - or to export the static method separately from the component itself

            .. code-block:: python
                :linenos:

                // Instead of...
                MyComponent.someFunction = someFunction;
                export default MyComponent;

                // ...export the method separately...
                export { someFunction };

                // ...and in the consuming module, import both
                import MyComponent, { someFunction } from './MyComponent.js';

Refs Aren't passed through automatically
----------------------------------------
    - while the convention for higher-order components is to pass through all props to the wrapped component,
      this does not work for refs, because rf is not really a prop, it is handled specially be React

    - the solution is to use "React.forwardRef" API

:ref:`Go Back <react-label>`.