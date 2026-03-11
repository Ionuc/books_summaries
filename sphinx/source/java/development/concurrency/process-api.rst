.. _java-development-concurrency-process-api:

Process API
===========

JAva 9 Improvements
-------------------

    - new classes were created:
        - java.lang.ProcessHandle
        - java.lang.ProcessHandle.Info


    .. code-block:: python
        :linenos:

        ProcessHandle self = ProcessHandle.current(); // The current method returns an object representing a process of currently running JVM
        long PID = self.getPid();
        ProcessHandle.Info procInfo = self.info(); // The Info subclass provides details about the process
         
        Optional<String[]> args = procInfo.arguments();
        Optional<String> cmd =  procInfo.commandLine();
        Optional<Instant> startTime = procInfo.startInstant();
        Optional<Duration> cpuUsage = procInfo.totalCpuDuration();


    - destroying processes


    .. code-block:: python
        :linenos:

        childProc = ProcessHandle.current().children();
        childProc.forEach(procHandle -> {
            assertTrue("Could not kill process " + procHandle.getPid(), procHandle.destroy());
        });


:ref:`Go Back <java-development-concurrency-label>`.