package essencialjmt.cap2.ex2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import essencialjmt.*;

public class ImageManagerV2 {

    private ImageRepo imageRepo = new ImageRepo();
    private ImageViewer imageViewer = new ImageViewer();

    public void loadImages(String... imageNames) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        
        List<Future<ImageData>> futures = Arrays.stream(imageNames)
                .map(name -> service.submit(() -> imageRepo.loadImage(name)))
                .collect(Collectors.toList());
        
        service.shutdown();
        
        try {
            for (Future<ImageData> future : futures) {
                imageViewer.display(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error reading image", e);
        }        
    }
}
