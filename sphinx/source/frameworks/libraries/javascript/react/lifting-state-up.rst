.. _react-lifting-state-label:

Lifting State Up
================
    - often, several components need to reflect the same changing data
    - it is recommended lifting the shared state up to their closest common ancestor
    - if a parent component has 2 children and it wants to shync the state of those 2 children then:
        - the parent should keep the common state
        - the parent state should be passed as props to the children
        - children should use that props instead of their own state
        - parent should provide as props to children the method which will update the state
        - children should use the method sent as props when the input value was changed in order to update the parent state
            - which will update the props for both children


    .. code-block:: python
        :linenos:

        class TemperatureInput extends React.Component {
            constructor(props) {
                super(props);
                this.handleChange = this.handleChange.bind(this);
            }

            handleChange(e) {
                this.props.onTemperatureChange(e.target.value);
            }

            render() {
                const temperature = this.props.temperature;
                const scale = this.props.scale;
                return (
                  <fieldset>
                    <legend>Enter temperature in {scaleNames[scale]}:</legend>
                    <input value={temperature}
                           onChange={this.handleChange} />
                  </fieldset>
                );
            }
        }

        class Calculator extends React.Component {
            constructor(props) {
                super(props);
                this.handleCelsiusChange = this.handleCelsiusChange.bind(this);
                this.handleFahrenheitChange = this.handleFahrenheitChange.bind(this);
                this.state = {temperature: '', scale: 'c'};
            }

            handleCelsiusChange(temperature) {
                this.setState({scale: 'c', temperature});
            }

            handleFahrenheitChange(temperature) {
                this.setState({scale: 'f', temperature});
            }

            render() {
                const scale = this.state.scale;
                const temperature = this.state.temperature;
                const celsius = scale === 'f' ?
                    tryConvert(temperature, toCelsius) : temperature;
                const fahrenheit = scale === 'c' ?
                    tryConvert(temperature, toFahrenheit) : temperature;

                return (
                <div>
                    <TemperatureInput
                        scale="c"
                        temperature={celsius}
                        onTemperatureChange={this.handleCelsiusChange} />

                    <TemperatureInput
                        scale="f"
                        temperature={fahrenheit}
                        onTemperatureChange={this.handleFahrenheitChange} />

                    <BoilingVerdict
                        celsius={parseFloat(celsius)} />

                    </div>
                );
            }
        }

        function BoilingVerdict(props) {
            if (props.celsius >= 100) {
                return <p>The water would boil.</p>;
            }
            return <p>The water would not boil.</p>;
        }


:ref:`Go Back <react-label>`.