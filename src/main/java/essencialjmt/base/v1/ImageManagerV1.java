package essencialjmt.base.v1;

import static essencialjmt.base.ThreadUtil.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import essencialjmt.ImageRepo;
import essencialjmt.ImageViewer;

public class ImageManagerV1 {

    private ImageRepo imageRepo = new ImageRepo();
    private ImageViewer imageViewer = new ImageViewer();

    public void loadImagesParallel(String... imageNames) {        
        List<Thread> threads = Arrays.stream(imageNames)
                .map(name -> new Thread(() -> imageRepo.loadImage(name)))
                .collect(Collectors.toList());
        startAndJoin(threads.stream().toArray(Thread[]::new));
    }

    public void loadImages(String... imageNames) {
        Arrays.stream(imageNames).map(imageRepo::loadImage).forEach(imageViewer::display);
    }
}