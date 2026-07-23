.. _java-development-concurrency-callable-label:

Callable
========
- solves Runnable problems by not returning a type, but also allowing checked exceptions to be thrown
- it has a call() method which acts like Runnable.run()
- it is possible to adapt Runnable to Callable by using Executors static method callable
- is done automatically in the submit() method 


    .. code-block:: python
       :linenos:

        public class CallableDemo {
            
            public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
                
                ExecutorService es = Executors.newCachedThreadPool();
                Future<Integer> future = es.submit(() -> 1 + 1);
                
                System.out.println(future.get(10, TimeUnit.SECONDS));
                
            }

        }

:ref:`Go Back <java-development-concurrency-label>`.