package essentialjmt.cap3.ex2;

import essentialjmt.ImageData;
import essentialjmt.cap3.Work;

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
