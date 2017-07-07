package essencialjmt.cap3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import essencialjmt.ImageData;

public class Printer implements Runnable {

    private BlockingQueue<ImageData> queue = new LinkedBlockingQueue<>();

    public void print(ImageData data) {
        queue.add(data);
    }

    public void end() {
        queue.add(new ImageData(null, null));
    }

    @Override
    public void run() {
        ImageData data;
        try {
            while ((data = queue.take()).getName() != null) {
                System.out.println(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
