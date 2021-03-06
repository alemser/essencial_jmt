package essentialjmt.cap4;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
    
    public static void write(OutputStream out, BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        out.write(imageInByte);
    }
    
    public static BufferedImage createThumbnail(BufferedImage originalBufferedImage) {
        int thumbnailWidth = 75;
        int widthToScale, heightToScale;
        if (originalBufferedImage.getWidth() > originalBufferedImage.getHeight()) {
            heightToScale = (int) (1.1 * thumbnailWidth);
            widthToScale = (int) ((heightToScale * 1.0) / originalBufferedImage.getHeight() * originalBufferedImage.getWidth());

        } else {
            widthToScale = (int) (1.1 * thumbnailWidth);
            heightToScale = (int) ((widthToScale * 1.0) / originalBufferedImage.getWidth() * originalBufferedImage.getHeight());
        }

        BufferedImage resizedImage = new BufferedImage(widthToScale, heightToScale, originalBufferedImage.getType());
        Graphics2D g = resizedImage.createGraphics();

        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(originalBufferedImage, 0, 0, widthToScale, heightToScale, null);
        g.dispose();
        return resizedImage;
    }
    
}
