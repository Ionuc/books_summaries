.. _react-elements-label:

State & Lifecycle
=================
State
-----
    - state is similar to props, but it is private and fully controlled by the component
    - this is available only for class component

Lifecycle
---------
    - React provides different hooks when the component is in one state or another
    - there is :
        - hook when the component is rendered to the DOM for the first time : componentDidMount
        - hook when the component is removed from the DOM : componentWillUnmount

    .. code-block:: python
        :linenos:

        class Clock extends React.Component {
          constructor(props) {
            super(props);
            this.state = {date: new Date()};
          }

          componentDidMount() {
            this.timerID = setInterval(
              () => this.tick(),
              1000
            );
          }

          componentWillUnmount() {
            clearInterval(this.timerID);
          }

          tick() {
            this.setState({
              date: new Date()
            });
          }

          render() {
            return (
              <div>
                <h1>Hello, world!</h1>
                <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
              </div>
            );
          }
        }

        ReactDOM.render(
          <Clock />,
          document.getElementById('root')
        );

Using state correctly
---------------------
    - do not modify state directly:
        - this will not re-render a component:

        .. code-block:: python
            :linenos:

            // Wrong
            this.state.comment = 'Hello';

        - instead, use setState()

        .. code-block:: python
            :linenos:

            // Correct
            this.setState({comment: 'Hello'});

        - the only place where you can assign this.state is the constructor

    - state updates may be asynchrounous
        - React may batch multiple setState() calls into a single update for performance
        - because this.props and this.state may be updated asynchronously, you should not rely on their values for calculating the next state

        .. code-block:: python
            :linenos:

            // Wrong, this code may fail to update the counter
            this.setState({
                counter: this.state.counter + this.props.increment,
            });


        .. code-block:: python
            :linenos:

            // Correct
            this.setState((state, props) => ({
                counter: state.counter + props.increment
            }));
            // OR
            this.setState(function(state, props) {
                return {
                    counter: state.counter + props.increment
                };
            });

    - state updates are merged
        - when you call setState(), React merges the object you provide into the current state

The Data flows down
-------------------
    - neither parent nor child components can know if a certain component is stateful or stateless
    - they shouldn't care whether it is defined as a function or a class
    - the state is often called local or encapsulated. It is not accessible to any component other than the one that owns and sets it
    - a component may choose to pass its state down as props to its child component but the cild component would not know whether it came from the
      props or from the state
    - this is commonly called a "top-down" or "unidirectional" data flow. Any state is always owned by some specific component, and any data or UI
      derived from that state can only affect components "below" them in the tree.

:ref:`Go Back <react-label>`.