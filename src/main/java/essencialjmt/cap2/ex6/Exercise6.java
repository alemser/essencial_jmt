package essencialjmt.cap2.ex6;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import essencialjmt.*;

public class Exercise6 {
    private Repository repo = new Repository();
    private ImageManager imageManager = new ImageManager();

    public void process(String[] names) {
        CompletableFuture<?>[] cfutures = Arrays.stream(names).map(name -> CompletableFuture.supplyAsync(() -> repo.findImageByName(name))
                .thenApply(imageManager::extractDimension).thenApply(imageManager::countColours).thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfutures).join();
    }
    
    public void simulateUserChanges() {
        List<User> users = createUsers();
        CompletableFuture<?>[] futures = users.stream().map(CompletableFuture::runAsync).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }
    
    public static void main(final String[] args) {
        String[] names = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        Exercise6 ex6 = new Exercise6();
        ex6.process(names);
        ex6.simulateUserChanges();
        
        ImageData data = ex6.repo.getFromCache(names[0]);
        System.out.println("");
        System.out.println("Total commends for " + names[0] + ": " + data.getComments().size());
        System.out.println("Total like for " + names[0] + ": " + data.getLikes());
        
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("Client " + i, "/img1.jpg", repo));
            if (i % 2 == 0) {
                users.add(new Moderator("/img1.jpg", "Client " + i, repo));
            }
        }
        return users;
    }
}
