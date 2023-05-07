package Enitity;

import Config.Direction;

public abstract class LivingEntity extends Entity {
	
	protected double speed;  
	protected int live; 
	protected boolean destroyed = false;

    /**
     * @return the destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    protected boolean moving = false;
	protected Direction currentDirection; 
	
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
	
	public Direction getCurrenDirection() {
		return this.currentDirection; 
	}
	
	public void setCurrenDirection(Direction other) {
		this.currentDirection = other; 
		
		
	}
	
	
	
}
