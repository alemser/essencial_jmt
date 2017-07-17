package essencialjmt;

import java.lang.Thread.State;
import java.util.Arrays;

public class ThreadUtil {

    /**
     * Start threads.
     */
    public static void start(Thread... threads) {
        for (Thread t : threads) {
            t.start();
        }
    }

    /**
     * Start and join threads.
     */
    public static void startAndJoin(Thread... threads) {
        start(threads);
        join(threads);
    }
    
    /**
     * Utility to join a thread to make the examples clear, avoiding to catch exceptions.
     */
    public static void join(Thread... threads) {
        join(0, threads);
    }
    
    /**
     * Utility to join a thread to make the examples clear, avoiding to catch exceptions.
     * 
     * @param ms
     *            time in milliseconds.
     */
    public static void join(long ms, Thread... threads) {
        try {
            for (Thread t : threads) {
                t.join(ms);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Utility to put the current thread to sleep to make the examples clear, avoiding to catch exceptions.
     * 
     * @param ms
     *            time in milliseconds.
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints attributes from the current thread.
     */
    public static void printDetails() {
        final Thread t = Thread.currentThread();
        System.out.printf("Thread %s details [ID: %d, ALIVE: %s, DEAMON: %s, STATE: %s]\n", t.getName(), t.getId(), t.isAlive(), t.isDaemon(),
                t.getState());
    }

    /**
     * Prints the possible states of a thread.
     */
    public static void printThreadPossibleStates() {
        System.out.println("Possible states:");
        Arrays.stream(State.values()).forEach(System.out::println);
    }
    
    /**
     * Return the current thread name;
     * @return
     */
    public static String name() {
        return Thread.currentThread().getName();
    }
}
