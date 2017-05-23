package morphology;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Dilation implements MorphologicalImageCommand {

	protected Dilation() {};
	
	public Mat execute(Mat image, int kernelSize){
		Mat resultImage = new Mat();
		Imgproc.dilate(image, resultImage, Kernel.createKernel(kernelSize));
		return resultImage;
	}

}