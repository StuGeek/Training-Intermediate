import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

import java.util.logging.Logger;

public class ImplementImageIO implements IImageIO {
	// The length of byte array before the location where stores the width.
	private static int beforeWidthByteLength = 18;
	// The length of byte array where stores the width.
	private static int widthByteLength = 4;
	// The length of byte array where stores the height.
	private static int heightByteLength = 4;
	// The length of byte array between where stores the height and the bits data.
	private static int beforeBitsDataLength = 28;
	private static int colorBits = 3;
	private static int bytesLength = 8;

	@Override
	public Image myRead(String filePath) throws IOException {
		try (FileInputStream input = new FileInputStream(filePath);) {
			// create the array to store the information before the location where store the width.
			byte[] beforeWidthByte = new byte[beforeWidthByteLength];
			input.read(beforeWidthByte, 0, beforeWidthByteLength);
			
			// create the array to store the width and get the width.
			byte[] widthByte = new byte[widthByteLength];
			input.read(widthByte, 0, widthByteLength);
			int width = toInt(widthByte, widthByteLength);
			
			// create the array to store the height and get the height.
			byte[] heightByte = new byte[heightByteLength];
			input.read(heightByte, 0, heightByteLength);
			int height = toInt(heightByte, heightByteLength);
			
			// create the array to store the information between where stores the height and the bits data.
			byte[] beforeBitsData = new byte[beforeBitsDataLength];
			input.read(beforeBitsData, 0, beforeBitsDataLength);
			
			// create the pixel array
			int[] pix = new int[width * height];
			byte[] bitsData = null;
			// follow the equals
			int widthBytes = (width * colorBits * bytesLength + 31) / 32 * 4;
			int length = widthBytes - colorBits * width;
			for (int i = height - 1; i >= 0; i--) {
				for (int j = 0; j < width; j++) {
					bitsData = new byte[colorBits];
					input.read(bitsData, 0, colorBits);
					pix[i * width + j] = 0xff000000 | toInt(bitsData, colorBits);
				}
				if (length > 0) {
					bitsData = new byte[length];
					input.read(bitsData, 0, length);
				}
			}
			return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));
		}

	}

	@Override
	public Image myWrite(Image image, String filepath) {
		try {
			BufferedImage bufferImage = toBufferedImage(image);
			File output = new File(filepath + ".bmp");
			ImageIO.write(bufferImage, "bmp", output);
			return bufferImage;
		} catch (Exception e) {
			Logger log = Logger.getLogger("ImplementImageIO");
            log.info("write error!");
		}
		return image;
	}

	/**
	 * Converts the byte to integer
	 */
	private int toInt(byte[] bytes, int length) {
		int num = 0;
		for (int i = 0; i < length; ++i) {
			num |= (bytes[i] & 0xff) << (i * bytesLength);
		}
		return num;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param image The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// Create a buffered image with transparency
		BufferedImage bufferImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		// Draw the image on to the buffered image
		Graphics graph = bufferImage.createGraphics();
		graph.drawImage(image, 0, 0, null);
		graph.dispose();

		// Return the buffered image
		return bufferImage;
	}
}