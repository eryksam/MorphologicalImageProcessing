package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.rules.TemporaryFolder;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class BufferedImageConverter {
	private static final String temporaryImageFilePath = "./tmp/image.jpg";

	public static BufferedImage convertToBufferedImage(Mat matImage){
		Imgcodecs.imwrite(temporaryImageFilePath, matImage);
		BufferedImage convertedBufferedImage;
		try {
			convertedBufferedImage = ImageIO.read(new File(temporaryImageFilePath));
			return convertedBufferedImage;
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
	public static Mat convertToMatImage(BufferedImage bufferedImage) {
		try {
			ImageIO.write(bufferedImage, "jpg", new File(temporaryImageFilePath));
			Mat convertedBufferedImage = Imgcodecs.imread(temporaryImageFilePath);
			return convertedBufferedImage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}