package morphology;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import javax.swing.JPanel;

import org.opencv.core.Mat;

public class OpenCVMorphologicalImageSystemFacade extends JPanel {

	private MorphologicalCommandRegister commandRegister;

	public OpenCVMorphologicalImageSystemFacade(){
		commandRegister = MorphologicalCommandRegister.create();
	}

	public void displayImage(Mat image, JPanel panel) {
		
	}

	public void processImage(String chosenCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	
	
}