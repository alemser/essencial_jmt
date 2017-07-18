package essentialjmt.cap4;

import essentialjmt.ImageData;
import essentialjmt.Repository;
import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class ReactiveRepository extends Repository {

    public ParallelFlowable<ImageData> findImageData(String... names) {
        return Flowable.fromArray(names).parallel().runOn(Schedulers.io()).map(super::findImageByName);
    }        
}
