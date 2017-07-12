package essencialjmt.cap4.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import essencialjmt.ImageRepo;
import essencialjmt.cap4.ImageUtil;

@WebServlet(asyncSupported = true, value = "/ImageLoaderServlet")
public class ImageLoaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        Runnable loader = () -> writeImage(request.getParameter("imgName"), response);
        CompletableFuture.runAsync(loader).thenAccept(c -> asyncContext.complete());
    }

    private void writeImage(String imageName, ServletResponse response) {
        try {
            BufferedImage originalBufferedImage = new ImageRepo().loadImage(imageName).getBufferedImage();
            BufferedImage thumbnailBufferedImage = ImageUtil.createThumbnail(originalBufferedImage);
            ImageUtil.write(response.getOutputStream(), thumbnailBufferedImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
