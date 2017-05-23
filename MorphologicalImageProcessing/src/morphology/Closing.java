package morphology;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Closing implements MorphologicalImageCommand {

	protected Closing() {};
	
	@Override
	public Mat execute(Mat image, int kernelSize) {
		Mat resultImage = new Mat();
		Imgproc.morphologyEx(image, resultImage, Imgproc.MORPH_CLOSE, Kernel.createKernel(kernelSize));
		return resultImage;
	}

}