package essencialjmt.cap1;

import static essencialjmt.ThreadUtil.*;

public class Intro1_1 {

    private static int counter;

    public static void main(final String[] args) {
        Thread tDeamon = new Thread(() -> {
            while (true) {
                System.out.println("\\o/");
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
        
        System.out.println("tNormal State: " + tNormal.getState());
        System.out.println("tDeamon State: " + tNormal.getState());
        System.out.println("counter = " + counter);
    }
}
