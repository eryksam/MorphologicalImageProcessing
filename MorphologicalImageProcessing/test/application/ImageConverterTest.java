package application;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import test.TestData;

public class ImageConverterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void convertToBufferedImageTest() {
		Mat image = Imgcodecs.imread(TestData.DEFAULT_TEST_IMAGE_FILE_PATH);
		BufferedImage resultImage = BufferedImageConverter.convertToBufferedImage(image);
		String resultImageFilePath = TestData.buildTestResultFolderPathForClass(BufferedImageConverter.class.getSimpleName()) + "toImageResult.jpg";
		try {
			ImageIO.write((RenderedImage) resultImage, "jpg", new File(resultImageFilePath));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void convertToMatImageTest() {
		try {
			BufferedImage image = ImageIO.read(new File(TestData.DEFAULT_TEST_IMAGE_FILE_PATH));
			Mat resultImage = BufferedImageConverter.convertToMatImage(image);
			String resultImageFilePath = TestData.buildTestResultFolderPathForClass(BufferedImageConverter.class.getSimpleName()) + "toMatResult.jpg";
			Imgcodecs.imwrite(resultImageFilePath, resultImage);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
