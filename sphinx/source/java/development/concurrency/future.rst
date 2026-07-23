.. _java-development-concurrency-future-label:

Future
======
- represents the reulst of an asynchronous computation
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
        - may throw InterruptException in case thread will be interrupted while it waits
    - get(timeout: long, unit: TimeUnit) : V
        - will block the thread only the period of time specified as argument
        - throws TieoutException if the wait time out 
    - cancel(mayInterruptIfRunning: boolean) : boolean
        - used to cancel a task
        - parameter indicates :
            - true => wether we want to interrupt tasks that are in prograss and not completed yet
            - false => to cancel only tasks which were not started
        - return false if :
            - tasks has already completed
            - has already been cancelled
            - or could not be cancelled for some reason
    - isCancelled()

:ref:`Go Back <java-development-concurrency-label>`.