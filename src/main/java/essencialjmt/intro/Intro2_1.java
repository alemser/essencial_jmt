package essencialjmt.intro;

import static essencialjmt.intro.ThreadUtil.*;

import java.util.concurrent.atomic.AtomicInteger;

public class Intro2_1 {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(final String[] args) {
        Runnable incDecRunnable = () -> {
            for (int i = 0; i < 1000000; i++) {
                counter.incrementAndGet();
                counter.decrementAndGet();
            }
        };

        final Thread t1 = new Thread(incDecRunnable);
        final Thread t2 = new Thread(incDecRunnable);
        final Thread t3 = new Thread(incDecRunnable);
        final Thread t4 = new Thread(incDecRunnable);
        final Thread t5 = new Thread(incDecRunnable);

        startAndJoin(t1, t2, t3, t4, t5);

        System.out.println("counter = " + counter);

    }
}
