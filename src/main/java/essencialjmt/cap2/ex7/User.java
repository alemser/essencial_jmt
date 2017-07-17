package essencialjmt.cap2.ex7;

import essencialjmt.ImageData;
import essencialjmt.Repository;

public class User implements Runnable {
    static boolean laskLike = false;
    String name;
    String imageName;
    Repository repository;

    public User(String name, String imageName, Repository repository) {
        this.name = name;
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
        
        String[] comments = new String[10];
        for (int i = 0; i < comments.length; i++) {
            comments[i] = name + " comment number " + i;
        }
        data.addComment(comments);
    }

}
