package essencialjmt.base.v5;

import java.util.UUID;

import essencialjmt.ImageData;
import essencialjmt.base.ThreadUtil;

public class Moderator extends User {

    private String stringToModerate;
    private int count;
    
    public Moderator(String imageName, String stringToModerate) {
        super(UUID.randomUUID().toString(), imageName);
        this.stringToModerate = stringToModerate;
    }

    @Override
    public void run() {
        ImageData data = App.imageManager.getImage(imageName);
        while (true) {
            if (count==100) {
                break;
            }
            data.excludeCommentsWith(stringToModerate);
            ThreadUtil.sleep(10);
            count++;
        }
    }
}
