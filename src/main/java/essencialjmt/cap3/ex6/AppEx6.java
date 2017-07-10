package essencialjmt.cap3.ex6;

import java.util.concurrent.ExecutionException;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import essencialjmt.cap3.*;
import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class AppEx6 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private ImageRepo repo = new ImageRepo();       
    
    public AppEx6() {
        new Thread(printer).start();
        ParallelFlowable<ImageData> flowable = getImageNamesToProcess();
        flowable.doAfterNext(printer::print).doOnComplete(printer::end);//.subscribe(d -> processImageData(d));
    }

    public ParallelFlowable<ImageData> getImageNamesToProcess() {
        return Flowable.fromIterable(IterableSource::new).parallel().runOn(Schedulers.io()).map(repo::loadImage);
    }

    public void processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx6();
    }
}