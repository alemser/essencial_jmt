package essentialjmt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageData {
	private BufferedImage bufferedImage;
	private String name;
	private Set<Color> singleColors;
	private String resolution;

	private AtomicInteger likes = new AtomicInteger(0);
	private List<String> comments = new ArrayList<>();

	public ImageData(final String name, final BufferedImage bufferedImage) {
		this.name = name;
		this.bufferedImage = bufferedImage;
	}

	public Integer getNumberOfColors() {
		return singleColors.size();
	}

	public void setColors(Set<Color> listOfSingleColors) {
		this.singleColors = listOfSingleColors;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(final String resolution) {
		this.resolution = resolution;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public String getName() {
		return name;
	}

	public List<String> getComments() {
		return Collections.unmodifiableList(comments);
	}

	public boolean containsComment(String comment) {
		return comment.contains(comment);
	}

	public void excludeCommentsWith(String strToExclude) {
		comments.removeIf(s -> s.contains(strToExclude));
	}

	public void addComment(String... comments) {
		if (this.comments.size() < 10) {
			for (String comment : comments) {
				if (comment != null) {
					this.comments.add(comment);
				}
				if (this.comments.size() > 10) {
					break;
				}
			}
		}
	}

	public int like() {
		return likes.incrementAndGet();
	}

	public int unlike() {
		return likes.decrementAndGet();
	}

	public int getLikes() {
		return likes.get();
	}

	public Set<Color> getSingleColors() {
		return singleColors;
	}

	@Override
	public String toString() {
		return name + " (" + (singleColors != null ? "#colors: " + singleColors.size() : "")
				+ (resolution != null ? "; res: " + resolution : "") + ") ";
	}
}
