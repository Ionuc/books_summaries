.. _react-forwarding-refs-label:

Forwarding Refs
================
    - ref forwarding is atechnique for automatically pass a ref through a component to one of its children
    - this is not necessary for most components in the app

Forwarding refs to DOM Components
---------------------------------
    - there are cases when parent components needs to acces the components rendered inside their children
    - in this case the children needs to set a reference to that component and the parent to access it using that reference
    
    .. code-block:: python
        :linenos:

        const FancyButton = React.forwardRef((props, ref) => (
            <button ref={ref} className="FancyButton">
                {props.children}
            </button>
        ));

        // You can now get a ref directly to the DOM button:
        const ref = React.createRef();
        <FancyButton ref={ref}>Click me!</FancyButton>;

    - the second ref argument only exists when you define a component with React.forwardRef call
    - ref forwarding is not limited to DOM components, you can forward refs to class component instances too

Forwarding refs in higher-order components
------------------------------------------
   - this technique is useful with higher-order components 

    .. code-block:: python
        :linenos:

        class FancyButton extends React.Component {
            focus() {
                // ...
            }

            // ...
        }

        // Rather than exporting FancyButton, we export LogProps.
        // It will render a FancyButton though.
        export default logProps(FancyButton);

        // parent component
        import FancyButton from './FancyButton';

        const ref = React.createRef();

        // The FancyButton component we imported is the LogProps HOC.
        // Even though the rendered output will be the same,
        // Our ref will point to LogProps instead of the inner FancyButton component!
        // This means we can't call e.g. ref.current.focus()
        <FancyButton
            label="Click Me"
            handleClick={handleClick}
            ref={ref}
        />;

    - in the example above, the ref is not pointing the FancyButton, but the LogProps
    - we can explicitly forward refs to the inner component using "React.forwardRef" API

    .. code-block:: python
        :linenos:

        function logProps(Component) {
            class LogProps extends React.Component {
                componentDidUpdate(prevProps) {
                    console.log('old props:', prevProps);
                    console.log('new props:', this.props);
                }

                render() {
                    const {forwardedRef, ...rest} = this.props;

                    // Assign the custom prop "forwardedRef" as a ref
                    return <Component ref={forwardedRef} {...rest} />;
                }
            }

            // Note the second param "ref" provided by React.forwardRef.
            // We can pass it along to LogProps as a regular prop, e.g. "forwardedRef"
            // And it can then be attached to the Component.
            return React.forwardRef((props, ref) => {
                return <LogProps {...props} forwardedRef={ref} />;
            });
        }

:ref:`Go Back <react-label>`.