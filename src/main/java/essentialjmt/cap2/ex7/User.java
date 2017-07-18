package essentialjmt.cap2.ex7;

import java.util.Arrays;

import essentialjmt.ImageData;
import essentialjmt.Repository;

public class User implements Runnable {
    private boolean laskLike = false;
    protected Repository repository;

    public User(Repository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run() {
        ImageData data = repository.getFromCache("/img1.jpg");
        if( laskLike ) {
            data.like();
        } else {
            data.unlike();
        }
        laskLike = !laskLike;

        Arrays.stream(new String[]{"a", "b", "c", "d", "e"}).forEach(data::addComment);
    }

}
