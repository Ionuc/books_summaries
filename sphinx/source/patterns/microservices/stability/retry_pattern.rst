.. _retry-microservices-pattern-label:

Retry Pattern
=============
- different services often need to communicate and depend on each other
- Sometimes, requests between these services can fail due to temporary issues, like network glitches
- To handle such problems gracefully, the "Retry Pattern" is used
- This pattern involves automatically retrying a failed request a predetermined number of times before considering it a permanent failure


    .. image:: ../../../images/patterns/microservices/stability_patterns/retry_pattern/architectural_overview.png
        :align: center


How is working:
1. Initial Request: When a microservice makes a request to another service, it may encounter transient errors due to issues like network interruptions or momentary service unavailability.
2. Retry Logic: If the initial request fails, the Retry Pattern triggers a retry mechanism. This involves automatically resending the request a specified number of times. The retry attempts are made according to a defined strategy, which may include constant, incremental, or exponential backoff periods between retries.
3. Backoff Strategy: To prevent overwhelming the system or causing further issues, retries are spaced out using a backoff strategy. Constant backoff uses a fixed delay between retries, while exponential backoff increases the delay progressively with each retry. Jitter (randomized delay) can also be added to spread out retry attempts and reduce the risk of multiple services retrying simultaneously.
4. Retry Limits: There are usually limits set on the number of retry attempts to avoid endless retry loops. Once the maximum number of retries is reached, the system may log an error, trigger an alert, or take alternative actions, such as notifying a user or falling back to a different service.
5. Error Handling: If the retries still fail, the system may handle the error according to predefined rules, such as retrying at a later time, using cached data, or gracefully degrading service functionality.

Implementation
--------------
- Define Retry Policies
    - retry count: Set the maximum number of retry attempts before considering the operation as failed. Common choices are 3 to 5 retries
    - Backoff Strategy: Choose between constant, incremental, or exponential backoff for retry delays
    - Jitter: Add randomness to the backoff period to prevent synchronized retries from causing further issues
- Configure Retry Logic:
    - error identification: Determine which types of errors should trigger retries (e.g., network errors, timeouts) and which should not (e.g., authentication errors, data validation errors)
    - retry conditions: Implement logic to retry only when certain conditions are met, avoiding retries for cases where it is not appropriate
- Implement Expopnential Backoff:
    - Use exponential backoff to increase the wait time between retries, reducing the risk of overwhelming the service and allowing time for transient issues to resolve
- Incorporate Jitter:
    - Add jitter to the backoff period to randomize the retry intervals. This helps distribute retry attempts more evenly and prevents retries from clustering, which could exacerbate the problem
- Monitor and Log Retries
     - Logging: Implement comprehensive logging to track retry attempts, failures, and successful retries
     - Monitor: Use monitoring tools to observe the performance and impact of retries on the system
- Fallback Mechanism:
    - Design fallback mechanisms to handle cases where retries fail. This could involve alternative services, degraded functionality, or user notifications


Advantages
----------
- increase realiability
- improve fault tolerance: The pattern allows services to handle temporary failures gracefully
- reduce manual intervation
- resilience to fluctuations

Challanges
---------
- retry storms:
    - When many services retry simultaneously, it can lead to a surge of requests that exacerbates the problem rather than alleviating it
    - Use exponential backoff and jitter to spread out retry attempts and prevent simultaneous retries
- overloading Services
    - Frequent retries can put additional load on the affected service, potentially causing further degradation or failures
    - Implement rate limiting and circuit breakers alongside retries to manage load and avoid overwhelming services
- increased latency:
    - Retries can increase the overall latency of requests, as each retry adds additional delay
    - Balance retry attempts with acceptable latency thresholds and use appropriate backoff strategies to manage the delay impact
- complexity in configuration:
    - Configuring retries with appropriate limits, backoff strategies, and conditions can become complex and error-prone
- dependency management:
    - If a microservice relies on multiple other services, managing retries across all dependencies can become challenging and may require coordinated retry policies

:ref:`Go Back <stability_microservices_patterns-label>`.