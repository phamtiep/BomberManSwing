package Enitity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Graphics.Sprite;

public abstract class Item extends Entity {
	protected BufferedImage Item ;
	protected boolean _active = false;
	//public static Sprite sprite;
	//protected int _duration
	public Item(int x, int y) {
		super(x,y);
		sprite = new Sprite("/ItemBomberMan.png");
	}
	
	
	@Override 
	public void render(Graphics g) {
		g.drawImage(Item,x*32,y*32,null);
	}
	
	@Override
	public void update() {
		
	}
	
	public abstract void remove();
	
	
	
	
	
}
