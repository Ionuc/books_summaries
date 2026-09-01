.. _java-development-reflection-proxy-label:

Proxy
=====
- 

Java 16 improvements
--------------------
- new InvocationHandler.invokeDefault() was added to invoke default methods from Proxy instance
- invocation handler intercepts method calls on proxy and for default methods uses invocation handler

    .. code-block:: python
        :linenos:


        public class Java16 {
            public static void main(String[] args) {
                System.out.println("===== Invoke Default Methods From Proxy Instances =====");
                Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
                        new Class<?>[] { DefaultMethodOnProxyDemo.class },
                        (prox, method, arguments) -> {
                            if (method.isDefault()) {
                                return InvocationHandler.invokeDefault(prox, method, arguments);
                            }
                            return prox;
                        });
                Method method = proxy.getClass().getMethod("getText");
                System.out.println(method.invoke(proxy));
            }

            interface DefaultMethodOnProxyDemo {
                default String getText() {
                    return "Test Text from DefaultMethodOnProxyDemo";
                }
            }
        }



:ref:`Go Back <java-development-reflection-label>`.