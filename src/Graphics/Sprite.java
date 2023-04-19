package Graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Sprite {
	private int x , y;
	public BufferedImage[][] image;
	String path = "top1.java"; // duong dan toi anh 
	private SpriteSheet sheet; 
	
	public Sprite(int x , int y) {  
		sheet = new SpriteSheet(path); 
		load(); 
	}
	// ham load subimg vao mot ma tran voi kich thuoc la row / 32 va cols / 32; 
	private void load() {
		int row = sheet.getImg().getWidth() / 32 ;
		int cols = sheet.getImg().getWidth() / 32; 
		image = new BufferedImage[row][cols]; 
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0; j < cols; j++) {
					image[i][j] =  sheet.getSprite(i, j);
			}
		}
	}
	
	public BufferedImage getSprite(int x , int y) {
		 return image[x][y]; 
	}
	
	
	

}
