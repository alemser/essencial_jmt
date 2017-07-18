package essentialjmt.cap1;

import essentialjmt.ThreadUtil;

public class Intro3 {
    static boolean runAsACrazyThread = true;
    public static void main(String[] args) {
        
        Thread theCrazyThread = new Thread(() -> {
            while (runAsACrazyThread) {
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.print("\\./");
                }
            }
        });
        
        Thread theInterrupterThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Interrupter");
                    Thread.sleep(100);
                    theCrazyThread.interrupt();
                    System.out.println(theCrazyThread.getState() + " " + theCrazyThread.isInterrupted());
                    Thread.sleep(2_000);
                    System.out.println(theCrazyThread.getState() + " " + theCrazyThread.isInterrupted());
                }
            } catch (InterruptedException e) {
                System.out.println("stoping because I was interrupted");
            }
        });
        theCrazyThread.start();
        theInterrupterThread.start();
        try {
            theInterrupterThread.join(5000);
            theInterrupterThread.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("end - Crazy thread state: " + theCrazyThread.getState());
        runAsACrazyThread = false;
        ThreadUtil.sleep(1000);
        System.out.println("end - Crazy thread state: " + theCrazyThread.getState());
        
    }

}
