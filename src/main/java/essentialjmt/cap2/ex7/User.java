package essentialjmt.cap2.ex7;

import java.util.Arrays;

import essentialjmt.ImageData;
import essentialjmt.Repository;

public class User implements Runnable {
    protected Repository repository;

    public User(Repository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run() {
        ImageData data = repository.getFromCache("/img1.jpg");
        
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                data.like();
            } else {
                data.unlike();
            }
        }
                
        Arrays.stream(new String[]{"cool", "original", "awful"}).forEach(data::addComment);
    }

}
