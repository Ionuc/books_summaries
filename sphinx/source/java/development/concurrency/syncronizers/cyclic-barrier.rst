.. _java-development-concurrency-syncrhonizers-cyclic-barrier-label:

CyclicBarrier
=============
- implements barrier pattern:
    - this pattern describes the case when any thread must stop at the one point barrier and can't proceed until all other threads reach the same point
- all threads should reach one barrier, and after this condition is met, we proceed our execution
- this class is useful when a fix nr of threads needs to reach the barrier after which they can continue execution
- threads that need t synchronize their exectuion are called parties
- constructor:
    - nr of expected threads which should reach the barrier
    - action which needs to be done once barrier is reached
- each thread should call the await method on the barrier instance to signify reaching the common execution:
    - thread calling await() method, will suspend its execution until the specified nr of threads have called the same method on the barrier
    - await() method returns the arrival index of the current thread:
        - value 0 indicats last thread to arrive


    .. code-block:: python
       :linenos:

        public class CyclicBarrierDemo {
            
            private static final int NUMBER_OF_THREADS = 4;
            private static final int NUMBERS_TO_GENERATE = 3;
            private static final int MAXIMUM_RANDOM_NUMBER = 100;
            
            private static CyclicBarrier barrier;
            private List<Integer> generatedNumbers = new CopyOnWriteArrayList<>();
            private Random r = new Random();
            
            public static void main(String[] args) {
                var demo = new CyclicBarrierDemo();
                barrier = new CyclicBarrier(NUMBER_OF_THREADS, demo::numberGenerationCallback);
                
                var ex = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
                IntStream.range(0, NUMBER_OF_THREADS).forEach(i -> ex.submit(demo::generateNumbers));
                System.out.println("getParties(): " + barrier.getParties());
                
                ex.shutdown();
                
            }
            
            private void numberGenerationCallback() {
                int max = generatedNumbers.stream()
                                .mapToInt(i -> i)
                                .max().getAsInt();
                System.out.println("All generated numbers: " + generatedNumbers);
                System.out.println("Maximum: " + max);
                                
            }

            private void generateNumbers() {
                for (int i = 0; i < NUMBERS_TO_GENERATE; i++) {
                    generatedNumbers.add(r.nextInt(MAXIMUM_RANDOM_NUMBER));
                }
                try {
                    System.out.println("await(): " + barrier.await());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.