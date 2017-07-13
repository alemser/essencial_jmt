package essencialjmt.cap4.web;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import essencialjmt.ImageData;
import essencialjmt.ImageManager;
import essencialjmt.cap4.AsyncIterable;
import essencialjmt.cap4.ReactiveRepository;
import io.reactivex.parallel.ParallelFlowable;

@WebServlet(asyncSupported = true, value = "/ImageProcesserServlet")
public class ImageProcesserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ReactiveRepository repository = new ReactiveRepository();
    private ImageManager imgMng = new ImageManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] names = request.getParameterValues("imgName");

        if (names != null) {
            final AsyncContext ac = request.startAsync(request, response);
            final AsyncIterable iterable = new AsyncIterable();
            request.setAttribute("iterable", iterable);

            ParallelFlowable<ImageData> flowable = repository.findImageData(names);
            flowable.doOnNext(imgMng::extractDimension).doAfterNext(iterable::add).sequential().doOnComplete(() -> {
                iterable.add(AsyncIterable.DEATH_PILL);
                ac.dispatch("/result.jsp");
            }).subscribe(d -> {
            });
        }
    }

}
