package Map;


import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

import Config.GameConfig;
import Enemies.Enemy;
import Enitity.Brick;
import Enitity.EntityManager;
import Enitity.Grass;
import Enitity.Wall;

public class LeverMap {
        private char[][] mapHash = new char[21][20];
        private Grass grass = new Grass(0,0); 
        private Wall wall = new Wall(0 , 0); 

        
        private final EntityManager entitiesManager = EntityManager.getInstance();

        private static class SingletonHelper {
            private static final LeverMap INSTANCE = new LeverMap();
        }

        public static LeverMap getInstance() {
            return SingletonHelper.INSTANCE;
        }

        public static void init() {
            Grass.initGrass();
            Wall.initWall();
        }

        public void render(Graphics g) {
        	inputLever();
            for (int i = 0; i < mapHash.length; ++i) {
                for (int j = 0; j < mapHash[i].length; ++j) {
                    grass.setLocation(j, i);
                    grass.render(g);
                    if (mapHash[i][j] == getHash("wall")) {
                        wall.setLocation(j,i);
                        wall.render(g);
                    }
                }
            }
        }
        
        public int getWidth() {
            return this.mapHash[0].length * GameConfig.SIZE_BLOCK;
        }

        public int getHeight() {
            return this.mapHash.length * GameConfig.SIZE_BLOCK;
        }
        
        public void destroyBrick(int i, int j) {
            List<Brick> brickList = EntityManager.getInstance().brick;
           
            brickList.forEach(brick -> {
                if ((j * GameConfig.SIZE_BLOCK) == brick.getX() && (i * GameConfig.SIZE_BLOCK) == brick.getY()) {
                    brick.setDestroyed(true);
                }
            });

       
        }

        public char[][] getMapHash() {
            return this.mapHash;
        }

        public char getHashAt(int i, int j) {
            if (i < 0 || j < 0 || i >= this.mapHash.length || j >= this.mapHash[0].length) {
                return getHash("null");
            }
            return mapHash[i][j];
        }

        public void setHashAt(int i, int j, String input) {
            mapHash[i][j] = getHash(input);
        }

        public char getHash(String input) {
            char output = ' ';
            switch (input) {
                case "grass":
                    output = ' ';
                    break;
                case "brick":
                    output = '*';
                    break;
                case "wall":
                    output = '#';
                    break;
                case "bomb":
                    output = 'B';
                    break;
                case "coin":
                    output = '$';
                    break;
                case "portal":
                    output = 'x';
                    break;
                case "null":
                    output = '!';
                    break;
                default:
                    break;
            }
            return output;
        }
        
        
        
        
       public void inputLever() {
  
    	   List<Brick> brickList = entitiesManager.brick;
    	   
    	   try {
    		   File input = new File("res/Mapvip.txt"); 
    		   Scanner scanner = new Scanner(input);
    		   int rows = scanner.nextInt(); 
    		   int cols = scanner.nextInt(); 
    		   String temp = scanner.nextLine(); 
    		   
    		   
   
    		   mapHash = new char[rows][cols]; 
    		   
    		   for(int i = 0; i < rows ; i++) {
    			   String tile = scanner.nextLine(); // lay 1 hang 
    			   for(int j = 0; j < cols; j++) {
    				  char hash = tile.charAt(j); // doc tung phan tu
    				  
    				  switch (hash) 
    				  { 
                      case '*': {
                          brickList.add(new Brick(i , j));
                          break;
                      }
                      default:
                          break;
                  }
                  mapHash[i][j] = hash;
                 }
    				  
    		}
		}
    	   catch (Exception e) {
			System.out.println("EROR READING FILE");
		}
    	   
    	   
       }
        
        
        
}


