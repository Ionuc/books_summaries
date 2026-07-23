.. _java-development-concurrency-syncrhonizers-exchanger-label:

Exchanger
=========
- used to exchange data between threads
- is a generalized type
- there is a syncronization poit where threads may pair and swap elements
- methods:
    - exhange(value)
        - value will send to another thread and receive antother value in return
        - puts thread in waiting state, because thread needs to wait until other thread would arrive ot exhange point 
    - exhange(value, time, timeUnit)
        - you can defined how long we allow one thread to wait for onther before complete exchaging


    .. code-block:: python
        :linenos:

        public class ExchangerDemo {
            
            public static void main(String[] args) throws InterruptedException {
                Exchanger<String> exchanger = new Exchanger<>();

                Runnable task1 = () -> {
                    try {
                        String msg = exchanger.exchange("Message from Task #1");
                        System.out.println("Received from another thread in task #1: " + msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };

                Runnable task2 = () -> {
                    try {
                        String msg = exchanger.exchange("Message from Task #2");
                        System.out.println("Received from another thread in task #2: " + msg);
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                };
                
                var es = Executors.newCachedThreadPool();
                es.submit(task1);
                es.submit(task2);
                
                es.shutdown();
            }

        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.