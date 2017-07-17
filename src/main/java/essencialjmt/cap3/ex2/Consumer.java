package essencialjmt.cap3.ex2;

import essencialjmt.ImageData;
import essencialjmt.cap3.Work;

public class Consumer implements Runnable {

    public Consumer(Work work) {
    }

    @Override
    public void run() {
    }

    public static ImageData createDeathPill() {
        return new ImageData(null, null);
    }
}
