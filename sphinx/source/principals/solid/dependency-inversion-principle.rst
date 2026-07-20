.. _interface-segregation-principle-label:

Depency Inversion Principle
===========================
- high level modules should not depend on low-level modules. Both should depend on abstractions
- abstractions should not depend upon details Details should depend upon abstractions


- Example:
    - we need to aggregate weather from Accuweather of BbcWeather. We migt create WeatherAggregator which aggregates the temperature. But the problem with the following code is that WeatherAggregator is using concret implementation. Adding new weather provider will imply to change the source code

    .. code-block:: python
       :linenos:

        public class AccuweatherApi {

            public int getTemperatureCelcius() {
                // TODO Auto-generated method stub
                return 0;
            }

        }

        public class BbcWeatherApi {

            public double getTemperatureFahrenheit() {
                // TODO Auto-generated method stub
                return 0;
            }

        }

        public class WeatherAggregator {
            private AccuweatherApi accuweather = new AccuweatherApi();
            private BbcWeatherApi bbcWeather = new BbcWeatherApi();
         
            public double getTemperature() {
                return (accuweather.getTemperatureCelcius() 
                        + toCelcius(
                                bbcWeather.getTemperatureFahrenheit())) / 2;
            }
         
            private double toCelcius(double temperatureFahrenheit) {
                return (temperatureFahrenheit - 32) / 1.8f;
            }
        }

    - solution: create abstractions: Crete WeatherSource interface with only one method, add implementations of it and use the abstractions in the aggregator

    .. code-block:: python
       :linenos:


        public interface WeatherSource {
            double getTemperatureCelcius();
        }

        public class AccuweatherApi implements WeatherSource {

            @Override
            public double getTemperatureCelcius() {
                return 30; // stub value and method for the sake of demo
            }
        }

        public class BbcWeatherApi implements WeatherSource {
            @Override
            public double getTemperatureCelcius() {
                return toCelcius(getTemperatureFahrenheit());
            }
         
            private double getTemperatureFahrenheit() {
                return 0; // stub method for the sake of the demo
            }
         
            private double toCelcius(double temperatureFahrenheit) {
                return (temperatureFahrenheit - 32) / 1.8f;
            }
        }

        public class WeatherAggregator {
            private WeatherSource[] weatherSources;
         
            public WeatherAggregator(WeatherSource[] weatherSources) {
                this.weatherSources = weatherSources;
            }
         
            public double getTemperature() {
                return Arrays.stream(weatherSources)
                    .mapToDouble(WeatherSource::getTemperatureCelcius)
                    .average()
                    .getAsDouble();
            }
        }


:ref:`Go Back <principals-solid-label>`.