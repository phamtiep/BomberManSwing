package Enitity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Graphics.Sprite;
import Time.Timers;

public abstract class Item extends Entity {
	protected double timeAppear = 10000;
	protected double timePowerUp = 0.0;
	protected boolean appear = false;
	protected boolean eaten = false;
	protected boolean done = false;
	protected double time = 0;
	protected BufferedImage Item ;
	public Item(int x, int y) {
		super(x,y);
		sprite = new Sprite("/ItemBomberMan.png",1);
	}
	
	public abstract void changePower();
	public abstract BufferedImage getImage();
	@Override 
	public void render(Graphics g) {
		if(appear) {
			g.drawImage(Item,x*32,y*32,null);
		}
		
	}
	
	@Override
	public void update() {
		if(appear) {
			time += Timers.getInstance().getDeltaTime();
			if(time>= timeAppear||eaten) {
				appear = false;
				time = 0;
				if(time>= timeAppear) {
					done = true;
				}
			}
		}
		if(eaten&&!done) {
			time+=Timers.getInstance().getDeltaTime();
			changePower();
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
