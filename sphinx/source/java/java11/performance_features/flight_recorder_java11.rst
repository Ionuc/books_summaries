.. _java11_flyght_recorder:

Flight Recorder
===============

    - java Flight Recorder (JFR) is now open-source in Open JDK, whereas it used to be a commercial product in Oracle JDK. JFR is a profiling tool that we can use to gather diagnostics and profiling data from a running Java application.
    - to start a 120 seconds JFR recording, we can use the following parameter:



    .. code-block:: python
           :linenos:

            -XX:StartFlightRecording=duration=120s,settings=profile,filename=java-demo-app.jfr



    - we can use JFR in production since its performance overhead is usually below 1%. Once the time elapses, we can access the recorded data saved in a JFR file; however, in order to analyze and visualize the data, we need to make use of another tool called JDK Mission Control (JMC).


:ref:`Go Back <java11-performance-features-label>`.