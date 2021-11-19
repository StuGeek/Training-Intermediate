import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {
	private static final int RED_MODE = 1;    // Choose this mode will show red channel.
	private static final int GREEN_MODE = 2;  // Choose this mode will show green channel.
	private static final int BLUE_MODE = 3;   // Choose this mode will show blue channel.
	private static final int GRAY_MODE = 4;   // Choose this mode will show grey.

	private static final int RED_CHANNEL = 0xffff0000;    // The bits of red channel.
	private static final int GREEN_CHANNEL = 0xff00ff00;  // The bits of green channel.
	private static final int BLUE_CHANNEL = 0xff0000ff;   // The bits of blue channel.

	@Override
	public Image showChanelR(Image sourceImage) {
		return showChannelColor(sourceImage, RED_MODE);
	}

	@Override
	public Image showChanelG(Image sourceImage) {
		return showChannelColor(sourceImage, GREEN_MODE);
	}

	@Override
	public Image showChanelB(Image sourceImage) {
		return showChannelColor(sourceImage, BLUE_MODE);
	}

	@Override
	public Image showGray(Image sourceImage) {
		return showChannelColor(sourceImage, GRAY_MODE);
	}

	private Image showChannelColor(Image sourceImage, int mode) {
		// Get the params to construct a MemoryImageSource class.
		int width = sourceImage.getWidth(null);
		int height = sourceImage.getHeight(null);
		int[] pix = new int[width * height];
		BufferedImage bufferImage = ImplementImageIO.toBufferedImage(sourceImage);
		int index = 0;
		// Fill the pixel array by color mode.
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch (mode) {
					case RED_MODE:
						pix[index++] = bufferImage.getRGB(i, j) & RED_CHANNEL;
						break;
					case GREEN_MODE:
						pix[index++] = bufferImage.getRGB(i, j) & GREEN_CHANNEL;
						break;
					case BLUE_MODE:
						pix[index++] = bufferImage.getRGB(i, j) & BLUE_CHANNEL;
						break;
					case GRAY_MODE:
						int pixel = bufferImage.getRGB(i, j);
						int red = (pixel & 0x00ff0000) >> 16;
						int green = (pixel & 0x0000ff00) >> 8;
						int blue = (pixel & 0x000000ff);
						int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
						pix[index++] = (pixel & 0xff000000) | (gray << 16) | (gray << 8) | gray;
						break;
					default:
						break;
				}
			}
		}
		return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));
	}
}
