package essencialjmt.cap3.ex1;

import essencialjmt.ImageData;

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
