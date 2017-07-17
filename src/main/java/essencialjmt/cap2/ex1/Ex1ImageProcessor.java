package essencialjmt.cap2.ex1;

import static essencialjmt.cap1.ThreadUtil.*;

import java.util.Arrays;

import essencialjmt.*;
import essencialjmt.cap2.ImageProcessor;

public class Ex1ImageProcessor implements ImageProcessor {
    private Repository repo = new Repository();
    
    @Override
    public void loadAlbum(String... images) {
        Thread[] threads = Arrays.stream(images).map(name -> new Thread(() -> repo.findImageByName(name))).toArray(Thread[]::new);
        startAndJoin(threads);
    }
    
    public void processSingleThread(String... images) {
        Arrays.stream(images).forEach(repo::findImageByName);
    }

    @Override
    public void addImageListener(ImageListener listener) {
    }
}
