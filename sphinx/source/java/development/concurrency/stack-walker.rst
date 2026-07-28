.. _java-development-concurrency-stack-walker-label:

StackWalker
===========
- added in Java 9
- provides a way to traverse the stack frames of a thread
- allows to inspect and manipulate the frames at runtime
- replace old sun reflect refleton Getcoller class method
- is intendend for diagnostic and monitoring purposes because it impacts the performance
- is used in profilers to inspect the frames

- classes:
    - StackFrame
        - represents a single frame
        - has methods to retrieve information about the class method and other details of the frame
    - StackWalker
        - is the main class
        - method
            - wals():
                - used to iterate the frames


    .. code-block:: python
       :linenos:

        // Walk the stack frames and print information
        List<String> frameInfoList = stackWalker.walk(frames -> {
            List<String> frameInfo = frames.map(StackFrame::toString)
                                          .collect(Collectors.toList());

            System.out.println("Stack Frames:");
            frameInfo.forEach(System.out::println);
            return frameInfo;
        });

:ref:`Go Back <java-development-concurrency-label>`.