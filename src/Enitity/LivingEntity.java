package Enitity;

public abstract class LivingEntity extends Entity {
	
	protected double speed;  
	protected int live; 
	protected boolean alive = true;
	protected boolean moving = false; 
	public int timeafter = 80 ; 
	
	public LivingEntity(int x , int y) {
		super(x , y);
		live = 1; 
	}
	
	protected abstract void move();

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public boolean isKilled() {
		return alive|| live <= 0;
	}

	public void setDestroyed(boolean destroyed) {
		this.alive = destroyed;
	} 
	
	
	
}
