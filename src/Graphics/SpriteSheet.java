package Graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class SpriteSheet {
	String path; 
	public BufferedImage img; 
	int width,height;
	public SpriteSheet(String path,int width, int height) {
	  this.width = width;
	  this.height = height;
		this.path = path; 
		load(); 
	}
	public SpriteSheet(String path) {
	    this.width = 32;
	    this.height = 32;
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
	    if(x*width  + width > img.getWidth()) return null;
	    if(y*height + height > img.getHeight()) return null;
		return this.img.getSubimage(x*width, y*height, width, height); 
	}
	
}
