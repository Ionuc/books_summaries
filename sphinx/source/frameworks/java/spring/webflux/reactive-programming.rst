.. _frameworks-java-spring-webflux-reactive-programming-label:

Web Client
==========

Traditional way
---------------
- thread per request
- with the traditional way, each request will have its how thread:
    - each request owns one thread
    - the thread will not be able to process other requests until the current request completes (togher with the calls of DBs), meaning it will block the current thread
    - in case other request are coming, they will be blocked until a new thread is availale
    - this solution is not scalable
    - no backpressure: application not having the capibilities to restrict the amount of data that it can consume from downstream layers

New way
-------
- thread per processor core:
    - if the server is having 16 core CPU, it will be 16 threads that are dedicated for each core
    - JVM will pick any thread of any core
    - each thread is not going to wait for data to be retrieved
    - when data is available, then notify me and I will pick it
    - data from Datastore is send back to Server in form of Data Stream that application can consume
    - advantages:
        - asynchronous & non-blocking
        - scalable
        - backpressure

Reactive Specs
--------------
- are the specifications for reactive programming
- basic interfaces:
    - Publicsher:
        - used to publish the data (like a data store)
        - methods:
            - subscribe(Subscriber s):
                - used by Subscriber
    - Subscriber:
        - onSubscriber(Subscription s)
        - onNext(T t):
            - will be called on each element in the stream
        - onError(Throwable t)
        - onComplete()
    - Subscription:
        - methods:
            - request(long n):
                - used by Subscriber to retrieve the data
                - parameter is used to specify the maximum nr of data to retrieved
            - cancel():
                - cancel the subscription
    - Processor:
        - should extends a Subscriber

Backpressure
------------
- is the capability to entire response in bunch of responses instead of one huge response

Pull vs Push models
-------------------
- Pull models:
    - the producer is not pushing the data
    - the consumer is pulling the data from the stream


    .. code-block:: python
        :linenos:

        Stream<Integer> evenNumStream = Strea,.iterate(2, i -> i*2);
        List<Integer> collect = evenNumStream.limit(5).collect(Collectors.toList());


- Push models:
    - the producer is pushing the data to the subscribers (consumers)
    - Spring React is more push model
        - Subscriber will request for the data
        - Data source can have a huge data to stream
        - depending on how much data is being requested, the producer will only that much of data that the Subcriber is capable of handling
        - the data available to be stream but not consumed yet will be stored in a Buffer

Sending the response
--------------------
- traditional way:
    -  you will have to wait and retrieve all elements from data source
    - then filter and send back the response, even if there are 1 milion of elements



    .. code-block:: python
        :linenos:

        @GetMapping("")
        List<Product> getProducts() {
            return productService.getProducts();
        }


- reactive way:
    - application send send the request,
    - will receive just a number of elements that can consume
    - application will subscriber to the stream of the publiser from data store
    - will send back the responsed in chunks of data

    .. code-block:: python
        :linenos:

        @GetMapping("")
        Pulbisher<Product> getProducts() {
            Flux<Product> products = Flux.<Product>generate(sink -> sink.next(new PRoduct("ProductName")))
                .take(10); // send only chunks of 10 products
            return products;
        }


Live streaming
--------------
- used in in case you don't care about the start or end of the stream, you just want to process items
- set GetMapping.produces value


    .. code-block:: python
        :linenos:

        @GetMapping(value="", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
        Pulbisher<String> getProducts() {
            Flux<String> products = Flux.<String>generate(sink -> sink.next("Live Temperature " + random.nextInt(50)))
                .delayElements(Duration.ofSeconds(1))
            return products;
        }


Flux vs Mono
------------
- Flux is used in order to return a collection of elements
- Mono is used in order to return a single element


Parallel Streams
----------------
- by default, streams are processed inside a single thread
- to enable parallel streaming, you need to use runOn() with the Scheduler
- you can specify the nr of threads to run using .parallel() method
- on complete is called multiple times ( the nr of parallel() value set)



    .. code-block:: python
        :linenos:

        @GetMapping("")
        public void getProducts() {
            Flux.range(1,4)
                .log()
                .delayElements(Duration.ofSeconds(1)) // produce elements with specified delay for backpressure
                .parallel(4) // use only 4 threads
                .runOn(Scheduler.parallel()) // run in parallel
                .subscriber(this::processMethod);
        }

        private void processMethod(int i) {
            System.out.println("Processing " + i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


Example with Spring
-------------------
- use default Subscriber:


    .. code-block:: python
        :linenos:

        @RestController
        @RequestMapping("/products")
        public class ProductController {

            @GetMapping("")
            public void getProducts() {
                List<String> products = new ArrayList<>();
                Flux.just("Apple", "Banana", "Orange")
                    .log()
                    .subscribe(products::add);
            }
        }


- use custom Subscriber


    .. code-block:: python
        :linenos:

        @RestController
        @RequestMapping("/products")
        public class ProductController {

            @GetMapping("")
            public void getProducts() {
                List<String> products = new ArrayList<>();
                Flux.just("Apple", "Banana", "Orange")
                    .subscribe(new Subscriber() {
                        @Override
                        public void onSubscriber(Subscriptions s) {
                            s.request(Long.MAX_VALUE); // get all elements from the stream
                        }

                        @Override
                        public void onNext(Object t) {
                            products.add((String) t);
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Overrider
                        public void onComplete() {

                        }
                    });
            }
        }


- use Subscriber with backpressure:
    - needs extra logic for onNext() & onSubscriber()



    .. code-block:: python
        :linenos:

        @RestController
        @RequestMapping("/products")
        public class ProductController {

            @GetMapping("")
            public void getProducts() {
                List<String> products = new ArrayList<>();
                Flux.just("Apple", "Banana", "Orange")
                    .subscribe(new Subscriber() {
                        private Subscription s;
                        int count;

                        @Override
                        public void onSubscriber(Subscriptions s) {
                            s.request(2); // get only 2 elements from the stream
                            this.s = s; // keep the instance to be used in onNext() because onSubscriber() is called only once
                        }

                        @Override
                        public void onNext(Object t) {
                            products.add((String) t);

                            // consumer other elements from adta source
                            count ++;

                            if (count == 2) {
                                count = 0; // reset it
                                s.request(2); // retrieve new elements
                            }
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Overrider
                        public void onComplete() {

                        }
                    });
            }
        }

:ref:`Go Back <frameworks-java-spring-webflux-label>`.