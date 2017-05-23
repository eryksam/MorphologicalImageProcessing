package application;

import java.awt.image.ImageProducer;

import org.opencv.core.Core;

public class Main {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Frame frame = new Frame();
		frame.setVisible(true);
	}
}
