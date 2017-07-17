package essencialjmt.cap2.ex7;

import java.util.Arrays;

import essencialjmt.ImageData;
import essencialjmt.Repository;

public class User implements Runnable {
    private static boolean laskLike = false;
    protected String imageName;
    protected Repository repository;

    public User(String imageName, Repository repository) {
        this.imageName = imageName;
        this.repository = repository;
    }
    
    @Override
    public void run() {
        ImageData data = repository.getFromCache(imageName);
        if( laskLike ) {
            data.like();
        } else {
            data.unlike();
        }
        laskLike = !laskLike;
                
        Arrays.stream(new String[]{"a", "b", "c", "d", "e"}).forEach(c -> data.addComment(c));
    }

}
