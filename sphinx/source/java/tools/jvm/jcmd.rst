.. _java-tools-jvm-jcmd-label:

JCMD
=====
- used to identify when any carrier threads are beig pinned
- enable native access : --enable-native-access=ALL-UNNAMED
- list carier threads currently pinned by VTs
- include stack trace for diagnosis


    .. code-block:: python
           :linenos:

            $ jcmd <PID> Thread.pin



:ref:`Go Back <java-tools-jvm-label>`.