.. _java-development-concurrency-syncrhonizers-count-down-latch-label:

CountDownLatch
===============
- is used to make sure that the task waits for other threads before it starts
- has ca counter field initialized in the constructor which can devrement as we require
- thread is put to sleep until the counter reach value 0
- differente betwene CyclicBarrier
    - CyclicBarrier works with thread while CountDownLatch works with tasks:
        - in CountDownLatch we calculate nr of countdown methods invocation
        - in CyclciBarrier we calculate nr of threads that called await method on the barrier
    - in CyclicBarrier we can execute action after tripping the barrier
    - we can reuse CyclicBarrier by reseting the count to original value. We cannot reset CountDownLatch
    - brokerBarrierException in CyclicBarrier while InterruptedException in CoundDownLatch without impacting of other threads:
        - in CyclicBarrier, if one thread is interrupted while waiting, then all other waiting threads will throw BrokerBarrierException
        - in CountDonwLatch if current thread is interrupted, it will throw InterruptedException but won't impact other threads

    .. code-block:: python
       :linenos:

        public class CountDownLatchDemo {

            private static final int COUNT = 4;
            public static void main(String[] args) throws InterruptedException {
                CountDownLatch countDownLatch = new CountDownLatch(COUNT);
                var es = Executors.newFixedThreadPool(2);
                
                IntStream.range(0, COUNT).forEach(i -> es.submit(new Worker(countDownLatch)));
                
                countDownLatch.await();
                System.out.println("Latch is released");

                es.shutdown();
            }

            private static class Worker implements Runnable {

                private CountDownLatch countDownLatch;
                
                public Worker(CountDownLatch countDownLatch) {
                    this.countDownLatch = countDownLatch;
                }
                
                public void run() {
                    System.out.println("doing some work...");
                    System.out.println("Counted down -1");
                    countDownLatch.countDown();
                }
            }
        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.