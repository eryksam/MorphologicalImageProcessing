package morphology;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

public class Opening implements MorphologicalImageCommand {

	protected Opening(){};
	
	public Mat execute(Mat image, int kernelSize) {
		Mat resultImage = new Mat();
		Mat kernel = Kernel.createKernel(kernelSize);
		Imgproc.erode(image, resultImage, kernel);
		Imgproc.dilate(resultImage, image, kernel);
		return image;
	}

}