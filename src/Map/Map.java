package Map;

import java.awt.Graphics;
import java.util.List;

import Config.GameConfig;
import Enitity.Brick;
import Enitity.EntityManager;
import Enitity.Grass;
import Enitity.Wall;

public class Map {

        private char[][] mapHash;
        private Grass grass;
        private Wall wall;

        
        private final EntityManager entitiesManager = EntityManager.getInstance();

        private static class SingletonHelper {
            private static final Map INSTANCE = new Map();
        }

        public static Map getInstance() {
            return SingletonHelper.INSTANCE;
        }

       

        public static void init() {
            Grass.initGrass();
            Wall.initWall();
            
           
        }

        public void render(Graphics g) {
            for (int i = 0; i < mapHash.length; ++i) {
                for (int j = 0; j < mapHash[i].length; ++j) {
                    grass.setLocation(32 * j, 32 * i);
                    grass.render(g);
                    if (mapHash[i][j] == getHash("wall")) {
                        wall.setLocation(32 * j, 32 * i);
                        wall.render(g);
                    }
                }
            }
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
    }


