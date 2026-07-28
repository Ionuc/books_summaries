.. _java-development-concurrency-process-api-label:

Process API
===========

JAva 9 Improvements
-------------------

- new classes were created:
    - ProcessBuilder:
        - to create an instance of a Process
    - java.lang.ProcessHandle
        - provides methods to query information about the process, such as:
            - process Id
            - parent process
            - is alive
            - etc 


    .. code-block:: python
        :linenos:

        ProcessHandle self = ProcessHandle.current(); // The current method returns an object representing a process of currently running JVM
        long PID = self.getPid();
        ProcessHandle.Info procInfo = self.info(); // The Info subclass provides details about the process
         
        Optional<String[]> args = procInfo.arguments();
        Optional<String> cmd =  procInfo.commandLine();
        Optional<Instant> startTime = procInfo.startInstant();
        Optional<Duration> cpuUsage = procInfo.totalCpuDuration();


    .. code-block:: python
        :linenos:


        System.out.println("===== Process API Updates =====");
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        Process process = processBuilder.start();
        ProcessHandle processHandle = process.toHandle();

        // Get process information
        System.out.println("Process ID: " + processHandle.pid());
        System.out.println("Parent Process: " + processHandle.parent().get());
        System.out.println("Is process alive: " + processHandle.isAlive());

- destroying processes
    - 2 methods were added to terminate the associated process:
        - destroy():
            - sends a request to the process to terminate gracefully
        - destroForcibly():
            - attempts to forcibly terminate the process

    .. code-block:: python
        :linenos:

        childProc = ProcessHandle.current().children();
        childProc.forEach(procHandle -> {
            assertTrue("Could not kill process " + procHandle.getPid(), procHandle.destroy());
        });


:ref:`Go Back <java-development-concurrency-label>`.