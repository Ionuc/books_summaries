.. _observability-metrics-label:


Observability
==============
- Observability pertains to how a system’s internal state can be understood by examining its external outputs, especially its data
- In the context of modern application development, observability refers to the collection and analysis of data — logs, metrics and traces — from a wide variety of sources, to provide detailed insight into the behavior of applications running in your environments
- Observability is important because it allows teams to evaluate, monitor, and improve the performance of distributed IT systems
- The three pillars of observability are logs, metrics, and traces.

- observability hels organizations:
   - Discover and analyze the significance of performance incidents to their business
   - Increase the efficiency of software development life cycles
   - Accelerate problem resolution and root cause analysis
   - Improve end-user experiences
   - Fortify application security

Observability vs. APM vs. Monitoring
-------------------------------------

- Monitoring:
   - Monitoring is how teams observe and evaluate the state of their individual systems
   - It often uses predefined sets of metrics and logs to track errors and usage patterns
   - These can help answer simple questions about server utilization, response times, and throughput
   - But issues are less predictable on more complex cloud-native applications where numerous dependencies are in play

- APM
   - Application PErformance Monitor (APM)
   - is often considered a subset of observability
   - is a type of monitoring used to get in-depth insight into application code and dependencies
   - A key feature of APM is distributed tracing.
   - APM can help support and optimize application performance, identify bottlenecks, and enhance user experience

Observability:
   - Observability incorporates APM and traditional monitoring tools, but does not replace it
   - Observability uses logs, traces, and metrics collected and aggregated across all the applications, microservices, servers, and databases in your environment.
   - This helps teams identify root causes of issues by looking at data and dependencies across the entire IT ecosystem

How does observability work ?
-----------------------------
- Observability works by continuously collecting performance data to create a complete, correlated record of every user request and transaction
- The more observable your system, the more swiftly and accurately you can identify and trace a performance issue to its origins
- Key concepts for observability include:
   - Log data
   - Metrics
   - Distributed tracing
   - dependency mapping

What are system events ?
------------------------
- Observability requires meticulous data collection from every component of a network to determine the “what,” “where” and “why” of system events and to clarify how events might affect the performance of the entire architecture.
- Therefore, events are the basis of monitoring and telemetry.
- Events are distinct occurrences on a network that happen at specific times and typically produce valuable data for logs, metrics and traces
- Events exist within a broader context.
- Events trigger distinct actions at precise moments. So, observability tools rely on them to initiate the tracking, analysis and correlation processes that help DevOps teams visualize their IT environments and optimize their networks.

Metrics
-------
- Metrics provide quantitative insights into system performance by measuring various network parameters
- They help teams understand the “what” of system issues
- types of metrics include:
   - Host metrics: Memory, disk and CPU usage
   - Network performance metrics: uptime, latency, throughput
   - App metrics: response times, request and error rates
   - Server pool metrics: total instances, number of running instances
   - External dependency metrics: availability, service status

- Metrics are often aggregated to provide a summary view that uses dashboards and other visualizations (such as time-series graphs) to help developers quickly assess the overall health of the system
- They also inform decisions about scaling and resource allocation
- Teams can establish metrics thresholds that, when breached, trigger alerts to notify IT staff of current or impending problems
- However, metrics often provide limited context, so they generally require correlation with logs and traces to give developers a comprehensive understanding of system events

- the 4 golden signals for SRE teams:
   - Latency:
      - Latency measures the time it takes for data to travel from one point to another.
      - Latency will signal underlying performance issues
      - High latency can degrade user experiences by increasing load times, causing application errors, and challenging user expectations
   - Traffic:
      - Traffic metrics track the volume of requests or transactions an application processes
      - They help teams understand user behavior and anticipate scaling needs
   - Errors
      - Error metrics provide visibility into failed requests or operations
      - Monitoring error rates and identifying patterns can help address recurring issues
   - Saturation
      - Saturation metrics indicate how close a system is to its capacity limits
      - Monitoring resource utilization ensures that engineers can proactively address bottlenecks before they impact performance

Logs
----
- Logs are immutable, exhaustive records of discrete events that occur within a system
- They help teams understand the “why” of system issues.
- Log files store detailed information about system behavior and application processes, including:
   - Event timestamps
   - Transaction IDs
   - IP addresses and user IDs
   - Event and process details
   - Error messages
   - Connection attempts
   - Configuration changes
- When an error, security breach or compliance issue occurs, logs provide the details needed to trace the root cause and understand what went wrong

Traces
------
- Traces are the first signal to show the application from a user’s perspective by recording the actions a user performs while using an application or service
- traces map data across network components to show a request's workflow
- They represent the end-to-end journey of a request through the network, capturing the path and lifespan of each component involved in processing the request
- In short, tracing helps site reliability engineers (SREs) and software engineering teams understand the “where” and “how” of system events and issues

Tracing data can include:
   - The duration of network events and operations
   - The flow of data packets through the architecture
   - The order in which requests traverse network services
   - The root cause of system errors

:ref:`Go Back <observability-label>`.