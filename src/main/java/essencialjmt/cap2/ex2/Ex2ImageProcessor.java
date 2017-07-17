package essencialjmt.cap2.ex2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import essencialjmt.*;
import essencialjmt.cap2.ImageProcessor;

public class Ex2ImageProcessor implements ImageProcessor {
    private Repository repo = new Repository();
    
    @Override
    public void loadAlbum(String... images) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        
        List<Future<ImageData>> futures = Arrays.stream(images)
                .map(name -> service.submit(() -> repo.findImageByName(name)))
                .collect(Collectors.toList());        
        service.shutdown();
        
        try {
            for (Future<ImageData> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error reading image", e);
        }
    }

    @Override
    public void addImageListener(ImageListener listener) {
    }
}
