package essencialjmt.base;

import static essencialjmt.base.ThreadUtil.*;

public class Intro1_1 {

    private static int counter;

    public static void main(final String[] args) {
        Thread tDeamon = new Thread(() -> {
            while (true) {
                System.out.print("\\o/");
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
        
        System.out.println("tNormal State: " + tNormal.getState());
        System.out.println("tDeamon State: " + tNormal.getState());
        System.out.println("counter = " + counter);
    }
}
