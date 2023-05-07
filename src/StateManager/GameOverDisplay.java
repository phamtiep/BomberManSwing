package StateManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GameOverDisplay extends JFrame{
	private JLabel imageLabel;
	private void display() {
		try {
		BufferedImage img = ImageIO.read(new File("GameOver.png"));
		ImageIcon ic = new ImageIcon(img);
		imageLabel.setIcon(ic);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public GameOverDisplay() {
		setSize(500,500);
		imageLabel = new JLabel();
		add(imageLabel);
		display();
		
	}
}
