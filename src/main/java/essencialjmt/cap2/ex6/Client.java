package essencialjmt.cap2.ex6;

import essencialjmt.ImageData;

public class Client extends User {
    static boolean laskLike = false;
    
    public Client(String clientName, String imageName) {
        super(clientName, imageName);
    }

    @Override
    public void run() {
        ImageData data = App.imageManager.getImage(imageName);
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
