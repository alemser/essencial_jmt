package essencialjmt.base;

import static essencialjmt.base.ThreadUtil.*;

public class Intro1_2 {

    private static int counter;

    public static void main(final String[] args) {
        Thread tDeamon = new Thread(() -> {
            while (true) {
                System.out.print(".");
                sleep(500L);
            }
        }, "My deamon");
        tDeamon.setDaemon(true);
        tDeamon.start();

        Thread tNormal = new Thread(() -> {
            sleep(5000L);
            counter++;
            System.out.println("tNormal ends");
        });
        tNormal.start();
        
        join(tNormal);
        //join(tNormal, 500);
        
        System.out.printf("counter = %d", counter);
    }
}
