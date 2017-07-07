package essencialjmt.cap3.ex3;

import java.util.concurrent.ExecutionException;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import essencialjmt.cap3.*;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AppEx3 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private ImageRepo repo = new ImageRepo();       
    
    public AppEx3() {
        new Thread(printer).start();
        Observable<ImageData> observable = getImageNamesToProcess();
        observable.doAfterNext(printer::print).doOnComplete(printer::end).subscribe(this::processImageData);
    }

    public Observable<ImageData> getImageNamesToProcess() {        
        return Observable.fromIterable(IterableSource::new).subscribeOn(Schedulers.io()).map(repo::loadImage);
    }
    
    public void processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx3();
    }
}
