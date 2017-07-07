package essencialjmt.cap3.ex4;

import java.util.concurrent.ExecutionException;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import essencialjmt.cap3.*;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class AppEx4 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private ImageRepo repo = new ImageRepo();       
    
    public AppEx4() {
        new Thread(printer).start();
        Flowable<ImageData> flowable = getImageNamesToProcess();
        flowable.doAfterNext(printer::print).doOnComplete(printer::end).subscribe(this::processImageData);
    }

    public Flowable<ImageData> getImageNamesToProcess() {
        return Flowable.fromIterable(IterableSource::new).subscribeOn(Schedulers.io()).map(repo::loadImage);
    }
    
    public void processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx4();
    }
}
