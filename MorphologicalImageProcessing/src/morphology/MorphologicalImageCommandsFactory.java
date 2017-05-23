package morphology;

public class MorphologicalImageCommandsFactory {

	private static MorphologicalImageCommandsFactory singleton = new MorphologicalImageCommandsFactory();
	
	public MorphologicalImageCommandsFactory(){
		
	}

	public static MorphologicalImageCommand getCloseCommand(){
		return null;
	}

	public static MorphologicalImageCommand getDilationCommand(){
		return null;
	}

	public static MorphologicalImageCommand getErosionCommand(){
		return null;
	}

	public static MorphologicalImageCommand getOpenCommand(){
		return null;
	}

}