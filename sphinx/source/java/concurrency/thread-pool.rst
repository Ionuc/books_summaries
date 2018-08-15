.. _thread-pool:

Thread Pool Executor
====================

    - Task is passed to a ThreadPool Executor
    - Task is a runnable
    - Runnable interface does not give information regarding completion, what the result was, or if an exception was thrown
    - Before, the completion was done by 
        - using join
        - save results into shared memory
        - catch / report any unchecked exceptions, or using uncaught exception handlers

Future
------
    - we only need results at point if use
    - avoid monitoring
    - when a Task is submit, a Future is returned
    - is used to manage tasks which run in the future
    - it has a number of useful methods we can call:
        - isDone(): boolean:
            - tells us if the task is done yet
            - used if we are periodically pollingoutstanding tasks, as we don't want to block
        - get(): V
            - is blocking the current thread until the value is returned
        - get(timeout: long, unit: TimeUnit) : V
        - cancel(mayInterruptIfRunning: boolean) : boolean
            - used to cancel a task
            - parameter indicates if the task is running can be cancelled or not
        - isCancelled()

Callable
--------
    - solves Runnable problmes by not returning a type, but also allowing checked exceptions to be thrown
    - it has a call() method which acts like Runnable.run()
    - it is possible to adapt Runnable to Callable by using Executors static method callable
    - is done automatically in the submit() method 

ThreadFactory
-------------
    - provides a custom thread creation
    - has the method : newThred(r: Runnable):Thread
    - this might be useful if the threads need to have certain priorities, ThreadGroups, names and so on

Executor Hierarchy
------------------
    - Executor (interface):
        - execute(command:Runnable):void
    - ExecutorService<T>
        - shutdown():void -> allows all task to finish
        - shutdownNow(): List<Runnable> -> also stops those that are in progress
        - isShutdown(): boolean -> return true when we call shutdown() method
        - isTerminated(): boolean -> will return true when all task are completed or canceled
        - awaitTermination(timeout: long, unit: TimeUnit): boolean
        - submit(task: Calleble<T>): Future<T>
        - submit(task: Runnable): Future<?> -> will return a result of null or completion
        - submit(task: Runnable, result: T): Future<T> -> return that result when the task is done as uses it as the Futures
          return type

		- ...
    - AbstractExecutorService:
        - provides impl for submit, invokeAll, invokeAny
    - ThreadPoolExecutor:
        - implements Executor's run method
        - remove(task:Runnable): boolean -> removes Runnable we are already submitted
        - purge():void -> will remove all cancelled tasks
        - provides a constructor where we can set the corePoolSize, workQueue, threadFactory, RejectExecutionHandler and so on
        - it's rejectExecution() method is called if due to the limits in the queue size we can't accept the task

RejectedExecutionHandler
------------------------
    - CallerRunsPolicy
        - as long the Executor is not shut down, the thread that tried to submit it runs a task instead
        - could lead to starvation
    - AbortPolicy ( default )
        - throws RejectedExecutionException
    - DiscardPolicy
        - does nothing
        - the task will silently discarded
        - can be used in dealing with live video streams
    - DiscardOldestPolicy
        -  will run the task instead of the oldest waiting taks, as long as the executor is not shut down
        - is useful when we are interested in later task results rather than earlier ones

Executors types
---------------
    - fixed size
    - single thread
    - sheduled

:ref:`Go Back <java-concurrency-label>`.