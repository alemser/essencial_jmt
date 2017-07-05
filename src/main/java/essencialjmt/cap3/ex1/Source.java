
package essencialjmt.cap3.ex1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Source {
    
    private String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };
    
    private BlockingQueue<String> source = new LinkedBlockingQueue<>();
    
    public Source(String deathPill) {
        new Thread(()-> {
            for (int i = 0; i < imageNames.length; i++) {
                source.add(imageNames[i]);
            }

            source.add(deathPill);
        }).start();
    }
    
    public String getInput() {
        try {
            return source.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
