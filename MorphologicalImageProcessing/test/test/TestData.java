package test;

public class TestData {
	
	public static final String MAIN_TEST_FOLDER = "./testData/";
	
	public static final String TEST_DATA_FOLDER_PATH = MAIN_TEST_FOLDER + "Data/";
	
	public static final String DEFAULT_TEST_IMAGE_FILE_PATH = TestData.TEST_DATA_FOLDER_PATH + "test0.jpg";
	
	public static final String buildTestResultFolderPathForClass(String classSimpleName) {
		return MAIN_TEST_FOLDER + classSimpleName + "/";
	}
}
