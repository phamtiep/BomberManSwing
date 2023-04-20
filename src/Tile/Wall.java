package Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Enitity.Entity;
import Graphics.Sprite;

public class Wall extends Entity {
	
	public static final boolean initialized = false; 
	private  BufferedImage wall ; 
	private boolean appear = false;
	private boolean canPass = false; 
	
	public Wall(int x , int y) {
		super(x , y); 
		initWall(); 
	}
	
	public void initWall() {
		sprite = new Sprite("/anhgame.png"); 
		wall = sprite.getSprite(3, 5); 
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wall , 4*32 , 0 , null); 
	}

	@Override
	public BufferedImage getImg() {
		return wall;
	}

	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}

	public boolean isAppear() {
		return appear;
	}

	public void setAppear(boolean appear) {
		this.appear = appear;
	}

	public boolean isCanPass() {
		return canPass;
	}

	public void setCanPass(boolean canPass) {
		this.canPass = canPass;
	}
	
	

}
