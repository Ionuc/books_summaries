.. _java-development-concurrency-scope-values-label:

ScopeValues
===========
- added in Java 21
- modern alternative to ThreadLocal
- binds values to execution flow, not to a thread
- safe with virtual threads (immune to parking/unparking issue)
- explicitly declared and confined ti lexial scope
- context follows execution, not thread identity

:ref:`Go Back <java-development-concurrency-label>`.