.. _java-development-concurrency-thread-local-label:

ThreadLocal
===========
- allows us to store data that will be accessible nly by a single thread
- each thread that accesses the thread local has its own independentyl initialized copy of that variable.:
    - it can be compared to a Map, where the key is the thread and the value is the value set by each thread
- want to avoid global variables:
    - cause additional coupling
    - modifications can cause problems
- but useful:
    - to not have to pass values for formatters and loggers
    - for threads to have their own copy accessed via same global reference
- to use ThreadLocal we simply create an instance of a ThreadLocal per a variable we wish to use in this way
- we use to set method, set a value, get to get the value back and remove to clear it out
- if 2 threads manipulate the same variable, they can use the same ThreadLocal instance safely since the value set and returned will be specific to the thread that it set it

    - beaware:
        - data persists until thread dies or no instances of ThreadLocal left
        - danger with long-lived threads to not have memory leaks:
            - if task finishes/dies without lcearing out Threadlocal objects, the thread might still exist and a Threadlocal may stil exist elswhere
            - lost knowledge that the thread stored an object
            - one way ot be safe is by capturing all exception and by calling Remove on any ThreadLocal we maye have stored data in

- InehritableThreadLocal:
    - instead of each thread having its own value inside the threadlocal, the InehritableThreadLocal grants access to values to a thread and all child threads created by the thread
    - a nested thread will have access the to the InheritatedThreadlocal value set on the parent Thread, but will not have access to the ThreadLocal value set on the parent Thread.


    .. code-block:: python
       :linenos:

        public class InheritableThreadLocalDemo {

            
            private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
            private static InheritableThreadLocal<String> inheritableThreadLocal 
                                                                    = new InheritableThreadLocal<>();
            
            public static void main(String[] args) {
                Thread thread1 = new Thread(() -> {
                    System.out.println("***** Thread #1 *****");
                    threadLocal.set("---Thread #1 - ThreadLocal---");
                    inheritableThreadLocal.set("---Thread #1 - InheritableThreadLocal---");

                    System.out.println(threadLocal.get());
                    System.out.println(inheritableThreadLocal.get());

                    Thread childThread = new Thread(() -> {
                        System.out.println("***** ChildThread *****");
                        System.out.println(threadLocal.get());              // => will print null
                        System.out.println(inheritableThreadLocal.get());   // => will print "---Thread #1 - InheritableThreadLocal---"
                    });
                    childThread.start();
                });

                thread1.start();

                Thread thread2 = new Thread(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }

                    System.out.println("**** Thread #2 *****");
                    System.out.println(threadLocal.get());              // will print null as it is another thread
                    System.out.println(inheritableThreadLocal.get());   // will print null as it is another thread
                });
                thread2.start();
            }
        }


Java 21 improvements
--------------------
- is not recommended to be used with Virtual Threads because this threads are discarded shortly
- can introduce bugs and memory leaks because developer may assume the thread persists beyond the task, which it doesn't
- when virtual thread is park, it might come back on a different carrier thread which breaks assumptions made by thread local and can be tricker with InherittedThreadLocal, which attempts to cpy context down to child threads:
    - when a Virtual thread is resumed on a new carrier, that inheritance is no longer predictable or safe

:ref:`Go Back <java-development-concurrency-label>`.