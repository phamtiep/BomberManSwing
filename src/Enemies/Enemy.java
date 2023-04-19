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
		
		public boolean checkMapHash(int i , int j) {
			
		}
		
		
}
