.. _circuit_breaker-microservices-pattern-label:

Circuite Breaker Pattern
========================
- is like a safety switch for microservices
- A circuit breaker temporarily blocks access to a faulty service after it detects failures.
- This action prevents repeated unsuccessful attempts so that the system can recover effectively


States
------
- The Circuit Breaker pattern typically operates in three main states: Closed, Open, and Half-Open

1. Closed State
- In the Closed state, the circuit breaker operates normally, allowing requests to flow through between services.
- During this phase, the circuit breaker monitors the health of the downstream service by collecting and analyzing metrics such as response times, error rates, or timeouts.

2. Open State
- When the monitored metrics breach predetermined thresholds, signaling potential issues with the downstream service, the circuit breaker transitions to the Open state.
- In the Open state, the circuit breaker immediately stops forwarding requests to the failing service, effectively isolating it.
- This helps prevent cascading failures and maintains system stability by ensuring that clients receive timely feedback, even when services encounter issues.

3. Half-Open State
- After a specified timeout period in the Open state, transitions to Half-Open state.
- Allows a limited number of trial requests to pass through to the downstream service.
- Monitors responses to determine service recovery.
- If trial requests succeed, indicating service recovery, transitions back to Closed state.
- If trial requests fail, service issues persist.
- May transition back to Open state or remain in Half-Open state for further evaluation.


    .. image:: ../../../images/patterns/microservices/stability_patterns/circuit_breaker/state-flows.png
        :align: center

Challenges
----------
- Exception handling:
    - An application that invokes an operation through a circuit breaker must be able to handle the exceptions if the operation is unavailable
    - Exception management is based on the application
- Types of exceptions:
    - The reasons for a request failure can vary in severity
    - For example, a request might fail because a remote service crashes and requires several minutes to recover, or because an overloaded service causes a time-out
    - A circuit breaker might be able to examine the types of exceptions that occur and adjust its strategy based on the nature of these exceptions
- Monitoring:
    - A circuit breaker should provide clear observability into both failed and successful requests so that operations teams can assess system health
- Recoverability:
    - You should configure the circuit breaker to match the likely recovery pattern of the operation that it protects
    - For example, if the circuit breaker remains in the Open state for a long period, it can raise exceptions even if the reason for the failure is resolved
    - Similarly, a circuit breaker can fluctuate and reduce the response times of applications if it switches from the Open state to the Half-Open state too quickly
- Failed operations testing:
    - In the Open state, rather than using a timer to determine when to switch to the Half-Open state, a circuit breaker can periodically ping the remote service or resource to determine whether it's available
    - This ping can either attempt to invoke a previously failed operation or use a special health-check operation that the remote service provides
- Manual override:
    - If the recovery time for a failing operation is extremely variable, you should provide a manual reset option that enables an administrator to close a circuit breaker and reset the failure counter
    - Similarly, an administrator can force a circuit breaker into the Open state and restart the time-out timer if the protected operation is temporarily unavailable
- Concurrency:
    - A large number of concurrent instances of an application can access the same circuit breaker
    - The implementation shouldn't block concurrent requests or add excessive overhead to each call to an operation
- Resource differentiation:
    - Be careful when you use a single circuit breaker for one type of resource if there might be multiple underlying independent providers
    - For example, in a data store that contains multiple shards, one shard might be fully accessible while another experiences a temporary problem
    - If the error responses in these scenarios are merged, an application might try to access some shards even when failure is likely
    - And access to other shards might be blocked even though it's likely to succeed
- Accelerated circuit breaking:
    - Sometimes a failure response can contain enough information for the circuit breaker to trip immediately and stay tripped for a minimum amount of time
    - For example, the error response from a shared resource that's overloaded can indicate that the application should instead try again in a few minutes, instead of immediately retrying
- Multiregion deployments:
    - You can design a circuit breaker for single region or multiregion deployments
    - To design for multiregion deployments, use global load balancers or custom region-aware circuit breaking strategies that help ensure controlled failover, latency optimization, and regulatory compliance
- Service mesh circuit breakers:
    - You can implement circuit breakers at the application layer or as a cross-cutting, abstracted feature
    - For example, service meshes often support circuit breaking as a sidecar or as a standalone capability without modifying application code
- Failed request replay:
    - In the Open state, rather than failing immediately, a circuit breaker can record the details of each request in a journal and arrange for these requests to be replayed when the remote resource or service becomes available
- Inappropriate time-outs on external services:
    - A circuit breaker might not fully protect applications from failures in external services that have long time-out periods

Advantages
----------
- Improves fault tolerance by isolating failing dependencies.
- Monitors latency, error rate, and timeouts over a rolling window.
- Prevents cascades by temporarily stopping calls to an unhealthy service.
- Supports fallbacks (default responses, cached data, queueing) for graceful degradation.
- Auto-recovers by probing and resetting when the service stabilizes.


:ref:`Go Back <stability_microservices_patterns-label>`.