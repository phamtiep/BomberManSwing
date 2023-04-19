package Graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class SpriteSheet {
	String path; 
	public int SIZE = 0;
	public int[] pixels; 
	public BufferedImage img; 
	
	public SpriteSheet(String path , int size) {
		SIZE = size; 
		this.path = path; 
		pixels = new int[size * size];
		load(); 
	}

	private void load() {
		InputStream is = getClass().getResourceAsStream(path); 
		try {
			img = ImageIO.read(is); 
		} catch (Exception e) {
			 System.out.println("Khong doc duoc anh!");
		}
	}
}
