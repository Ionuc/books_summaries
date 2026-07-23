.. _java-development-concurrency-syncrhonizers-semaphor-label:

Semaphor
========
- is a common pattern in multi-threading programming
- is a variable used to control access to a common resource by multiple threads of executio and avoid concurrency issued
- another way to image semaphore is to imagine a system that stores information about nr of threads that got access to particular resource and nr of threads that releses the access to that resource:
    - it restricts the access to other threads if maximum nr of threads was reached
    - those threads will wait until semaphore will allow them to access the resource
- constructor:
    - permits argument:
        - nr of threads which can access the resource
        - can be negative nr, in this case, releasing must happen before any acquire will be granted
    - fair:
        - in case of true, semaphore will guarantee FIFO granting of permist under contention
        - in case of false, will mean opposite

- methods:
    - tryAcquire()
        - returns true if pyramid is available and immediately acquire it, otherwise returns false
        - thread will become locked and wait until one will be available
        - wait throw InterruptedException while thread is in waiting state and is interrupted
    - tryAcquire(time, timeUnit)
        - the same as tryAcquire() but it will return false in case time elapsed and pyramid was not acquired
        - wait throw InterruptedException while thread is in waiting state and is interrupted
    - tryAcquire(permits):
        - acquired the given number of pyramids from this semaphore only if all are available at the time of invocation
        - wait throw InterruptedException while thread is in waiting state and is interrupted

    - aquireUninterruptile(permits)
        - acquired the given number of pyramids from this semaphore only if all are available at the time of invocation
        - will not throw InterruptedException in case thread is in waiting state and is interrupted

    - release(permits)
        - is releasing the nr of pyramids and return it to semaphor
        - is important methods that whould not be forget to invoke when pyramids are acquired
    - availablePermits():
        - return the available permits the semaphor has

    .. code-block:: python
       :linenos:

        public class SemaphoreDemo {

            public static void main(String[] args) throws InterruptedException {
                int loginPermits = 10;
                var es = Executors.newFixedThreadPool(4);
                var demo = new SemaphoreDemo(loginPermits);
                IntStream.range(0, loginPermits).forEach(user -> es.submit(demo::tryLogin));
                es.shutdown();
                es.awaitTermination(2, TimeUnit.SECONDS);
                
                System.out.println("availablePermitsForLogin:\t" + demo.availablePermitsForLogin());
                demo.tryLogin();
                
                demo.logout();
                System.out.println("availablePermitsForLogin:\t" + demo.availablePermitsForLogin());
            }

            private Semaphore semaphore;

            public SemaphoreDemo(int slotLimit) {
                semaphore = new Semaphore(slotLimit);
            }

            private void tryLogin() {
                if (semaphore.tryAcquire()) {
                    System.out.println("Congrats! You are logged in!");
                } else {
                    System.out.println("No permits for login available. Try later.");
                }
            }

            private void logout() {
                semaphore.release();
            }

            private int availablePermitsForLogin() {
                return semaphore.availablePermits();
            }

        }

:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.