package essentialjmt.cap2.ex7;

import essentialjmt.ImageData;
import essentialjmt.Repository;

public class Moderator extends User {

    private String stringToModerate;
    private int count;
    
    public Moderator(String stringToModerate, Repository repository) {
        super(repository);
        this.stringToModerate = stringToModerate;
    }

    @Override
    public void run() {
        ImageData data = repository.getFromCache("/img1.jpg");
        while (true) {
            if (count==100) {
                break;
            }
            data.excludeCommentsWith(stringToModerate);
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
            count++;
        }
    }
}
