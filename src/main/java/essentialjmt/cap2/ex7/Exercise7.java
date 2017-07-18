package essentialjmt.cap2.ex7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Exercise7 {

    public Exercise7(String[] images) {
                
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
            users.add(new User(null));
            if (i % 2 == 0) {
                users.add(new Moderator("Client " + i, null));
            }
        }
        return users;
    }

}
