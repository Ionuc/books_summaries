.. _java-development-reflection-dynamic-proxy-label:

Dynamic Proxy
=============
- is one of the primary proxy mechanisms available to us in the language.
- Simply put, proxies are fronts or wrappers that pass function invocation through their own facilities (usually onto real methods) – potentially adding some functionality.
- Dynamic proxies allow one single class with one single method to service multiple method calls to arbitrary classes with an arbitrary number of methods
- Under the cover, it routes all method invocations to a single handler – the invoke() method.

Invocation Handler
------------------
- example of a simple proxy that doesn’t actually do anything except printing what method was requested to be invoked and return a hard-coded number.


    .. code-block:: python
           :linenos:

            public class DynamicInvocationHandler implements InvocationHandler {

                private static Logger LOGGER = LoggerFactory.getLogger(
                  DynamicInvocationHandler.class);

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) 
                  throws Throwable {
                    LOGGER.info("Invoked method: {}", method.getName());

                    return 42;
                }
            }

Creating Proxy Instance
-----------------------
- A proxy instance serviced by the invocation handler we have just defined is created via a factory method call on the java.lang.reflect.Proxy class:


    .. code-block:: python
           :linenos:

            Map proxyInstance = (Map) Proxy.newProxyInstance(
                  DynamicProxyTest.class.getClassLoader(), 
                  new Class[] { Map.class }, 
                  new DynamicInvocationHandler());


- Once we have a proxy instance we can invoke its interface methods as normal:


    .. code-block:: python
           :linenos:

            proxyInstance.put("hello", "world");
            // As expected a message about put() method being invoked is printed out in the log file.


Invocation Handler via Lambda Expressions
-----------------------------------------
- Since InvocationHandler is a functional interface, it is possible to define the handler inline using lambda expression:


    .. code-block:: python
           :linenos:

            Map proxyInstance = (Map) Proxy.newProxyInstance(
              DynamicProxyTest.class.getClassLoader(), 
              new Class[] { Map.class }, 
              (proxy, method, methodArgs) -> {
                if (method.getName().equals("get")) {
                    return 42;
                } else {
                    throw new UnsupportedOperationException(
                      "Unsupported method: " + method.getName());
                }
            });

            (int) proxyInstance.get("hello"); // 42
            proxyInstance.put("hello", "world"); // exception


Timing Dynamic Proxy Example
----------------------------
- example of how long our functions take to execute


    .. code-block:: python
           :linenos:

            public class TimingDynamicInvocationHandler implements InvocationHandler {
                private static Logger LOGGER = LoggerFactory.getLogger(
                  TimingDynamicInvocationHandler.class);
                
                private final Map<String, Method> methods = new HashMap<>();

                private Object target;

                public TimingDynamicInvocationHandler(Object target) {
                    this.target = target;

                    for(Method method: target.getClass().getDeclaredMethods()) {
                        this.methods.put(method.getName(), method);
                    }
                }

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) 
                  throws Throwable {
                    long start = System.nanoTime();
                    Object result = methods.get(method.getName()).invoke(target, args);
                    long elapsed = System.nanoTime() - start;

                    LOGGER.info("Executing {} finished in {} ns", method.getName(), 
                      elapsed);

                    return result;
                }
            }


- it can be used as:



    .. code-block:: python
           :linenos:

            Map mapProxyInstance = (Map) Proxy.newProxyInstance(
              DynamicProxyTest.class.getClassLoader(), new Class[] { Map.class }, 
              new TimingDynamicInvocationHandler(new HashMap<>()));

            mapProxyInstance.put("hello", "world");

            CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
              DynamicProxyTest.class.getClassLoader(), 
              new Class[] { CharSequence.class }, 
              new TimingDynamicInvocationHandler("Hello World"));

            csProxyInstance.length()

            // output will be:
            // Executing put finished in 19153 ns 
            // Executing get finished in 8891 ns 
            // Executing charAt finished in 11152 ns 
            // Executing length finished in 10087 ns


:ref:`Go Back <java-development-reflection-label>`.