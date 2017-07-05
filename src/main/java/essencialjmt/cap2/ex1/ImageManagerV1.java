package essencialjmt.cap2.ex1;

import static essencialjmt.cap1.ThreadUtil.*;

import java.util.Arrays;

import essencialjmt.ImageRepo;

public class ImageManagerV1 {

    private ImageRepo imageRepo = new ImageRepo();

    public void loadImagesParallel(String... imageNames) {
        Thread[] threads = Arrays.stream(imageNames).map(name -> new Thread(() -> imageRepo.loadImage(name))).toArray(Thread[]::new);
        startAndJoin(threads);
    }

    public void loadImages(String... imageNames) {
        Arrays.stream(imageNames).forEach(imageRepo::loadImage);
    }
}