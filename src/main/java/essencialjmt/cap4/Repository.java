package essencialjmt.cap4;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    
    private ImageRepo repo = new ImageRepo();
    
    public ParallelFlowable<ImageData> findImageData(String... names) {
        return Flowable.fromArray(names).parallel().runOn(Schedulers.io()).map(repo::loadImage);
    }        
}
