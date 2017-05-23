package morphology;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import test.TestData;

public class MorphologyCommandsTest {
	private static Mat image;
	private static MorphologicalImageCommand testedCommand;
	private static List<Integer> kernelSizeList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 initializeKernelList();
	}

	private static void initializeKernelList() {
		kernelSizeList = new ArrayList<Integer>();
		kernelSizeList.add(0);
		kernelSizeList.add(1);
		kernelSizeList.add(10);
		kernelSizeList.add(100);
		kernelSizeList.add(5000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String testFilePath = TestData.TEST_DATA_FOLDER_PATH + "test0.jpg";
		image = Imgcodecs.imread(testFilePath);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testErosion() {
		testedCommand = new Erosion();
		executeCommand(testedCommand);
	}
	
	@Test
	public void testDilation() {
		testedCommand = new Dilation();
		executeCommand(testedCommand);
	}
	
	@Test
	public void testOpening() {
		testedCommand = new Opening();
		executeCommand(testedCommand);
	}
	
	@Test
	public void testClosing() {
		testedCommand = new Closing();
		executeCommand(testedCommand);
	}

	private void executeCommand(MorphologicalImageCommand testedCommand) {
		Iterator<Integer> kernelListIterator = kernelSizeList.iterator();
		
		while(kernelListIterator.hasNext()) {
			int kernelSize = (int)kernelListIterator.next();
			Mat result = testedCommand.execute(image, kernelSize);
			String resultImageFileFolderPath = TestData.MAIN_TEST_FOLDER + testedCommand.getClass().getSimpleName() + "/";
			Imgcodecs.imwrite(resultImageFileFolderPath + "result" + kernelSize + ".jpg", result);
		}
	}
	
}
