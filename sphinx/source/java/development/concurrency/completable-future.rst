.. _java-development-concurrency-completale-future-label:

Completable Future
==================
- Java 8 introduced the CompletableFuture class. Along with the Future interface, it also implemented the CompletionStage interface. This interface defines the contract for an asynchronous computation step that we can combine with other steps.
- Usually, we want to think of any computation as a series of steps, but in the case of asynchronous computation, actions represented as callbacks tend to be either scattered across the code or deeply nested inside each other
- Things get even worse when we need to handle errors that might occur during one of the steps.

Using CompletableFuture as a Simple Future
------------------------------------------
- the CompletableFuture class implements the Future interface so that we can use it as a Future implementation but with additional completion logic

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


Running multiple Futures in parallel
------------------------------------
- When we need to execute multiple Futures in parallel, we usually want to wait for all of them to execute and then process their combined results.
- The CompletableFuture.allOf static method allows to wait for the completion of all of the Futures provided as a var-arg:

   .. code-block:: python
        :linenos:

        CompletableFuture<String> future1  
          = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2  
          = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3  
          = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture 
          = CompletableFuture.allOf(future1, future2, future3);

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

Handling errors
---------------
- Instead of catching an exception in a syntactic block, the CompletableFuture class allows us to handle it in a special handle method.
- This method receives two parameters: a result of a computation (if it finished successfully) and the exception thrown (if some computation step did not complete normally).


   .. code-block:: python
        :linenos:

        String name = null;
        // ...

        CompletableFuture<String> completableFuture
          =  CompletableFuture.supplyAsync(() -> {
              if (name == null) {
                  throw new RuntimeException("Computation error!");
              }
              return "Hello, " + name;
          }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        assertEquals("Hello, Stranger!", completableFuture.get());


- if we want ti complete with exception, we can use completeExceptionally:


   .. code-block:: python
        :linenos:


        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // ...

        completableFuture.completeExceptionally(
          new RuntimeException("Calculation failed!"));

        // ...

        completableFuture.get(); // ExecutionException


:ref:`Go Back <java-development-concurrency-label>`.