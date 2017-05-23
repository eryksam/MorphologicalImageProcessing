package morphology;

import org.opencv.core.Mat;

public interface MorphologicalImageCommand {

	public Mat execute(Mat image, int kernelSize);

}