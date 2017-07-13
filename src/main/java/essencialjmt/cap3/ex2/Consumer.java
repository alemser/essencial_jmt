package essencialjmt.cap3.ex2;

import essencialjmt.ImageData;
import essencialjmt.ImageManager;
import essencialjmt.cap3.Work;

public class Consumer implements Runnable {
    private Work work;
    private ImageManager imageManager = new ImageManager();

    public Consumer(Work work) {
        this.work = work;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ImageData data = work.request();
                if (data.getName() == null) {
                    break;
                }
                try {
                    imageManager.countColours(data);
                    imageManager.extractDimension(data);
                } catch (Exception e) {
                    throw new ConsumerException(data.getName(), e.getMessage());
                }
                System.out.println(data.getName() + " consumed");
            }
            System.out.println("End of consumption");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static ImageData createDeathPill() {
        return new ImageData(null, null);
    }
}
