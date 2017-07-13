package essencialjmt.cap2.ex6;

import java.util.UUID;

import essencialjmt.ImageData;
import essencialjmt.Repository;
import essencialjmt.cap1.ThreadUtil;

public class Moderator extends User {

    private String stringToModerate;
    private int count;
    
    public Moderator(String imageName, String stringToModerate, Repository repository) {
        super(UUID.randomUUID().toString(), imageName, repository);
        this.stringToModerate = stringToModerate;
    }

    @Override
    public void run() {
        ImageData data = repository.getFromCache(imageName);
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
