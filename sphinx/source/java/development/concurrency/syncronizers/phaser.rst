.. _java-development-concurrency-syncrhonizers-phaser-label:

Phaser
======
- you can syncrhonized threads that represent separte phase step of the whole execution
- the nr of threads that participate in this process is not fixed, it can be configured dynamically
- the same as CyclicBarrier, Phasor represents a synchronization point where multiple threads should meet each other
- when all threads arrived, phasor goes to the next phase and waits all threads at the next point

- difference betweebt CyclerBarrier:
    - Phaser supports multiple phases, each phase has a number
    - the number of threads that are participating in Phasor is not limited and is not fixed. Thread may take part and after that cancel own participaton
    - threads shouldn't necessarly wait for other threads reach the synchronization point:
        - to continue its work, it is just enough to notify aout arrival and proceed execution. This is not available in CyclicBarrier
    - there is no action attached that potnetially might be executed after all threads reach specific point, like in CyclicBarrier

- constructor:
    - default constructor:
        - barrier will be closed until all registered participants would reach the barrier
    - constructor with  int parties:
        - barrier will be open when specified nr of threads would reached the barrier

- methods:
    - register()
        - should be called when thread wants to participate in phaser
        - this method just incresed the nr of parties
        - we cannot check if this thread is registered or not
    - arriveAndAwaitAdvance
        - signal that thread is arrived to the barrier
        - moves thread into waiting state
        - when the nr of arrived parties is equal to the nr of registered parties, the execution of the program will continue and the phase nr will increase
    - arrive()
        - is used in case thread doesn want to wait for other threads, but just want to continue processing
        - signal that the thread reached the barrier
        - return the nr of the phase
    - arriveAndDeregister()
        - signal that the thread reached the barrier
        - uregister this thread for the next phase 
        - does not wait for other threads to arrive
    - gerRegisterParties()
        - return the nr of registered parties
    - getArrivedParties()
        - return the nr of arrived parties


    .. code-block:: python
        :linenos:

        public class PhaserDemo {

            private static final int NUMBER_OF_THREADS = 2;
            private static Phaser phaser = new Phaser();

            public static void main(String[] args) throws InterruptedException {
                var es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

                System.out.println("Phase before tasks execution: " + phaser.getPhase());
                IntStream.range(0, NUMBER_OF_THREADS).forEach(i -> es.submit(new Task(phaser)));
                es.shutdown();
                
                es.awaitTermination(1, TimeUnit.SECONDS);
                System.out.println("Parties after all threads are de-registered: " 
                        + phaser.getRegisteredParties());
                
            }

            private static class Task implements Runnable {
                private Phaser phaser;

                public Task(Phaser phaser) {
                    this.phaser = phaser;
                    this.phaser.register();
                }

                @Override
                public void run() {
                    System.out.println("Arrived in thread: " + Thread.currentThread().getName());
                    System.out.println("Arrival phase number: " + phaser.arriveAndAwaitAdvance());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println("getPhase(): " + phaser.getPhase());
                        System.out.println("Arrived in thread: " + Thread.currentThread().getName());
                        System.out.println("Arrival phase number: " + phaser.arriveAndAwaitAdvance());
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println("getPhase(): " + phaser.getPhase());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    phaser.arriveAndDeregister();
                }
            }

        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.