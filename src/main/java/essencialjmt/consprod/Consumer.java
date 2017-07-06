package essencialjmt.consprod;

import essencialjmt.ImageData;
import essencialjmt.ImageViewer;

public class Consumer implements Runnable {
    private Work work;
    private ImageManager imageManager = new ImageManager();
    private ImageViewer imageViewer= new ImageViewer();
    
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
                imageManager.countColours(data);
                imageManager.extractDimension(data);
                imageViewer.display(data);
            }
            System.out.println("\nEnd of consumption");            
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
