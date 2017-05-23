package morphology;

import java.util.HashMap;
import java.util.Map;

public class MorphologicalCommandRegister {
	private Map<String,MorphologicalImageCommand> commandMap = new HashMap<>();
	private static MorphologicalCommandRegister singleton;
	
	public static final String MORPHOLOGY_EROSION = "Erosion";
	public static final String MORPHOLOGY_DILATION = "Dilation";
	public static final String MORPHOLOGY_OPENING = "Opening";
	public static final String MORPHOLOGY_CLOSING = "Closing";
	
	private MorphologicalCommandRegister() {
		commandMap.put(MORPHOLOGY_EROSION, new Erosion());
		commandMap.put(MORPHOLOGY_DILATION, new Dilation());
		commandMap.put(MORPHOLOGY_OPENING, new Opening());
		commandMap.put(MORPHOLOGY_CLOSING, new Closing());
	}
	
	public static MorphologicalCommandRegister create() {
		if(!singletonCreated()) {
			singleton = new MorphologicalCommandRegister();
		}
		return singleton;
	}
	
	public MorphologicalImageCommand getCommand(String name) {
		return commandMap.get(name);
	}
	
	private static boolean singletonCreated() {
		return singleton != null;
	}
}
