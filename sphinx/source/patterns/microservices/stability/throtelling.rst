.. throtelling-microservices-pattern-label:

Throtelling
===========
- is the practice of controlling the rate at which requests are processed or resources are allocated within a system
- Instead of allowing an unlimited number of requests, throttling manages the rate at which requests are processed to ensure that the system remains stable and doesn't get overwhelmed
- It doesn't block requests entirely but slows down or queues them, allowing the system to handle them gradually over time
- The primary goal is to prevent overloading services, ensure equitable resource distribution, and maintain overall system stability
- In essence, throttling helps to manage demand and prevent a single component from becoming a bottleneck or causing service degradation

- prevents overloading: By limiting the number of requests or resource usage, throttling helps avoid overloading services, which can lead to crashes or significant slowdowns
- maintain fariness: It ensures that resources are shared equitably among different users or services, preventing any single entity from monopolizing resources
- improve performance: Throttling can help in maintaining consistent performance levels by controlling spikes in demand and distributing load evenly
- enhance resilience: It contributes to system resilience by mitigating the impact of unexpected traffic surges or resource consumption, thus improving overall system reliability

Throtelling Strategies
----------------------
- Rate Limiting:
    - Rate limiting involves restricting the number of requests that can be made to a service within a specific time period. This can be implemented using a variety of algorithms
    - types:
        - T0ken Bucket
        - Leaky Bucket
        - Fixed Window
        - Sliding Windows
- Load Shedding:
    - involves intentionally dropping or delaying requests when the system is under heavy load
    - This strategy helps in preserving the stability of the system by prioritizing critical operations over less important ones
- Circuit Breaker:
    - involves monitoring the health of a service and temporarily stopping requests to it if a failure threshold is crossed
    -  This prevents further strain on the failing service and allows it time to recover
- Queuing
    - Using queues to manage incoming requests can help in controlling the flow of requests to backend services
    - Requests are placed in a queue and processed in a controlled manner, ensuring that the service is not overwhelmed

Implementation Techniques of Throtelling
----------------------------------------
1. API Gateways
    - API gateways are crucial in managing traffic across microservices and distributed systems
    - They sit between clients and backend services, providing centralized control over request handling
    - Rate Limiting: API gateways can enforce rate limiting policies by restricting the number of requests from a client or IP address within a specified time frame
    - Request Quotas: They can manage quotas for different clients or services, ensuring that no single entity exceeds its allocated resources
    - Trafic Shaping: API gateways can implement traffic shaping strategies, such as queuing requests during high load periods and releasing them gradually
    - Example: Nginx, Kong, AWS API Gateway

2. Middleware
    - refers to software layers that sit between the web server and application logic
    - They can be used to enforce throttling policies in web applications and services
    - Rate Limiting Middleware: Middleware can be configured to apply rate limiting policies, such as limiting the number of API requests per minute per user
    - Dynamic Throtelling: Middleware can adjust throttling policies based on real-time metrics or system load, allowing for adaptive control
    - Error Handling: Middleware can handle errors related to throttling, such as returning appropriate HTTP status codes (e.g., 429 Too Many Requests) and messages to clients
    - Example: Express-rate-limit for Node.js applications, Django Ratelimit for Python applications

3. Distributed Caches
    - Distributed caching systems can be leveraged to implement rate limiting and request tracking across multiple nodes in a distributed system
    - Token Bucket Algorithm: A distributed cache like Redis can store tokens in a bucket for each client or service. Requests consume tokens, and if the bucket is empty, further requests are throttled
    - Request Tracking: Caches can track request counts and timestamps, allowing for the implementation of rate limiting and request quota policies across distributed components
    - Sliding Windows: Distributed caches can manage sliding window counters to provide more granular control over request rates.
    - Example: Redis, Memcached

4. Queuing Systems
     - Queuing systems help manage the flow of requests to backend services by buffering incoming requests and processing them in a controlled manner.
     - Message Queues:
        - Requests are placed in a queue and processed according to a predefined rate or priority
        - This prevents backend services from being overwhelmed by high traffic
    - Batch Processing: Queues can batch process requests, allowing for efficient handling and resource management
    - Backpressure Management: Queuing systems can provide backpressure signals to clients when the system is under high load, indicating that they should retry or wait
    - Example: RabbitMQ, Apache Kafka, AWS SQS

5. Rate Limiting Algorithms:
    - Different algorithms are used to enforce rate limiting, each with its own characteristics:
        - Token Buket:
            - This algorithm allows a fixed number of tokens to accumulate over time
            - Each request consumes a token. If no tokens are available, the request is throttled
        - Leaky Bucket:
            - This algorithm processes requests at a fixed rate, regardless of the incoming request rate
            - Excess requests are queued or discarded based on capacity
        - Fixed Window:
            - Requests are counted within fixed time intervals (e.g., per minute)
            - If the limit is exceeded within that window, additional requests are throttled
        - Sliding Window:
            - This approach maintains a rolling time window to track request rates, providing finer control compared to fixed windows

Scenarios
---------
- Public APIs
    - For public APIs, rate limiting is a common approach to prevent abuse and ensure fair usage
    - Policies such as requests per minute or per hour can be applied to manage the load effectively
- Microservices:
    - In microservices architectures, throttling can be used to protect individual services from excessive load and to manage inter-service communication efficiently
- Cloud Services:
    - Cloud-based services often implement throttling at various levels, including at the API gateway, load balancer, and service level, to handle traffic spikes and maintain service quality
- Databases:
    - Throttling database queries can prevent excessive load on database systems, ensuring that queries are processed efficiently and avoiding performance degradation

 
:ref:`Go Back <stability_microservices_patterns-label>`.