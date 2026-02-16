.. _fallback-microservices-pattern-label:

Fallback Pattern
================
- The Fallback pattern is a resilience design pattern used to provide an alternative or backup solution when the primary service or component fails
- It helps ensure that the application continues to function and provide some level of service, even if certain parts are not working as expected
- It ensures service continuity, improves user experience, and provides significant business benefits by reducing the impact of failures

Problems Resolved
-----------------
- Continuity of Operations:
    -  Ensures critical operations are not interrupted by providing alternative paths for execution
- Service Unavailability:
    - When a primary service is down or unavailable, the fallback pattern provides an alternative, ensuring that the application continues to function
- Error Handling:
    - It allows the system to handle errors gracefully without crashing, maintaining a smooth user experience
- Enhanced User Experience:
    - Minimizes disruptions for end-users, maintaining their trust and satisfaction

:ref:`Go Back <stability_microservices_patterns-label>`.