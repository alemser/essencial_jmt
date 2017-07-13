package essencialjmt.cap3.ex5;

import java.util.List;
import java.util.concurrent.ExecutionException;

import essencialjmt.*;
import essencialjmt.cap3.IterableSource;
import essencialjmt.cap3.Printer;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class AppEx5 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private Repository repo = new Repository();

    public void process() {
        new Thread(printer).start();
        Flowable.fromIterable(IterableSource::new).parallel().runOn(Schedulers.io()).map(repo::findImageByName).doOnNext(this::processImageData)
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