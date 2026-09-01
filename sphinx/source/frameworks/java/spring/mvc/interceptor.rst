.. _frameworks-java-spring-mvc-interceptor-label:

Interceptor
===========
- is part of Spring MVC
- It allows modifying requests and responses specifically for controllers.
- Works only for Spring MVC controllers (not for static files or servlets).
- Has access to Spring beans (e.g., services, repositories).

Use Cases
---------
- Use Interceptors when you need Spring-specific logic, such as:
    - Authentication & Authorization: Validate user credentials and permissions for controllers.
    - Logging Execution Time: Measure and track how long a controller takes to process a request.
    - Modifying Model/View: Adjust the model or view before sending a response to the client.
    - Adding Common Attributes: Include shared data (e.g., user details) in all responses.


Example
-------


    .. code-block:: python
        :linenos:

        @Component
        public class AuthInterceptor implements HandlerInterceptor {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                System.out.println("Checking authentication...");
                return true; // Continue to the controller
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
                System.out.println("Controller executed.");
            }
        }


:ref:`Go Back <frameworks-java-spring-mvc-label>`.