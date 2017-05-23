package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 3120405200442076123L;
	private Image image;

	public ImagePanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2D = (Graphics2D) g.create();

		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
