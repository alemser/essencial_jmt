package essentialjmt.cap3.ex3;

import java.util.concurrent.ExecutionException;

import essentialjmt.*;
import essentialjmt.cap3.IterableSource;
import io.reactivex.Flowable;

public class AppEx3 {

    private ImageManager imageManager = new ImageManager();
    private Repository repo = new Repository();

    public void process() {
        Flowable.fromIterable(IterableSource::new).map(repo::findImageByName).doAfterNext(System.out::println).subscribe(this::processImageData);
    }

    public void processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx3().process();
    }
}
