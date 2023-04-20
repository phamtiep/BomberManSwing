package Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Config.Direction;
import Enitity.Entity;
import Enitity.LivingEntity;
import Graphics.Sprite;
import Graphics.SpriteSheet;

public abstract class Enemy extends LivingEntity {
		
		protected BufferedImage[] animation; 
		protected boolean done = false; 
		protected double moveX = 0; 
		protected double moveY = 0; 
		protected boolean canMoveR = false; 
		protected boolean canMoveL = false; 
		protected boolean canMoveU = false;
		protected boolean canMoveD = false; 
		protected Random random = new Random(); 
		
		protected List<Direction> directionList = new ArrayList<>(); 
		protected Sprite sheet; 
		protected int score; 
		
		public Enemy(int x , int y) {
			super(x , y); 
			speed = 1; 
		}
		
		// doi tiep code animation
		public void render(Graphics g) {
			
		}
		
		public void update() {
			if(!alive) {
				move(); 
			}
		}
		/**
		public boolean checkMapHash(int i , int j) {
			return true; 
		}
		**/
		
		protected void initDirectionList() {
			this.directionList.clear();
			directionList.add(Direction.UP);
			directionList.add(Direction.DOWN); 
			directionList.add(Direction.LEFT); 
			directionList.add(Direction.RIGHT); 
		}
		
		protected void checkMove() {
			if (currenDirection == null) {
				initDirectionList();
				return;
			}
			switch (currenDirection) {
			case UP: {
				if (canMoveU) {
					moveY = -speed;
				} else {
					directionList.remove(Direction.UP);
				}
				break;
			}
			case DOWN: {
				if (canMoveD) {
					moveY = speed;
					initDirectionList();
				} else {
					directionList.remove(Direction.DOWN);
				}
				break;
			}
			case LEFT: {
				if (canMoveL) {
					moveX = -speed;
					initDirectionList();
				} else {
					directionList.remove(Direction.LEFT);
				}
				break;
			}
			case RIGHT: {
				if (canMoveR) {
					moveX = speed;
					initDirectionList();
				} else {
					directionList.remove(Direction.RIGHT);
				}
				break;
			}
			default:
				break;
			}
		}

}
