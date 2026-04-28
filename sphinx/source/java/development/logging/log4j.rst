.. _java-development-logging-log4j-label:

Log4j
=====
- key elements:
    - Loggers
    - Appenders
    - Layout


    .. image:: ../../../images/java/development/logging/log4j-key-elements.png
        :align: center


Loggers
-------
- are log message destinations
- each logger is independently configurable as to what level of logging it currently logs
- can send log message to multiple appenders


Appender
--------
- make the actual output
- there are multiple appenders:
    - file appender
    - rolling file appender:
    - console appender
    - socket appender
    - syslog appender
    - smtp appender

- log4j 2 added apenders that write to:
    - Apache Flume
    - the Java persistence API
    - Apache Kafka
    - NoSQL
    - memory mapped files
    - randome access files
    - Zeromq endpoints

- multiple appenders can be attached to any logger

- RollingFileAppender:
    - it creates new files based on a policy and not log all in one file
    - triggering policies:
        - determines when the log file is rolled, meaning a new file is created
        - values:
            - CompositeTriggerPolicy:
                - combine multiple trigger policies
            - CronTriggerPolicy:
                - rollover is triggered based on a cron expression
                - the policy is controlled by a timer and is asynchrounous to processing log events
                - it is possible that log eents from a previous or next time period may appear at the beginning or end of the log file
                - the filePattern attribute shoud contain a timestamp, otherwise the target file will be overwritten on each rollover
            - OnStartupTriggerPolicy:
                - a new log file is created every time the JVM starts
            - SizeBasedTriggerPolicy:
                - the file is rolled when it reaches a certian size
            - TimeBasedTriggerPolicy:
                - the log file is rolled based on a date/time pattern
                - it rollover once the date/time pattern no longer applied to the active file:
                - for example "yyy-MM-dd-HH" will rollover every hour while "yyy-MM-dd" will rollover every day
    - rollover strategies:
        - determines how the file is rolled
        - values:
            - default rollover strategy:
                - accepts both a date time form and an integer from a file pattern attribute
            - directWrite rollover strategy:
                - causes log events to be written directly to files represented by the file pattern
                - file renamed are not performed
                - if the size based triggering policies causes multiple files to be written during the specified time period, they will be numbered starting at one and continually incremented until a time based rollover occurs
    - there are 2 actions which can be done at rollover:
        - delete on rollover:
            - log4j 2 introduces a Delete action that gives users more controll over what files are deleted at rollover
            - lets users configure one or more conditions that select the files to delete relative to a base directory
        - adding custom file attribute:
            - it gives users more controll over which file attribute permissions, owner and group should be applied
            - lets users configure one or more conditions that select the elgible files relative to a base directory


- log4j2 provides async logging
    - to enable async loggin, you need to add Lmax disruptor library to your build.gradle
    - AsyncRoot element will enable the async logging


Layouts
-------
- are used to format log entries
- there are multiple layouts:
    - Pattern lauout
    - HTML layout
    - XML layout

- log4j 2 added new layouts for:
    - CSV
    - JSON
    - YAML

Log levels
----------
- Java log levels:
    - OFF:
        - the highest possible rank and is intended to turn off logging
    - FATAL:
        - severe errors that cause premature termination
    - ERROR:
        - other runtime errors or unexpected conditions
    - WARN:
        - use of deprecated APIs, "almost" errors
    - INFO: 
    - DEBUG: details information of the flow through the system
    - TRACE: most details information


:ref:`Go Back <java-development-logging-label>`.