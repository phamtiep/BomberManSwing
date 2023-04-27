package Graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class SpriteSheet {
	String path; 
	public BufferedImage img; 
	
	public SpriteSheet(String path) {
		this.path = path; 
		load(); 
	}

	private void load() {
		InputStream is = getClass().getResourceAsStream(path); 
		try {
			img = ImageIO.read(is);  
		} catch (Exception e) {
			 System.out.println("Khong doc duoc anh!");
			 System.out.println(path);
		}
	}
	
	public BufferedImage getImg() {
		return img; 
	}
	
	public BufferedImage getSprite(int x , int y) {
		// tra ve subimg toa do x y trong mot anh 
		return this.img.getSubimage(x*32, y*32, 32, 32); 
	}
	
}
