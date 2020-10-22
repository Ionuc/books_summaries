.. _java9-process-api:

Process API Improvements
========================

    - new classes were created:
        - java.lang.ProcessHandle
        - java.lang.ProcessHandle.Info

    .. code-block:: python
        :linenos:

        ProcessHandle currentProcess = ProcessHandle.current();
​       System.out.println("Current Process Id: = " + currentProcess.getPid());

:ref:`Go Back <java9-label>`.