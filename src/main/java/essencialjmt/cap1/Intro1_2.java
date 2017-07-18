package essencialjmt.cap1;

import static essencialjmt.ThreadUtil.*;

public class Intro1_2 {

    private static int counter;

    public static void main(final String[] args) {
        Thread tDeamon = new Thread(() -> {
            while (true) {
                System.out.println(".");
                sleep(1000);
            }
        }, "My deamon");
        tDeamon.setDaemon(true);
        tDeamon.start();

        Thread tNormal = new Thread(() -> {
            sleep(5000);
            counter++;
            System.out.println("tNormal ends");
        });
        tNormal.start();
        
        join(tNormal);
        //join(100, tNormal);
        
        System.out.printf("counter = %d", counter);
    }
}
