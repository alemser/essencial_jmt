package essencialjmt.cap2.ex6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import essencialjmt.ImageData;

public class App {

    static ImageManagerV6 imageManager = new ImageManagerV6();
    static String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };

    public static void main(final String[] args) {
        imageManager.loadImages(imageNames);

        List<User> users = createUsers();
        CompletableFuture<?>[] futures = users.stream().map(CompletableFuture::runAsync).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        ImageData data = imageManager.getImage(imageNames[1]);
        System.out.println("");
        System.out.println("Total commends for " + imageNames[1] + ": " + data.getComments().size());
        System.out.println("Total like for " + imageNames[1] + ": " + data.getLikes());
    }

    static List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new Client("Client " + i, imageNames[1]));
            if (i % 2 == 0) {
                users.add(new Moderator(imageNames[1], "Client " + i));
            }
        }
        return users;
    }
}
