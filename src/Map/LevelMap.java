package Map;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

import Boomber.Bomber;
import Config.GameConfig;
import Enemies.Enemy;
import Enemies.Ghost;
import Enemies.Otopus;
import Enitity.Brick;
import Enitity.EntityManager;
import Enitity.Grass;
import Enitity.Wall;

public class LevelMap {
    private char[][] mapHash = new char[21][21];
    private Grass grass = new Grass(0, 0);
    private Wall wall = new Wall(0, 0);

    private final EntityManager entitiesManager = EntityManager.getInstance();

    private static class SingletonHelper {
        private static final LevelMap INSTANCE = new LevelMap();
    }

    public static LevelMap getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void init() {
        grass.initGrass();
        wall.initWall();
    }

    public void render(Graphics g) {
        for (int i = 0; i < mapHash.length; ++i) {
            for (int j = 0; j < mapHash[i].length; ++j) {
                if (mapHash[i][j] == getHash("wall")) {
                    wall.setLocation(j*32, i*32);
                    wall.render(g);
                }
                else{
                    grass.setLocation(j*32, i*32);
                    grass.render(g);
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
            if ((j * GameConfig.SIZE_BLOCK) == brick.getX()
                    && (i * GameConfig.SIZE_BLOCK) == brick.getY()) {
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
      
        default:
            break;
        }
        return output;
    }

    public void inputLever() {

        List<Brick> brickList = entitiesManager.brick;
        String tile = null;
        try {
            File input = new File("res/Mapvip.txt");
            
            Scanner scanner = new Scanner(input);
            int cols = scanner.nextInt();
            int rows = scanner.nextInt();
           String temp = scanner.nextLine();
           
            mapHash = new char[rows][cols];
                //System.out.println(rows + " " + cols);
            for (int i = 0; i < rows; i++) {
                tile = scanner.nextLine(); // lay 1 hang
                //System.out.println(tile);
                for (int j = 0; j < cols; j++) {
                    char hash = tile.charAt(j); // doc tung phan tu
                    //System.out.println(hash);
                    switch (hash) {
                    case '*': {
                        brickList.add(new Brick(j*32, i*32));
                      
                        break;
                    }
                    case '#': {
                        
                        break;
                    }
                    case '1' :{
                        entitiesManager.enemy.add(new Ghost(j*32,i*32));
                        hash = getHash("grass");
                        break;
                    }
                    
                    case '2' : {
                        entitiesManager.enemy.add(new Otopus(j*32,i*32));
                        hash = getHash("grass");
                        break;
                    }
                    
                    case ' ' : {
                        hash = getHash("grass");
                        break;
                    }
                    case 'p' :{
                        hash = getHash("grass");
                        EntityManager.getInstance().bomber = new Bomber(j, i);
                    }
                    default:
                        break;
                    }
                    mapHash[i][j] = hash;
                }

            }
        } catch (Exception e) {
            System.out.println(tile);
        }

    }

}
