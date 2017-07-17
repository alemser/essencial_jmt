package essencialjmt.cap3.ex1;

import essencialjmt.ImageData;
import essencialjmt.ImageManager;

public class Consumer implements Runnable {
    private Work work;
    private ImageManager imageManager = new ImageManager();
    
    public Consumer(Work work) {
        this.work = work;
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                ImageData data = work.request();
                if (data.getName() == null) {
                    break;
                }
                System.out.println(" Consuming " + data.getName());
                imageManager.countColours(data);                
                imageManager.extractDimension(data);
            }
            System.out.println("End of consumption");            
        } catch (InterruptedException e) {            
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageData createDeathPill() {
        return new ImageData(null, null);
    }
}
