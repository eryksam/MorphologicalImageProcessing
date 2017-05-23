package application;

import java.awt.Font;

import javax.swing.JRadioButton;

public class MorphologicalCommandRadioButton extends JRadioButton {

	public MorphologicalCommandRadioButton() {
		super();
		initialize();
	}

	public MorphologicalCommandRadioButton(String text, String actionCommand) {
		super(text);
		setActionCommand(actionCommand);
		initialize();
	}
	
	private void initialize() {
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		
	}

}
