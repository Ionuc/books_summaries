.. _java-development-concurrency-completale-future-label:

Completable Future
==================
- Java 8 introduced the CompletableFuture class.
- Along with the Future interface, it also implemented the CompletionStage interface.
- This interface defines the contract for an asynchronous computation step that we can combine with other steps.
- Usually, we want to think of any computation as a series of steps, but in the case of asynchronous computation, actions represented as callbacks tend to be either scattered across the code or deeply nested inside each other
- Things get even worse when we need to handle errors that might occur during one of the steps.

Using CompletableFuture as a Simple Future
------------------------------------------
- the CompletableFuture class implements the Future interface so that we can use it as a Future implementation but with additional completion logic
- complete() method will return directly a value and finish makr the CompletableFuture as done
    - it can be used to return a default value in case of failure

    .. code-block:: python
        :linenos:

        public Future<String> calculateAsync() throws InterruptedException {
            CompletableFuture<String> completableFuture = new CompletableFuture<>();

            Executors.newCachedThreadPool().submit(() -> {
                Thread.sleep(500);
                completableFuture.complete("Hello");
                return null;
            });

            return completableFuture;
        }

        ....
        Future<String> completableFuture = calculateAsync();

        // ... 

        String result = completableFuture.get();
        assertEquals("Hello", result);


    - If we already know the result of a computation, we can use the static completedFuture method with an argument that represents the result of this computation:


    .. code-block:: python
        :linenos:

        Future<String> completableFuture = 
          CompletableFuture.completedFuture("Hello");

        // ...

        String result = completableFuture.get();
        assertEquals("Hello", result);


CompletableFuture With Encapsulated Computation Logic
-----------------------------------------------------
- Static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of Runnable and Supplier functional types correspondingly.

   .. code-block:: python
        :linenos:

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        // ...

        assertEquals("Hello", future.get());

        CompletableFuture.runAsync(() -> System.out.println("Hello"));


Processing Results of Asynchronous Computations
-----------------------------------------------
- The most generic way to process the result of a computation is to feed it to a function
- The thenApply method does exactly that; it accepts a Function instance, uses it to process the result, and returns a Future that holds a value returned by a function


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture
          = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
          .thenApply(s -> s + " World");

        assertEquals("Hello World", future.get());


- If we don’t need to return a value down the Future chain, we can use an instance of the Consumer functional interface. Its single method takes a parameter and returns void.


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture
          = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
          .thenAccept(s -> System.out.println("Computation returned: " + s));

        future.get();


- if we neither need the value of the computation nor want to return some value at the end of the chain, then we can pass a Runnable lambda to the thenRun method

   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture 
          = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
          .thenRun(() -> System.out.println("Computation finished."));

        future.get();


Combining Futures
-----------------
- The best part of the CompletableFuture API is the ability to combine CompletableFuture instances in a chain of computation steps
- The result of this chaining is itself a CompletableFuture that allows further chaining and combining.


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture 
          = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        assertEquals("Hello World", completableFuture.get());


- If we want to execute two independent Futures and do something with their results, we can use the thenCombine method that accepts a Future and a Function with two arguments to process both results
    - it also an overloaded method thenCombinaAsync() which can have a dedicate ExecutorService or the default ForkJoinExecutorService


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture 
          = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCombine(CompletableFuture.supplyAsync(
              () -> " World"), (s1, s2) -> s1 + s2);

        assertEquals("Hello World", completableFuture.get());


thenApply() vs thenCompose()
----------------------------
- thenApply:
    - We can use this method to work with the result of the previous call.
    - However, a key point to remember is that the return type will be combined of all calls.
    - So this method is useful when we want to transform the result of a CompletableFuture call:


   .. code-block:: python
        :linenos:

        - CompletableFuture<Integer> finalResult = compute().thenApply(s-> s + 1);


    - in case you combine more CompletableFuture and you need the result, you will have to call multiple times get() method to retrieve it


- thenCompose:
    - The thenCompose() is similar to thenApply() in that both return a new CompletionStage.
    - However, thenCompose() uses the previous stage as the argument.
    - It will flatten and return a Future with the result directly, rather than a nested future as we observed in thenApply():
    - So if the idea is to chain CompletableFuture methods, then it’s better to use thenCompose().


   .. code-block:: python
        :linenos:

        CompletableFuture<Integer> computeAnother(Integer i){
            return CompletableFuture.supplyAsync(() -> 10 + i);
        }
        CompletableFuture<Integer> finalResult = compute().thenCompose(this::computeAnother);



   .. code-block:: python
        :linenos:

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            CompletableFuture<CompletableFuture<Double>> result = getUserDetailById(125)
                    .thenApply(user -> getCreditRating(user));

            System.out.println(result.get().get());

            CompletableFuture<Double> result2 = getUserDetailById(125)
                    .thenCompose(user -> getCreditRating(user));
            System.out.println(result2.get());
        }

        private static CompletableFuture<String> getUserDetailById(int userId) {
            return CompletableFuture.supplyAsync(() -> {
                return "user details string";
            }); 
        }

        private static CompletableFuture<Double> getCreditRating(String userDetails) {
            return CompletableFuture.supplyAsync(() -> {
                return 110.98;
            });
        }


Running multiple Futures in parallel
------------------------------------
- CompletableFuture.allOf()
    - When we need to execute multiple Futures in parallel, we usually want to wait for all of them to execute and then process their combined results.
    - The CompletableFuture.allOf static method allows to wait for the completion of all of the Futures provided as a var-arg:

       .. code-block:: python
            :linenos:

            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

            // ...

            combinedFuture.get();

            assertTrue(future1.isDone());
            assertTrue(future2.isDone());
            assertTrue(future3.isDone());


    - Notice that the return type of the CompletableFuture.allOf() is a CompletableFuture<Void>.
    - The limitation of this method is that it does not return the combined results of all Futures. Instead, we have to get results from Futures manually. Fortunately, CompletableFuture.join() method and Java 8 Streams API makes it simple:


       .. code-block:: python
            :linenos:

            String combined = Stream.of(future1, future2, future3)
              .map(CompletableFuture::join)
              .collect(Collectors.joining(" "));

            assertEquals("Hello Beautiful World", combined);


    - The CompletableFuture.join() method is similar to the get method, but it throws an unchecked exception in case the Future does not complete normally.
    - This makes it possible to use it as a method reference in the Stream.map() method.

- CompletableFuture.anyOf()
    - returns a new CompletbleFuture that is completed when any of the given CompletaleFuture is completed 


       .. code-block:: python
            :linenos:

            public static void main(String[] args) {
                List<String> messages = Arrays.asList("a", "b", "c");
                List<CompletableFuture> futures = messages.stream()
                        .map(msg -> CompletableFuture.completedFuture(msg)
                                .thenApply(Demo11::delayedUpperCase))
                        .collect(Collectors.toList());
                CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()]))
                        .whenComplete((res, th) -> {
                            if (th == null) {
                                System.out.println(res);
                            }
                });
            }

            static String delayedUpperCase(String s) {
                randomSleep();
                return s.toUpperCase();
            }

            static void randomSleep() {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


Handling errors
---------------
- errors are handle using 2 methods:
    - handle(CompletableFuture, Throwable)
        - Instead of catching an exception in a syntactic block, the CompletableFuture class allows us to handle it in a special handle method.
        - This method receives two parameters: a result of a computation (if it finished successfully) and the exception thrown (if some computation step did not complete normally).


        .. code-block:: python
            :linenos:

            String name = null;
            // ...

            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    if (name == null) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
            }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

            assertEquals("Hello, Stranger!", completableFuture.get());


        - if we want to complete with exception, we can use completeExceptionally:


        .. code-block:: python
            :linenos:


            CompletableFuture<String> completableFuture = new CompletableFuture<>();
            // ...
            completableFuture.completeExceptionally(
              new RuntimeException("Calculation failed!"));
            // ...
            completableFuture.get(); // ExecutionException


    - exceptionally():
        - has one parameter: a function where the parameter is the error:


        .. code-block:: python
            :linenos:

            public static void main(String[] args) throws InterruptedException, ExecutionException {
                CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    int i = 5;
                    i /= 0;
                    return i++;
                }).handle((result, exc) -> {
                    if (exc != null) {
                        System.out.println("Exception happend during the execution: " + exc.getClass());
                        System.out.println("Exception is caused by: " + exc.getCause());
                    }
                    return result;
                });

                System.out.println(future.get());

                CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
                    int i = 5;
                    i /= 0;
                    return i++;
                }).exceptionally((exc) -> {
                    if (exc != null) {
                        System.out.println("Exception happend during the execution: " + exc.getClass());
                        System.out.println("Exception is caused by: " + exc.getCause());
                    }
                    return 10; // default value
                });

                System.out.println(future2.get());
            }


Async methods
-------------
- Most methods of the fluent API in the CompletableFuture class have two additional variants with the Async postfix
- These methods are usually intended for running a corresponding execution step in another thread.
- The methods without the Async postfix run the next execution stage using a calling thread
- In contrast, the Async method without the Executor argument runs a step using the common fork/join pool implementation of Executor that is accessed with the ForkJoinPool.commonPool(), as long as parallelism > 1
- Finally, the Async method with an Executor argument runs a step using the passed Executor


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture  
          = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
          .thenApplyAsync(s -> s + " World");

        assertEquals("Hello World", future.get());


Java 9 features
---------------
- Java 9 introduced new instance methods that improve flexibility and ease of use when working with asynchronous computing:
    - Executor defaultExecutor()
        - returns the default executor for asynchronous execution of tasks


   .. code-block:: python
        :linenos:

        Executor defaultExecutor = new CompletableFuture().defaultExecutor();
        System.out.println(defaultExecutor.getClass());
        future = CompletableFuture.supplyAsync(() -> "Result");
        future.thenAcceptAsync(r -> System.out.println(r), defaultExecutor);


    - CompletableFuture<U> newIncompleteFuture()
        - create a new incomplete CompletableFuture instance that is initially neither completed nor exceptionally completed
        - usefull when you manually control the completion of CompletableFuture to either return value or thrown exception


   .. code-block:: python
        :linenos:

        CompletableFuture<String> incompleteFuture = new CompletableFuture().newIncompleteFuture();
        // Perform some asynchronous operation (e.g., fetching data from a remote service)
        // and complete the CompletableFuture when the operation is done
        performAsyncOperation(incompleteFuture);
        // Wait for the result and handle it
        String resultString = incompleteFuture.join();
        System.out.println("Result: " + resultString);

        private static void performAsyncOperation(CompletableFuture<String> future) {
            // Simulate an asynchronous operation
            new Thread(() -> {
                try {
                    // Simulate a delay
                    TimeUnit.SECONDS.sleep(2);

                    // Complete the CompletableFuture with a result
                    future.complete("Async Operation Result");
                } catch (InterruptedException e) {
                    // Handle exception (if needed)
                    future.completeExceptionally(e);
                }
            }).start();
        }


    - CompletableFuture<T> copy():
        - The copy() method returns a new CompletableFuture. This new CompletableFuture completes normally if the original CompletableFuture completes normally. If the original completes with an exception, then the new one also completes with an exception. In this case, it completes with a CompletionException that contains the original exception as its cause.


   .. code-block:: python
        :linenos:

        CompletableFuture<String> original = CompletableFuture.supplyAsync(() -> "Original Result");
        CompletableFuture<String> copy = original.copy();


    - CompletionStage<T> minimalCompletionStage()
        - returns a CompletionStage view of the current CompletableFuture
        - allows to work with a broader completion stage API, like interract with libraris which works with more general completion stage
        - it is a way to bridge between specific featured of CompletableFuture and the more general completion stage


   .. code-block:: python
        :linenos:

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        // Using minimalCompletionStage to obtain a CompletionStage view
        CompletionStage<String> completionStage = completableFuture.minimalCompletionStage();
        // Interact with libraries or methods expecting CompletionStage
        processWithCompletionStage(completionStage);

        ...
        private static void processWithCompletionStage(CompletionStage<String> completionStage) {
            // Use the CompletionStage API
            completionStage.thenApply(String::toUpperCase)
                          .thenAccept(System.out::println)
                          .exceptionally(ex -> {
                              System.err.println("Exception: " + ex);
                              return null;
                          });
        }


    - CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
    - CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)
        - The completeAsync() method should be used to complete the CompletableFuture asynchronously using the value given by the Supplier provided
    - CompletableFuture<T> orTimeout(long timeout, TimeUnit unit)
        - The orTimeout()  method is used to automatically complete the CompletableFuture with a TimeoutException if not completed with a specified timeout period:


   .. code-block:: python
        :linenos:

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running operation
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result";
        });
        CompletableFuture<String> resultFuture = future.orTimeout(2, TimeUnit.SECONDS);
        try {
            resultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Timeout Exception caught: " + e.getCause().getClass());
        }


    - CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit)
        - The completeOnTimeout() completes the CompletableFuture normally with the specified value unless it’s completed before the specified timeout


   .. code-block:: python
        :linenos:

        future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running operation
             try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
             return "Result";
        });
        
        resultFuture = future.completeOnTimeout("Default", 2, TimeUnit.SECONDS);
        System.out.println(resultFuture.get());


- Java 9 enhancements also added support for creating and managing instances of  CompletableFuture with static utility methods:
    - Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
    - Executor delayedExecutor(long delay, TimeUnit unit)
        - create an Executor which adds a delay before executing tasks
        - usefull when a delay is needed before performaing an asynchornous operation
    - <U> CompletionStage<U> completedStage(U value)
    - <U> CompletionStage<U> failedStage(Throwable ex)
    - <U> CompletableFuture<U> failedFuture(Throwable ex)


:ref:`Go Back <java-development-concurrency-label>`.