import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageProcessorTest {
	private ImplementImageIO imageIO;
	private ImplementImageProcessor imageProcessor;
	private Image bmptestImg1;
	private Image bmptestImg2;

	@Before
	public void init() throws IOException {
		imageIO = new ImplementImageIO();
		imageProcessor = new ImplementImageProcessor();
		bmptestImg1 = ImageIO.read(new File("./bmptest/1.bmp"));
		bmptestImg2 = ImageIO.read(new File("./bmptest/2.bmp"));
	}

	/**
	 * Test myRead() method
	 */
	@Test
	public void testMyRead() throws IOException {
		Image readImage = imageIO.myRead("./bmptest/1.bmp");
		assertEquals(true, imgCompare(readImage, bmptestImg1));
	}

	/**
	 * Test myWrite() method
	 */
	@Test
	public void testMyWrite() {
		Image writeImage = imageIO.myWrite(bmptestImg1, "./bmptest/test1.bmp");
		assertEquals(true, imgCompare(writeImage, bmptestImg1));
	}

	/**
	 * Test showChanelR() method by using "./bmptest/1.bmp"
	 */
	@Test
	public void testShowChanelR1() throws IOException {
		Image redGoal1 = ImageIO.read(new File("./bmptest/goal/1_red_goal.bmp"));
		Image redProcess1 = imageProcessor.showChanelR(bmptestImg1);
		assertEquals(true, imgCompare(redProcess1, redGoal1));
	}

	/**
	 * Test showChanelR() method by using "./bmptest/2.bmp"
	 */
	@Test
	public void testShowChanelR2() throws IOException {
		Image redGoal2 = ImageIO.read(new File("./bmptest/goal/2_red_goal.bmp"));
		Image redProcess2 = imageProcessor.showChanelR(bmptestImg2);
		assertEquals(true, imgCompare(redProcess2, redGoal2));
	}

	/**
	 * Test showChanelG() method by using "./bmptest/1.bmp"
	 */
	@Test
	public void testShowChanelG1() throws IOException {
		Image greenGoal1 = ImageIO.read(new File("./bmptest/goal/1_green_goal.bmp"));
		Image greenProcess1 = imageProcessor.showChanelG(bmptestImg1);
		assertEquals(true, imgCompare(greenProcess1, greenGoal1));
	}

	/**
	 * Test showChanelG() method by using "./bmptest/2.bmp"
	 */
	@Test
	public void testShowChanelG2() throws IOException {
		Image greenGoal2 = ImageIO.read(new File("./bmptest/goal/2_green_goal.bmp"));
		Image greenProcess2 = imageProcessor.showChanelG(bmptestImg2);
		assertEquals(true, imgCompare(greenProcess2, greenGoal2));
	}

	/**
	 * Test showChanelB() method by using "./bmptest/1.bmp"
	 */
	@Test
	public void testShowChanelB1() throws IOException {
		Image blueGoal1 = ImageIO.read(new File("./bmptest/goal/1_blue_goal.bmp"));
		Image blueProcess1 = imageProcessor.showChanelB(bmptestImg1);
		assertEquals(true, imgCompare(blueProcess1, blueGoal1));
	}

	/**
	 * Test showChanelB() method by using "./bmptest/2.bmp"
	 */
	@Test
	public void testShowChanelB2() throws IOException {
		Image blueGoal2 = ImageIO.read(new File("./bmptest/goal/2_blue_goal.bmp"));
		Image blueProcess2 = imageProcessor.showChanelB(bmptestImg2);
		assertEquals(true, imgCompare(blueProcess2, blueGoal2));
	}

	/**
	 * Test showGray() method by using "./bmptest/1.bmp"
	 */
	@Test
	public void testShowGray1() throws IOException {
		Image grayGoal1 = ImageIO.read(new File("./bmptest/goal/1_gray_goal.bmp"));
		Image grayProcess1 = imageProcessor.showGray(bmptestImg1);
		assertEquals(true, imgCompare(grayProcess1, grayGoal1));
	}

	/**
	 * Test showGray() method by using "./bmptest/2.bmp"
	 */
	@Test
	public void testShowGray2() throws IOException {
		Image grayGoal2 = ImageIO.read(new File("./bmptest/goal/2_gray_goal.bmp"));
		Image grayProcess2 = imageProcessor.showGray(bmptestImg2);
		assertEquals(true, imgCompare(grayProcess2, grayGoal2));
	}

	/**
	 * Compares every pixel of two images to find out if they are the same.
	 */
	public boolean imgCompare(Image image1, Image image2) {
		BufferedImage bufferImage1 = ImplementImageIO.toBufferedImage(image1);
		BufferedImage bufferImage2 = ImplementImageIO.toBufferedImage(image2);

		int width1 = image1.getWidth(null);
		int height1 = image1.getHeight(null);
		int width2 = image2.getWidth(null);
		int height2 = image2.getHeight(null);
		if (width1 != width2 || height1 != height2) {
			return false;
		}

		for (int i = 0; i < width1; i++) {
			for (int j = 0; j < height1; j++) {
				if (bufferImage1.getRGB(i, j) != bufferImage2.getRGB(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

}
