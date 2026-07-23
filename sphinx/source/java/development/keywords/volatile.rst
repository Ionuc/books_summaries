.. _java-development-keywords-volatile-label:

Volatile keyword
================
- the order in which instructions are written is not always the same as the order in which instructions are executed by JVM because JVM might try yo optimize the execution
- volatile keyword:
    - tells JVM that it is needed to be kept the order of instructions and reordering won't apply to such variables
    - to flash any updates t these variables instantly to make updates visible to all other threads


:ref:`Go Back <java-development-label>`.