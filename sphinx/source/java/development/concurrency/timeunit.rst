.. _java-development-concurrency-timeunit-label:

TimeUnit
========
- enum created to put to sleep easier a thread

    .. code-block:: python
       :linenos:

        public static void main(String[] args) throws InterruptedException {
            InterruptDemo runnableTask = new InterruptDemo();
            Thread thread = new Thread(runnableTask);
            thread.start();

            TimeUnit.MILLISECONDS.sleep(2000); // the same as Thread.sleep(2000);
            TimeUnit.SECONDS.sleep(2);

            System.out.println("in main() - interrupting other thread");
            thread.interrupt();
            
            thread.join();
            System.out.println("in main() - leaving");
        }

:ref:`Go Back <java-development-concurrency-label>`.