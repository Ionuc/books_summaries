.. _java-development-concurrency-virtual-threads-label:

VirtualThreads
==============
- added in Java 21
- in traditional Java programming, we have long relied on platform threads, which are wrappers over OS threads
- each Java thread will correspond to one platform thread
- each thread requires ~ 1 MP stack memory
- context switching manged by OS is expensive
- limits scalability to large nr of concurrent tasks
- botthelneck for high-throughput apps (web servers, microservices)

- other async & reactive frameworks( like Reactor, RxJava) solve scaling, but add complexity which is harder to write, readm debug and maintain

- virtual threads are lightweight, cheep to create and allow us to write straightforward blocking code


    .. image:: ../../../images/java/development/concurrency/virtual-threads.png
        :align: center


How is working
--------------
- is a lightweight implementation of java.lang.Thread
- it is not tied direclty to an operating system thread
- it is managed by JVM itself in coordination with small pool of so-called carrier threads (OS Threads)
- virtual threads are mounted on these carriers only when they are actively running and the are dismounted when they are waiting, like a blocking IO operation or sleep call
- JVM parkes the virtual thread when it hits a blocking operation and then unpacks it later, resuming its execution, possible on other carrier thread:
    - thread Java level state is preserved and suspended while the carrier threads are free to do other work

- virtual threads are implemented using continuations (low-level control-flow primitive)
- JVM can suspend and resume execution at arbitrary points
- Continuation stores the call stack for later resumption and JVM simply swaps it in out as needed
- Virtual thread stacks are heap-allocated (Cs native-memory for platform threads)
- avoids OS-level context switching overhead


    .. image:: ../../../images/java/development/concurrency/virtual-threads-how-is-working.png
        :align: center


Virtual Threads vs Platform Threads
-----------------------------------
- platform threads are expensive to create, manage and switch
- virutl threas are extremely lightweight, heap-allocated stacks
- virtual threads can scale to hundreds of thousands or million of tasks
- virtual threads enable thread-per-task model at massive scale

Structured Concurrency
----------------------
- lifetime follows stucture
- child tasks complete or cancel toghether with their parent


Example
-------
- run a benchmark with 100 platform threads or 100 virtual threads:
    - the platform threads can run as many available processors are
    - virtual threads can run in a bigger number, because blocked virtual threads are parked and the carrier threads can still process onther virtul threads

    .. code-block:: python
       :linenos:

        public class VirtualThreadDemo {

            public static void main(String[] args) throws InterruptedException {
                System.out.println("=== DEMO: Virtual Threads in Java 21 ===");

                // --- 1. Basic Hello World using Thread.startVirtualThread() ---
                Thread vThread = Thread.startVirtualThread(() -> {
                    System.out.println("Hello from a virtual thread (Thread.startVirtualThread)");
                });

                // Wait for the virtual thread to finish
                vThread.join();

                // --- 2. Preferred API: Executors.newVirtualThreadPerTaskExecutor() ---
                try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                    // Submit a simple task
                    Future<String> result = executor.submit(() -> {
                        System.out.println("Hello from a virtual thread in ExecutorService!");
                        return "Task completed";
                    });

                    // Wait and print result
                    System.out.println("Result: " + result.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                // --- 3. Parallel task processing example ---
                int numberOfTasks = 100;

                System.out.println("\nRunning " + numberOfTasks + " tasks in parallel using virtual threads...");
                long startTime = System.nanoTime();

                try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                    List<Future<?>> futures = IntStream.range(0, numberOfTasks)
                            .<Future<?>>mapToObj(i -> executor.submit(() -> {
                                System.out.println("Task #" + i + " is running on " + Thread.currentThread());
                                // Simulate some work
                                try {
                                    Thread.sleep(100);           // Simulate work
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }))
                            .toList();
                    
                    for (Future<?> future : futures) {
                        try {
                            future.get(); // block until task completes
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                long endTime = System.nanoTime();
                double virtualThreadsTime = (endTime - startTime) / 1_000_000.0;
                System.out.printf("Finished %d tasks in %.2f ms%n",
                        numberOfTasks, virtualThreadsTime);
                
                startTime = System.nanoTime();
                int cpus = Runtime.getRuntime().availableProcessors();
                try (ExecutorService executor = Executors.newFixedThreadPool(cpus)) {
                    List<Future<?>> futures = IntStream.range(0, numberOfTasks)
                            .<Future<?>>mapToObj(i -> executor.submit(() -> {
                                System.out.println("Task #" + i + " is running on " + Thread.currentThread());
                                // Simulate some work
                                try {
                                    Thread.sleep(100);           // Simulate work
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }))
                            .toList();
                    
                    for (Future<?> future : futures) {
                        try {
                            future.get(); // block until task completes
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                endTime = System.nanoTime();
                double platformThreadsTime = (endTime - startTime) / 1_000_000.0;
                System.out.printf("Finished %d tasks in %.2f ms%n",
                        numberOfTasks, platformThreadsTime);
                System.out.println("=== Result ===");
                System.out.println("Virtual Threads Time: " + virtualThreadsTime);
                System.out.println("Platform Threads Time: " + platformThreadsTime);

                // --- 4. Memory usage (rough check) ---
             
            }
        }


How JVM handle Blocking operatios
---------------------------------
- it gives the illusion of scalability
- virtual threads make blocking code appeara scalable
- JVM intercepts blocking calls: I/O, sleep, accept, but does not block the underlying operating system thread
- Virtual threads is parked, freeing carrier thread
- carrier thread runs other task while I/O waits
- Given the illusion of non-blocking without async APIs

Pinning carrier or Unsafe Blocking calls
----------------------------------------
- JVM handle JAva-manged blocking (I/O, sleep, sockets) efficiently
- the problem cames when a thread enters a native blocking state:
    - like calling a method that goes through Java Native Interface (JNI)
    - or using certain parts of JDK like file channel map or older JDBC drivers
- in these situations, JVM can't safetly intervene because the thread is really blocked at the operating system level and so it is its carrier

- one pinned carrier (blocked platforme thread) can stall hunders of virtual threads

- recommendations:
    - USe modern HttpClient which is virtual-thread friend
    - prefer async DB drivers (like R2DBC) over legacy JDBC
    - use NIO / async file channels over old blocking streams


Detecting Pinned Virtual Threads
--------------------------------
- virtual threads can silently pin carrier threads in legacy/native ops
- JVM provides tools for diagnosis and monitoring:
    - jcmd
    - Java Fligh Recorder (jfr)
    - custom agents
- use --enable-native-access=ALL_UNMOUNTED for full visibility
- grants tooling access to low-level reuntime detauls on virtual threads and native calls

:ref:`Go Back <java-development-concurrency-label>`.