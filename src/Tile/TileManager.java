package Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Enitity.Entity;

public class TileManager {
	
	public Entity GRASS , BRICK , WALL;
	 
	public ArrayList<Entity> tiles = new ArrayList<>();
	
	public TileManager() {
		creatTile(); 
	}

	private void creatTile() {
		tiles.add(BRICK = new Brick(0 , 0)); 
		tiles.add(GRASS = new Grass(0, 0));
		tiles.add(WALL = new Wall(0 , 0)); 
	}
	
	public Entity getTile(int id) {
		return tiles.get(id); 
	}
	
}
