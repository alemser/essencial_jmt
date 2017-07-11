package essencialjmt.cap3.ex4;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import essencialjmt.ImageData;
import essencialjmt.ImageRepo;
import essencialjmt.cap3.*;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class AppEx4 {

    private Printer printer = new Printer();
    private ImageManager imageManager = new ImageManager();
    private ImageRepo repo = new ImageRepo();

    public void process() {
        Flowable.fromIterable(IterableSource::new).map(repo::loadImage).doAfterNext(System.out::println).subscribe(this::processImageData);
    }

    public void process2() {
        Flowable.fromIterable(IterableSource::new).buffer(3).map(this::loadImage).doAfterNext(System.out::println).subscribe(this::processImageData);
    }

    public void process3() {
        new Thread(printer).start();
        Flowable.fromIterable(IterableSource::new).onBackpressureDrop().observeOn(Schedulers.io()).doOnNext(repo::loadImage)
                .doOnComplete(printer::end).doOnError(e -> printer.end()).subscribe(name -> {
                    ImageData data = repo.loadImage(name);
                    processImageData(data);
                    printer.print(data);
                });
    }

    public void process4() {
        final Set<Integer> dropped = new HashSet<>();
        final Set<Integer> completed = new HashSet<>();
        final Set<Integer> consumed = new HashSet<>();
        Flowable.range(1, 1_000_000).onBackpressureDrop(dropped::add).observeOn(Schedulers.computation()).doOnNext(completed::add)
                .subscribe(n -> consumed.add(n), Throwable::printStackTrace);
        System.out.println("Dropped: " + dropped.size());
        System.out.println("Completed: " + completed.size());
        System.out.println("Consumed: " + completed.size());
    }

    public void processImageData(List<ImageData> images) {
        images.forEach(this::processImageData);
    }

    public ImageData processImageData(ImageData data) {
        imageManager.countColours(data);
        imageManager.extractDimension(data);
        return data;
    }

    public List<ImageData> loadImage(List<String> names) {
        return names.stream().map(repo::loadImage).collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AppEx4 ex4 = new AppEx4();
        ex4.process();
        ex4.process2();
        ex4.process3();
        ex4.process4();
    }
}
