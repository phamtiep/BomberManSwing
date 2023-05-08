package Enitity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import Graphics.Sprite;
import Map.LevelMap;
import Time.Timers;

public abstract class Item extends Entity {
	protected double timeAppear = 10000;
	protected double timePowerUp = 0.0;
	protected boolean appear = false;
	protected boolean eaten = false;
	protected boolean done = false;
	protected double time = 0;
	protected Image Item = null;
	LevelMap level = LevelMap.getInstance(); 
	public Item(int x, int y) {
		super(x,y);
		sprite = new Sprite("/ItemBomberman.png",1);
	}
	
	public abstract void checkEaten(); 
	public abstract void changePower();
	public abstract BufferedImage getImage();
	@Override 
	public void render(Graphics g) {
		if(appear) {
			g.drawImage(Item,x,y,null); 
		}	
	}
	
	@Override
	public void update() {
		if(level.getHashAt(x, y) != level.getHash("brick") && !done) {
			appear = true; 
		}
		if(appear) {
			checkEaten();
		}
		
		if(eaten) {
			if(!done) {
			    done = true;
			changePower();
			}
		}
	}

	public double getTimePowerUp() {
		return timePowerUp;
	}

	public void setTimePowerUp(double timePowerUp) {
		this.timePowerUp = timePowerUp;
	}

	public boolean isAppear() {
		return appear;
	}

	public void setAppear(boolean appear) {
		this.appear = appear;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public boolean isDone() {
		return done;
	}

	
}
