package essentialjmt.cap3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import essentialjmt.ImageData;

public class Work {
    private BlockingQueue<ImageData> workQueue = new LinkedBlockingQueue<>();
    
    public ImageData request() throws InterruptedException {
        return workQueue.take();
    }
    
    public void produce(ImageData imageData) {
        try {
            workQueue.put(imageData);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
