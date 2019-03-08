.. _react-forms-label:

Forms
=====
    - HTML form elements work a little bit differently from other DOM elements in React because form elements naturally keep spme internal state

Controlled Components
---------------------
    - in HTML, form elements such as <input>, <textarea> and <select> typicaly maintain their own state and update it based on user input
    - in React, mutable state is typically kep in the state property of components and only updated with setState()

    .. code-block:: python
        :linenos:

        class NameForm extends React.Component {
            constructor(props) {
                super(props);
                this.state = {value: ''};

                this.handleChange = this.handleChange.bind(this);
                this.handleSubmit = this.handleSubmit.bind(this);
            }

            handleChange(event) {
                this.setState({value: event.target.value});
            }

            handleSubmit(event) {
                alert('A name was submitted: ' + this.state.value);
                event.preventDefault();
            }

            render() {
                return (
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Name:
                            <input type="text" value={this.state.value}
                                onChange={this.handleChange} />
                        </label>
                        <input type="submit" value="Submit" />
                    </form>
                );
            }
        }

    - with a controlled component, every state mutation will have an associated handler function. This makes it straighforward
      to modify or validate user input

The textarea Tag
----------------
    - in HTML, <textarea> element defines its text by its children

    .. code-block:: python
        :linenos:

        <textarea>
            Hello there, this is some text in a text area
        </textarea>

    - in React, a <textarea> uses a value attribute instead

    .. code-block:: python
        :linenos:

        class EssayForm extends React.Component {
            constructor(props) {
                super(props);
                this.state = {
                    value: 'Please write an essay about your favorite DOM element.'
                };
                this.handleChange = this.handleChange.bind(this);
                this.handleSubmit = this.handleSubmit.bind(this);
            }

            handleChange(event) { ... }
            handleSubmit(event) { .. }

            render() {
                return (
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Essay:
                            <textarea value={this.state.value}
                                onChange={this.handleChange} />
                        </label>
                        <input type="submit" value="Submit" />
                    </form>
                );
            }
        }

The select Tag
--------------
    - in HTML, <select> creates a drop-down list, with or without a selected value

    .. code-block:: python
        :linenos:

        <select>
            <option value="grapefruit">Grapefruit</option>
            <option value="lime">Lime</option>
            <option selected value="coconut">Coconut</option>
            <option value="mango">Mango</option>
        </select>

    - React, instead of using this selected attribute, uses a value attribute on the root select tag

    .. code-block:: python
        :linenos:

        class FlavorForm extends React.Component {
            constructor(props) {
                super(props);
                this.state = {value: 'coconut'};

                this.handleChange = this.handleChange.bind(this);
                this.handleSubmit = this.handleSubmit.bind(this);
            }

            handleChange(event) {
                this.setState({value: event.target.value});
            }

            handleSubmit(event) {
                alert('Your favorite flavor is: ' + this.state.value);
                event.preventDefault();
            }

            render() {
                return (
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Pick your favorite flavor:
                            <select value={this.state.value} onChange={this.handleChange}>
                                <option value="grapefruit">Grapefruit</option>
                                <option value="lime">Lime</option>
                                <option value="coconut">Coconut</option>
                                <option value="mango">Mango</option>
                            </select>
                        </label>
                        <input type="submit" value="Submit" />
                    </form>
                );
            }
        }

    - you can pass an array into the value attribute, allowing you to select multiple options in a select tag:

    .. code-block:: python
        :linenos:

        <select multiple={true} value={['B', 'C']}>

Handling Multiple Inputs
------------------------
    - when you need to handle multiple controlled input elements, you can add a name attribute to each element and let the handler function choose what to do based on the value of event.target.name

    .. code-block:: python
        :linenos:

        class Reservation extends React.Component {
            constructor(props) {
                super(props);
                this.state = {
                    isGoing: true,
                    numberOfGuests: 2
                };

                this.handleInputChange = this.handleInputChange.bind(this);
            }

            handleInputChange(event) {
                const target = event.target;
                const value = target.type === 'checkbox' ? target.checked : target.value;
                const name = target.name;

                this.setState({
                    [name]: value
                });
            }

            render() {
                return (
                    <form>
                        <label>
                            Is going:
                            <input
                                name="isGoing"
                                type="checkbox"
                                checked={this.state.isGoing}
                                onChange={this.handleInputChange} />
                        </label>
                        <br />
                        <label>
                            Number of guests:
                            <input
                                name="numberOfGuests"
                                type="number"
                                value={this.state.numberOfGuests}
                                onChange={this.handleInputChange} />
                        </label>
                    </form>
                );
            }
        }

Fully-Fledged solutions
-----------------------
    - if you are looking for a complete solution including validation, keeping track of the visited fields and handling form submission,
      Formik is one of the popular choices:

        - https://jaredpalmer.com/formik/


:ref:`Go Back <react-label>`.