package Bomb;

import java.util.ArrayList;
import java.util.List;

import Enitity.Entity;
import Map.LevelMap;


public class Bomb extends Entity {
    public static boolean initialized = false;
    public static final int DEFAULT_FLAME_LENGTH = 3;
    private Image image;
    private static List<Image> bombs;
    private LevelMap levelMap = LevelMap.getInstance();
    private final List<Flame> flameList = new ArrayList<>();
    private boolean allowPass = true;  // cho phép bomber vượt qua
    private boolean explode = false;
    private static int flameLength = DEFAULT_FLAME_LENGTH;
    private double timeBeforeExplode = 2000;
    private final double flameTime = 500;
    private boolean hasFlame = false;
    private double time = 0;
    private boolean done = false;
    
    public Bomb() {
        // TODO Auto-generated constructor stub
    }
    
    private void explosion() {
        
        LevelMap levelMap  = LevelMap.getInstance();
        hasFlame = true;
        
        flameList.add(new Flame(x, y, width, height, Flame.FlameDirection.CENTER, false));

        // check left
        int l = 1;
        for (; l <= flameLength; l++) {
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 - l) == levelMap.getHash("brick")) {
                levelMap.destroyBrick((int) y / 32, (int) x / 32 - l);
                break;
            }
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 - l) == levelMap.getHash("bomb")) {
                setTimeExplodeBomb(x - 32 * l, y);
                break;
            }
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 - l) == levelMap.getHash("wall")) {
                break;
            }

        }
        for (int i = 1; i < l; i++) {
            if (i != (l - 1)) {
                flameList.add(new Flame(x - 32 * i, y, width, height, Flame.FlameDirection.LEFT, false));
            } else {
                flameList.add(new Flame(x - 32 * i, y, width, height, Flame.FlameDirection.LEFT, true));
            }
        }

        //check right
        int r = 1;
        for (; r <= flameLength; r++) {
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 + r) == levelMap.getHash("brick")) {
                levelMap.destroyBrick((int) y / 32, (int) x / 32 + r);
                break;
            }
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 + r) == levelMap.getHash("bomb")) {
                setTimeExplodeBomb(x + 32 * r, y);
                break;
            }
            if (levelMap.getHashAt((int) y / 32, (int) x / 32 + r) == levelMap.getHash("wall")) {
                break;
            }
        }
        for (int i = 1; i < r; i++) {
            if (i != (r - 1)) {
                flameList.add(new Flame(x + 32 * i, y, width, height, Flame.FlameDirection.RIGHT, false));
            } else {
                flameList.add(new Flame(x + 32 * i, y, width, height, Flame.FlameDirection.RIGHT, true));
            }
        }

        // check up
        int u = 1;
        for (; u <= flameLength; u++) {
            if (levelMap.getHashAt((int) y / 32 - u, (int) x / 32) == levelMap.getHash("brick")) {
                levelMap.destroyBrick((int) y / 32 - u, (int) x / 32);
                break;
            }
            if (levelMap.getHashAt((int) y / 32 - u, (int) x / 32) == levelMap.getHash("bomb")) {
                setTimeExplodeBomb(x, y - 32 * u);
                break;
            }
            if (levelMap.getHashAt((int) y / 32 - u, (int) x / 32) == levelMap.getHash("wall")) {
                break;
            }
        }
        for (int i = 1; i < u; i++) {
            if (i != (u- 1)) {
                flameList.add(new Flame(x, y - 32 * i, width, height, Flame.FlameDirection.UP, false));
            } else {
                flameList.add(new Flame(x, y - 32 * i, width, height, Flame.FlameDirection.UP, true));
            }
        }

        // check down
        int d = 1;
        for (; d <= flameLength; d++) {
            if (levelMap.getHashAt((int) y / 32 + d, (int) x / 32) == levelMap.getHash("brick")) {
                levelMap.destroyBrick((int) y / 32 + d, (int) x / 32);
                break;
            }
            if (levelMap.getHashAt((int) y / 32 + d, (int) x / 32) == levelMap.getHash("bomb")) {
                setTimeExplodeBomb(x, y + 32 * d);
                break;
            }
            if (levelMap.getHashAt((int) y / 32 + d, (int) x / 32) == levelMap.getHash("wall")) {
                break;
            }
        }
        for (int i = 1; i < d; i++) {
            if (i != (d - 1)) {
                flameList.add(new Flame(x, y + 32 * i, width, height, Flame.FlameDirection.DOWN, false));
            } else {
                flameList.add(new Flame(x, y + 32 * i, width, height, Flame.FlameDirection.DOWN, true));
            }
        }
    }

    
    public void explode() {
        
    }

}
