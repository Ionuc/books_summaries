.. _java-development-concurrency-fork-join-pool-label:

ForkJoin Pool
=============
- this framework help to speed up parallel processing by using all available cores
- it is designed for work that can be broken into smaller pieces recursivelly
- the goal is to use all available processing power to enhance the performance of your application
- the framework recursively breaks the task into smaller indenpendent subtasks until they are simple enough to be executed asynchronously
- after that, result of all subtasks are recursively joined into a single result
- distributes tasks to worked threads in a thread pool
- framework use work stealing algorithm:
    - workers that run out of things to do can steal tasks from other threads that are still busy
- doesn't create separate thread for every single subtask
- each thread in the pool has its own double ended queue which stores tasks


    .. image:: ../../../images/java/development/concurrency/fork-join-task.png
        :align: center


Work Steaing algorithm
----------------------
- threads that are free try to steal work from double ended queue of busy threads
- by default, workers get tasks from the head of its own queue
- when queue is empty, the thread takes a task from the tail of the queue of another busy thread, or from the global entry queue

ForkJoinPool class
------------------
- extends AbstractExecutorService

Creation of ForkJoinPool
------------------------
- you can create it:
    - from common pool using ForkJoinPool.commonPool():
        - this pool and any ongoing processing are automatically terminated upon program termination in case will call System.exit()
    - using constructor:
        - you can provide and int describing parallelism level (how many threads can work in parrallel)
        - constructor with no argument will use the available core processors
        - you can provide also an implementation of UncaughExceptonHandler use to handle exceptions


    .. code-block:: python
       :linenos:

        public class ForkJoinPoolDemo {
            
            public static void main(String[] args) {
                ForkJoinPool commonPool = ForkJoinPool.commonPool();    
                ForkJoinPool forkJoinPool = new ForkJoinPool(4);
                ForkJoinPool forkJoinPool2 = new ForkJoinPool();
                
                forkJoinPool2.invoke(new DefaultRecursiveAction(32));
            }
        }


Fork join tasks
---------------
- you can submit 2 types of tasks:
    - actions:
        - doesn't return any value after execution
        - similar to Runnale
        - extends ForkJoinTaks
        - may still need to break up its work into smaller chunks which can be executed separatelly 
        - methods:
            - compute()

    .. code-block:: python
       :linenos:

        public class DefaultRecursiveAction extends RecursiveAction {
            
            private int workload = 0;

            public DefaultRecursiveAction(int workload) {
                    this.workload = workload;
                }

            @Override
            protected void compute() {
                if (this.workload < 18) {
                    System.out.println("Doing workLoad myself in thread " + Thread.currentThread().getName()
                            + " with workload: " + this.workload);
                } else {
                    System.out.println("Splitting workLoad in thread " + Thread.currentThread().getName()
                            + " with workload: " + this.workload);
                    List<DefaultRecursiveAction> subtasks = new ArrayList<>(createSubtasks());
                    for (RecursiveAction subtask : subtasks) {
                        subtask.fork();
                    }
                    
                    // Alternatively we may call next method
                    // ForkJoinTask.invokeAll(createSubtasks());
                }
            }

            private List<DefaultRecursiveAction> createSubtasks() {
                List<DefaultRecursiveAction> subtasks = new ArrayList<>();

                DefaultRecursiveAction subtask1 = new DefaultRecursiveAction(this.workload / 2);
                DefaultRecursiveAction subtask2 = new DefaultRecursiveAction(this.workload / 2);

                subtasks.add(subtask1);
                subtasks.add(subtask2);

                return subtasks;
            }

        }

    - tasks:
        - is returning the result of the execution
        - similiar to callable type
        - extends ForJoinTaks
        - should join the results of executed subtasks
        - methods:
            - compute()

    .. code-block:: python
       :linenos:

        public class DefaultRecursiveTask extends RecursiveTask<Integer> {

            private int workload = 0;

            public DefaultRecursiveTask(int workload) {
                    this.workload = workload;
                }

            @Override
            protected Integer compute() {
                if (this.workload < 18) {
                    System.out.println("Doing workLoad myself in thread " + Thread.currentThread().getName()
                            + " with workload: " + this.workload);
                    return workload * 2;
                } else {
                    System.out.println("Splitting workLoad in thread " + Thread.currentThread().getName()
                            + " with workload: " + this.workload);
                    List<DefaultRecursiveTask> subtasks = new ArrayList<>(createSubtasks());
                    for (RecursiveTask<Integer> subtask : subtasks) {
                        subtask.fork();
                    }
                    
                    // Alternatively we may call next method
                    // ForkJoinTask.invokeAll(createSubtasks());
                    
                    int result = 0;
                    for(DefaultRecursiveTask subtask : subtasks) {
                        result += subtask.join();
                    }
                    return result;
                }
            }

            private List<DefaultRecursiveTask> createSubtasks() {
                List<DefaultRecursiveTask> subtasks = new ArrayList<>();

                var subtask1 = new DefaultRecursiveTask(this.workload / 2);
                var subtask2 = new DefaultRecursiveTask(this.workload / 2);

                subtasks.add(subtask1);
                subtasks.add(subtask2);

                return subtasks;
            }

        }


Disadvantage
------------
- a faulty taks manager
- inefficient
- special purpose
- slow and unscalable
- exceedingly complex

Example
-------
- you need to find the threashold in order to decide if you split the task or execute it
- the implementation of compute() should know the condition that would define whether it is time to split or execute the task

    .. code-block:: python
       :linenos:

        public class ForkJoinPoolDemo2 {
            
            public static void main(String[] args) {
                var pool = new ForkJoinPool();
                
                var result = pool.invoke(new DefaultRecursiveTask(40));
                System.out.println("Result: " + result);
                
            }

        }

:ref:`Go Back <java-development-concurrency-label>`.