.. _rate-limiting-microservices-pattern-label:

Rate Limiting Pattern
=====================
- is a technique used in system architecture to regulate how quickly a system processes or serves incoming requests or actions
- It limits the quantity or frequency of client requests to prevent overload, maintain stability, and ensure fair resource distribution
- Reduces the possibility of resource abuse and denial-of-service (DoS) attacks and hence the best possible performance, dependability, and security of systems
- A rate limiter is a component that controls the rate of traffic or requests to a system.


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/architectural_overview.png
        :align: center


Use cases
---------
- API Rate Limiting: 
    - APIs commonly employ rate limitation to control the volume of client requests, ensure fair access to resources, and prevent abuse.
- Web Server Rate Limiting: 
    - Web servers employ rate limitation as a defense against denial-of-service attacks and to prevent server overload.
- Database Rate limitation: 
    - To keep the database server from experiencing undue strain and to preserve database performance, rate limitation is applied to database queries. For instance, to avoid resource exhaustion and guarantee seamless functioning, an e-commerce website can restrict the quantity of database queries per user.
- Login Rate restriction: 
    - To stop password guessing and brute-force assaults, login systems employ rate restriction. Systems can prevent unwanted access by restricting the quantity of login attempts made by each person or IP address.

Types of Rate Limiting
----------------------
1. IP-based Rate Limiting
    - The number of requests that can be sent from a single IP address in a specified amount of time is limited by IP-based rate limitation (e.g., 10 requests per minute)
    - By restricting the amount of traffic that can originate from a single IP address, it is frequently used to stop abuses like bots and denial-of-service attacks
    - Advantages:
        - It’s simple to implement at both the network and application level.
        - If someone is trying to flood the system with requests, limiting their IP can prevent this.
    - Limitations:
        - Attackers can use techniques like VPNs, proxy servers, or even botnets to spoof different IPs and get around the limit
        - If multiple users share the same IP address (like in a corporate network), a legitimate user could get blocked if someone else on the same network exceeds the limit
    - Example:
        - An online retailer might set a limit of 10 requests per minute per IP address to prevent bots from scraping product data. This ensures that bots can’t steal data at scale, while regular users can still shop without interruption.
2. Server-based Rate Limiting
    - The number of requests that can be sent to a particular server in a predetermined period of time (e.g., 100 requests per second) is controlled by server-based rate restriction
    - Advantages:
        - Helps protect the server from being overwhelmed, especially during peak usage.
        - By limiting requests per server, you make sure that no single user can monopolize resources and degrade the experience for others.
    - Limitations:
        - If attackers send requests across different servers, they might avoid hitting the rate limit on any single one
        - If the limit is too low or the server is under heavy load, even legitimate users might face delays or blocks
    - Example:
        - A music streaming service might implement server-based rate limiting to prevent their API from being overloaded during rush hours. By setting a limit of 100 requests per second per server, they can ensure the service stays fast and responsive even when usage spikes.
3. Geography-based Rate Limiting
    - restricts traffic based on the geographic location of the IP address
    - It’s useful for blocking malicious requests that originate from certain regions (for example, countries known for high levels of cyber attacks), or for complying with regional laws and regulations
    - Advantages:
        - If you know certain regions are the source of a lot of bad traffic (e.g., spam, hacking attempts), you can limit requests from those areas.
        - Helps comply with local data protection laws or restrictions on content in certain countries.
    - Disadvantages:
        - Attackers can use VPNs or proxy servers to disguise their actual location and get around geography-based limits.
        - Users traveling or accessing services via international servers (like employees using VPNs or customers in hotels) might get blocked if they’re in a region that's restricted.
    - Example:
        - A social media platform might implement geography-based rate limiting to fight spam. If a certain region is known for having a lot of fake accounts or bot activity, the platform could set a rule where IP addresses from that region can only make 10 requests per minute. 

How it workes
-------------
- The number of queries a user or system can make to a service in a predetermined period of time can be managed by rate limitation. 
- A service might permit 100 requests per minute, for instance. 
- Any additional requests will be blocked or slowed down by the system until the time window is reset once that limit is reached

Rate Limiting Algorithms
------------------------

1. Token Bucket Algorithm
    - The token bucket algorithm allocates tokens at a fixed rate into a "bucket."
    - Each request consumes a token from the bucket, and requests are only allowed if there are sufficient tokens available.
    - Unused tokens are stored in the bucket, up to a maximum capacity.
    - This algorithm provides a simple and flexible way to control the rate of requests and smooth out bursts of traffic


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/token_bucket_algorithm.png
        :align: center


2. Leacky Bucket Algorithm
    - The leaky bucket algorithm models a bucket with a leaky hole, where requests are added at a constant rate and leak out at a controlled rate.
    - Incoming requests are added to the bucket, and if the bucket exceeds a certain capacity, excess requests are either delayed or rejected.
    - This algorithm provides a way to enforce a maximum request rate while allowing some burstiness in traffic.


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/leacky_bucket_algorithm.png
        :align: center


3. Fixed Wondow Counting Algorithm
- The fixed window counting algorithm tracks the number of requests within a fixed time window (e.g., one minute, one hour).
- Requests exceeding a predefined threshold within the window are rejected or delayed until the window resets.
- This algorithm provides a straightforward way to limit the rate of requests over short periods, but it may not handle bursts of traffic well.


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/fixed_window_counting_algorithm.png
        :align: center


4. Sliding Windows Log Algorithm


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/sliding_window_log_algorithm.png
        :align: center


Client-Side vs Server-side Rate limiting
-----------------------------------------


    .. image:: ../../../images/patterns/microservices/stability_patterns/rate_limiting/client-side-server-side-rate-limiting.png
        :align: center


Rate limitin in different Layers
--------------------------------
- Application Layer
    - Implementing rate restriction logic inside the application code itself is known as rate limitation at the application layer.
    - It is applicable to all requests that the application processes, irrespective of where they come from or end up
- API Gateway Layer:
    - Setting up rate limiting rules inside the API gateway infrastructure is known as rate limitation at the API gateway layer.
    - It covers incoming requests that the API gateway receives prior to forwarding them to services further down the line.
- Service Layer:
    - Rate limiting at the service layer involves implementing rate limiting logic within individual services or microservices.
    - It applies to requests processed by each service independently, allowing for fine-grained control and customization
- Databse Layer:
    - Rate limiting at the database layer involves controlling the rate of database queries or transactions.
    - It applies to database operations performed by the application or services, such as read and write operations.

Challenges
----------
- Latency:
    - Rate limitation has the potential to cause latency, particularly when requests are throttled or delayed as a result of exceeding rate constraints.
- False Positives:
    - If the rate limiting logic is flawed or the rate restrictions are very restrictive, rate limiting may unintentionally block valid requests
    - False positives may cause users to become frustrated and experience service interruptions.
- Configuration Complxity:
    - It can be difficult to set up rate limiting rules and thresholds, particularly in systems with a variety of traffic patterns and use cases
- Scalability Challenges:
    - If not appropriately scaled, rate limiting methods themselves may create a bottleneck under excessive load.
    - One of the biggest challenges is making sure rate-limiting systems can manage growing traffic levels without seeing any degradation in performance

:ref:`Go Back <stability_microservices_patterns-label>`.