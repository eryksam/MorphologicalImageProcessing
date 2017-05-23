package morphology;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class Kernel {
	private static Mat kernel;
	
	public static Mat createKernel(int kernelSize) {
		if(kernel == null) {
			kernel = Mat.ones(new Size(kernelSize,kernelSize), CvType.CV_32F);
		}
		return kernel;
	}
}
