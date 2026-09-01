.. _frameworks-java-spring-core-filter-label:

Spring Filters
==============
- A Filter is part of the Servlet API and works at a low level, processing all incoming requests before they reach Spring’s dispatcher.
- Runs for all requests, including static resources.
- No access to Spring beans or controllers directly.

Use cases
---------
- Use Filters for general request/response modifications that apply to all HTTP requests, such as:
    - Logging: You want to track all incoming traffic, including static resources.
    - GZIP Compression: Reduce response size for faster data transfer.
    - Character Encoding: Ensure proper text encoding (e.g., UTF-8) for consistency.
    - Security Checks: Implement firewalls, rate limiting, and access control.
    - Request/Response Modification: Alter HTTP data (e.g., wrapping, sanitization, caching).


Key points
----------
- Execution Point:
    - Filters run before requests reach Spring’s DispatcherServlet, affecting all incoming HTTP traffic
- Scope:
    - Filters operate at the Servlet API level, meaning they apply to all requests, including static resources (CSS, JS, images).
- Access to Srping Beans:
    - Filters cannot access Spring-managed beans directly because they are part of the Servlet container
- Request Type:
    - Filters apply to all HTTP requests, including API endpoints, static files, and servlets.
- Modifying Request/Response:
    - Filters can modify HTTP request and response details (headers, encoding, compression, security).



Example
-------

    .. code-block:: python
        :linenos:

        @Component
        public class LoggingFilter implements Filter {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
                System.out.println("Request received: " + request.getRemoteAddr());
                chain.doFilter(request, response);
                System.out.println("Response sent.");
            }
        }


:ref:`Go Back <frameworks-java-spring-core-label>`.