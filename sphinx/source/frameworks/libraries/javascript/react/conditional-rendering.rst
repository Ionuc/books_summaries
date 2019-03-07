.. _react-conditional-rendering-label:

Conditional Rendering
=====================
    - in React, depending on the state of your application, you can render only some components
    - conditional rendering in React works the same way conditions work in JavaSCript

    - in this example, depending of isLoggedIn prop, it will render one of another component

    .. code-block:: python
        :linenos:

        function Greeting(props) {
            const isLoggedIn = props.isLoggedIn;
            if (isLoggedIn) {
                return <UserGreeting />;
            }
            return <GuestGreeting />;
        }

        ReactDOM.render(
            // Try changing to isLoggedIn={true}:
            <Greeting isLoggedIn={false} />,
            document.getElementById('root')
        );

Element Variable
----------------
    - you can use variables to store elements
    - this can help you conditionally render a part of the component while the rest of the output doesn't change

    .. code-block:: python
        :linenos:

        class LoginControl extends React.Component {
            constructor(props) {
                super(props);
                this.handleLoginClick = this.handleLoginClick.bind(this);
                this.handleLogoutClick = this.handleLogoutClick.bind(this);
                this.state = {isLoggedIn: false};
            }

            handleLoginClick() {
                this.setState({isLoggedIn: true});
            }

            handleLogoutClick() {
                this.setState({isLoggedIn: false});
            }

            render() {
                const isLoggedIn = this.state.isLoggedIn;
                let button;

                if (isLoggedIn) {
                    button = <LogoutButton onClick={this.handleLogoutClick} />;
                } else {
                    button = <LoginButton onClick={this.handleLoginClick} />;
                }

                return (
                    <div>
                    <Greeting isLoggedIn={isLoggedIn} />
                    {button}
                    </div>
                );
            }
        }

        ReactDOM.render(
            <LoginControl />,
            document.getElementById('root')
        );

Inline if with Logical && Operator
----------------------------------
    - you may embed any expressions in JSX by wrapping them in curly braces
    - in JavaScript, true && expression always evaluates to expression, and false && expression always evaluates to false

    .. code-block:: python
        :linenos:

        function Mailbox(props) {
            const unreadMessages = props.unreadMessages;
            return (
                <div>
                <h1>Hello!</h1>
                {unreadMessages.length > 0 &&
                    <h2>
                        You have {unreadMessages.length} unread messages.
                    </h2>
                }
                </div>
            );
        }

        const messages = ['React', 'Re: React', 'Re:Re: React'];
        ReactDOM.render(
            <Mailbox unreadMessages={messages} />,
            document.getElementById('root')
        );

Inline If-Else with Conditional Operator
----------------------------------------
    - it can be used Condition ? true : false

    .. code-block:: python
        :linenos:

        render() {
            const isLoggedIn = this.state.isLoggedIn;
            return (
                 <div>
                    The user is <b>{isLoggedIn ? 'currently' : 'not'}</b> logged in.
                </div>
            );
        }

Preventing a Component from Rendering
-------------------------------------
    - if you want to prevent from rendering, you can return null


    .. code-block:: python
        :linenos:

        function WarningBanner(props) {
            if (!props.warn) {
                return null;
            }

            return (
                <div className="warning">
                    Warning!
                </div>
            ;
        }


:ref:`Go Back <react-label>`.