package essencialjmt.cap2.ex7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import essencialjmt.ImageData;
import essencialjmt.ImageListener;

public class Exercise7 implements ImageListener {
    private Ex7ImageProcessor imageProcessor = new Ex7ImageProcessor();

    public Exercise7(String[] images) {
        imageProcessor.addImageListener(this);
        imageProcessor.loadAlbum(images);
        
        simulateUserChanges();
        
        ImageData data = imageProcessor.getRepository().getFromCache(images[0]);
        System.out.println("");
        System.out.println("Total commends for " + images[0] + ": " + data.getComments().size());
        System.out.println("Total like for " + images[0] + ": " + data.getLikes());
        System.out.println("Total cache hits: " + imageProcessor.getRepository().getCacheHits());
    }
    
    public void simulateUserChanges() {
        List<User> users = createUsers();
        CompletableFuture<?>[] futures = users.stream().map(CompletableFuture::runAsync).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }
    
    public static void main(final String[] args) {
        String[] images = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        new Exercise7(images);
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(imageProcessor.getRepository()));
            if (i % 2 == 0) {
                users.add(new Moderator("awful", imageProcessor.getRepository()));
            }
        }
        return users;
    }

    @Override
    public void imageProcessed(ImageData data) {
        System.out.println(data);
    }
}
