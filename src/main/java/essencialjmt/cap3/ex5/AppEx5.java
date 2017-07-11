package essencialjmt.cap3.ex5;

import java.util.List;
import java.util.concurrent.ExecutionException;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import essencialjmt.cap3.*;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class AppEx5 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private ImageRepo repo = new ImageRepo();

    public void process() {
        new Thread(printer).start();
        Flowable.fromIterable(IterableSource::new).parallel().runOn(Schedulers.io()).map(repo::loadImage).doOnNext(this::processImageData)
                .doAfterNext(printer::print).sequential().doOnComplete(printer::end).subscribe(d -> {
                });
    }

    public void processImageData(List<ImageData> data) {
        data.forEach(this::processImageData);
    }

    public void processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx5().process();
    }
}