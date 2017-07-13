package essencialjmt.cap4;

import essencialjmt.ImageData;
import essencialjmt.Repository;
import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class ReactiveRepository extends Repository {

    public ParallelFlowable<ImageData> findImageData(String... names) {
        return Flowable.fromArray(names).parallel().runOn(Schedulers.io()).map(super::findImageByName);
    }        
}
