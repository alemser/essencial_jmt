package essencialjmt;

/**
 * Simulates a image view.
 */
public class ImageViewer {
    private boolean enabled = true;

    public void display(ImageData data) {
        if (enabled) {
            System.out.print(data);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
