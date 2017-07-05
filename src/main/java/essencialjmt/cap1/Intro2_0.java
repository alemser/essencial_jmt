package essencialjmt.cap1;

import static essencialjmt.cap1.ThreadUtil.*;

public class Intro2_0 {

    private static int counter;

    public static void main(String[] args) {
        Runnable incDecRunnable = () -> {
            for (int i = 0; i < 10000; i++) {
                ++counter;
                --counter;
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
