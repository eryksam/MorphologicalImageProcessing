package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import morphology.MorphologicalCommandRegister;
import morphology.MorphologicalImageCommand;
import morphology.MorphologicalImageCommandsFactory;
import morphology.OpenCVMorphologicalImageSystemFacade;

import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;

public class Frame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup;
	OpenCVMorphologicalImageSystemFacade facade;
	MorphologicalCommandRegister commandRegister;
	JButton processImageButton;
	BufferedImage originalImage;
	static BufferedImage processedImage;
	JSlider slider;
	ImageIcon image;
	JLabel processedImageLabel;
	JLabel originalImageLabel;
	JLabel kernelSizeLabel;

	public Frame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 458);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmOpenImage = new JMenuItem("Open image");
		mntmOpenImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageLoader = new JFileChooser();
				contentPane.add(imageLoader);
				int r = imageLoader.showOpenDialog(contentPane);
				if (r == JFileChooser.APPROVE_OPTION) {
					originalImage = (BufferedImage) readImage(imageLoader.getSelectedFile().getPath());
					displayImage(originalImage, originalImageLabel);
				}
			}
		});
		mnNewMenu.add(mntmOpenImage);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		commandRegister = MorphologicalCommandRegister.create();
		buttonGroup = new ButtonGroup();

		JButton processImageButton = new ProcessImageButton("Process image");
		processImageButton.setBounds(324, 325, 193, 49);
		processImageButton.addActionListener(new ProcessImageHandler());
		contentPane.add(processImageButton);

		JLabel lblOriginalImage = new JLabel("Original Image:");
		lblOriginalImage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOriginalImage.setBounds(10, 11, 119, 23);
		contentPane.add(lblOriginalImage);

		JLabel lblImageAfterProcessing = new JLabel("Image after processing:");
		lblImageAfterProcessing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImageAfterProcessing.setBounds(10, 215, 178, 23);
		contentPane.add(lblImageAfterProcessing);

		JLabel lblKernelSize = new JLabel("Kernel size:");
		lblKernelSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKernelSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblKernelSize.setBounds(470, 31, 93, 14);
		contentPane.add(lblKernelSize);

		JRadioButton erodeRadioButton = new MorphologicalCommandRadioButton("erode",
				commandRegister.MORPHOLOGY_EROSION);
		buttonGroup.add(erodeRadioButton);
		erodeRadioButton.setBounds(324, 52, 109, 23);
		contentPane.add(erodeRadioButton);

		JRadioButton dilateRadioButton = new MorphologicalCommandRadioButton("dilate",
				commandRegister.MORPHOLOGY_DILATION);
		buttonGroup.add(dilateRadioButton);
		dilateRadioButton.setBounds(324, 110, 109, 23);
		contentPane.add(dilateRadioButton);

		JRadioButton openRadioButton = new MorphologicalCommandRadioButton("open", commandRegister.MORPHOLOGY_OPENING);
		buttonGroup.add(openRadioButton);
		openRadioButton.setBounds(324, 181, 109, 23);
		contentPane.add(openRadioButton);

		JRadioButton closeRadioButton = new MorphologicalCommandRadioButton("close",
				commandRegister.MORPHOLOGY_CLOSING);
		buttonGroup.add(closeRadioButton);
		closeRadioButton.setBounds(324, 242, 109, 23);
		contentPane.add(closeRadioButton);

		slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.addChangeListener(new KernelSizeHandler());
		slider.setBounds(470, 68, 93, 197);
		contentPane.add(slider);

		originalImageLabel = new JLabel("");
		originalImageLabel.setLabelFor(originalImageLabel);
		originalImageLabel.setForeground(Color.WHITE);
		originalImageLabel.setBackground(Color.WHITE);
		originalImageLabel.setBounds(10, 36, 297, 152);
		contentPane.add(originalImageLabel);
		
		processedImageLabel = new JLabel("");
		processedImageLabel.setBackground(Color.WHITE);
		processedImageLabel.setBounds(10, 237, 297, 152);
		contentPane.add(processedImageLabel);
		
		kernelSizeLabel = new JLabel("");
		kernelSizeLabel.setBounds(573, 24, 65, 30);
		Integer value = slider.getValue();
		kernelSizeLabel.setText(value.toString());
		contentPane.add(kernelSizeLabel);
	}

	private BufferedImage readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void displayImage(BufferedImage image, JLabel component) {
		Image tmpImage = image.getScaledInstance(component.getWidth(), component.getHeight(), java.awt.Image.SCALE_SMOOTH);
		component.setIcon(new ImageIcon(tmpImage));
	}

	private class ProcessImageHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String processImageCommand = buttonGroup.getSelection().getActionCommand();
			MorphologicalImageCommand command = commandRegister.getCommand(processImageCommand);
			Mat convertedOriginalImage = BufferedImageConverter.convertToMatImage(originalImage);
			Mat processedImage = command.execute(convertedOriginalImage, slider.getValue());
			Frame.processedImage = BufferedImageConverter.convertToBufferedImage(processedImage);
			displayImage(Frame.processedImage, processedImageLabel);
		}
	}
	
	private class KernelSizeHandler implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			Integer kernelSize = slider.getValue();
			kernelSizeLabel.setText(kernelSize.toString());
		}

		
		
	}
}
