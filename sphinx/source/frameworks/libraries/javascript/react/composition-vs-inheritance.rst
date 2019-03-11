.. _react-composition-inheritance-label:

Composition vs Inheritance
==========================
    - in React it is recommended using composition instead of inheritance

Containment
-----------
    - some components don't know their children ahead of time, for examples Sidebar or Dialog that
      represents generic "boxes"

    - it is recommended that such components use the special "children" prop to pass children elements directly into their output

    .. code-block:: python
        :linenos:

        function FancyBorder(props) {
            return (
                <div className={'FancyBorder FancyBorder-' + props.color}>
                    {props.children}
                </div>
            );
        }

        function WelcomeDialog() {
            return (
                <FancyBorder color="blue">
                    <h1 className="Dialog-title">
                        Welcome
                    </h1>
                    <p className="Dialog-message">
                        Thank you for visiting our spacecraft!
                    </p>
                </FancyBorder>
            );
        }

    - anything inside the <FancyBorder> JSX tag gets passed into the FancyBorder component as a children prop.
    - if you want, you can use another property name, but you will be forced to set the value for it on the parent component

Specialization
--------------
    - for cases when one component is a special case of another component, like WelcomeDialog is a special case for Dialog,
      React recommends to use the composition like:

    .. code-block:: python
        :linenos:

        function Dialog(props) {
            return (
                <FancyBorder color="blue">
                    <h1 className="Dialog-title">
                        {props.title}
                    </h1>
                    <p className="Dialog-message">
                        {props.message}
                    </p>
                </FancyBorder>
            );
        }

        function WelcomeDialog() {
            return (
                <Dialog
                    title="Welcome"
                    message="Thank you for visiting our spacecraft!" />
            );
        }

:ref:`Go Back <react-label>`.