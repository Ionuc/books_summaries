.. _java-development-concurrency-syncrhonizers-condition-label:

Condition
=========

- A Condition provides the equivalent of the traditional wait(), notify() and notifyAll()
    - The traditional wait and notify methods allow developers to implement an await/signal pattern
- You use an await/signal pattern when you would use locking, but with the added stipulation of trying to avoid spinning ( endless checking if it is okay to do something )
- Condition interface provides a thread ability to suspent its execution until the given condition is true
- you can have multiple conditions attached to the lock
- condition should be verified by thread when it tries to access the critical section
- methods:
    - await()
        - is similar to the Object.wait() method
        - it makes thread wait for notification
        - in case of interruption, thread will be interrupted and exception will be thrown
    - awaitUninterruptebly()
        - is similar to the await method
        - the difference is that thread will keep waiting until it got notified without interruption
    - awaitNanos(nanosTimeut)
    - awaitNanos(time, timeunit)
        - waits until thread would be interrupted or notified, or the specified timeout elapsed
    - awaitUntil(deadline)
        - await until the specified Date time
    - signal()
        - wakes up one thread only
        - is similar with Object.notify()
    - signalAll()
        - wakes up all threads
        - is similar with Object.notifyAll()



    .. code-block:: python
        :linenos:

        public class ConditionDemo {
            
            private static final int CAPACITY = 5;
            
            private Deque<String> stack = new LinkedList<>();
            private Lock lock = new ReentrantLock();
            private Condition stackEmptyCondition = lock.newCondition();
            private Condition stackFullCondition = lock.newCondition();

            public static void main(String[] args) {
                var demo = new ConditionDemo();
                var es = Executors.newFixedThreadPool(4);
                
                es.submit(() -> System.out.println(demo.popFromStack()));
                es.submit(() -> demo.pushToStack("Test String #1"));
                
                es.shutdown();
            }

            public void pushToStack(String item) {
                try {
                    lock.lock();
                    while (stack.size() == CAPACITY) {
                        stackFullCondition.await();
                    }
                    stack.push(item);
                    stackEmptyCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    
                }
            }

            public String popFromStack() {
                try {
                    lock.lock();
                    while (stack.size() == 0) {
                        stackEmptyCondition.await();
                    }
                    return stack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    stackFullCondition.signalAll();
                    lock.unlock();
                }
                return "";
            }

        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.