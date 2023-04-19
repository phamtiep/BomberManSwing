package Graphics;

public class Screen {
	protected int width , height; 
	public int [] pixels; 
	
	
	public static int xOffset = 0 , yOffset = 0; 
	
	public Screen(int width , int height) {
		this.width = width; 
		this.height = height;
		pixels = new int[width * height];  
	}
	
	
}
