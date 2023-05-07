package Bomb;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Boomber.Bomber;
import Config.GameConfig;
import Enitity.Entity;
import Enitity.EntityManager;
import Graphics.Animation;
import Map.LevelMap;
import Time.Timers;

public class Bomb extends Entity {
    public static boolean initialized = false;
    public static final int DEFAULT_FLAME_LENGTH = 1;
    public Animation img = new Animation("/anhgame.png", 3, 2, 1000_000_000, 0,2);
    
    private final List<Flame> flameList = new ArrayList<>();

    public boolean canPass = true;
    private static int flameLength = DEFAULT_FLAME_LENGTH;
    /**
     * @return the flameLength
     */
    public static int getFlameLength() {
        return flameLength;
    }

    /**
     * @param flameLength the flameLength to set
     */
    public static void setFlameLength(int flameLength) {
        Bomb.flameLength = flameLength;
    }

    private float timeBeforeExplode = 3000_000_000.f;
    private int flameTime = 500_000_000;
    private double time = 0;
    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    private boolean done = false;
    private boolean isAddFlame = false;

    public Bomb(int x, int y) {
        this.x = ((x +26)/ GameConfig.SIZE_BLOCK) ;
        this.y = ((y + 35)/ GameConfig.SIZE_BLOCK )  ;
//        if((x+26)%GameConfig.SIZE_BLOCK == 0)x--;
        if((y+35)%GameConfig.SIZE_BLOCK == 0)this.y--;
      if((x+26)%GameConfig.SIZE_BLOCK==0)this.x--;
//        System.out.println((x+26)%GameConfig.SIZE_BLOCK);
//        System.out.println((y+26)%GameConfig.SIZE_BLOCK);
        this.x *= GameConfig.SIZE_BLOCK;
        this.y *= GameConfig.SIZE_BLOCK;
        
        
        img.setLoop(true);
    }

    public void setTimeExplodeBomb(double x, double y) {
        List<Bomb> bombList = EntityManager.getInstance().bombs;
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).getX() == x && bombList.get(i).getY() == y) {
                bombList.get(i).setTimeBeforeExplode(bombList.get(i).getTime() + 5_0000000);
                bombList.get(i).setTime(0);
            }
        }
    }

    public void setTimeBeforeExplode(int i) {
        timeBeforeExplode = i;
    }

    private int getTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    private void explosion() {

        LevelMap levelMap = LevelMap.getInstance();

        flameList.add(new Flame(x, y, Flame.FlameDirection.CENTER, false));

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
                flameList.add(new Flame(x - 32 * i, y, Flame.FlameDirection.LEFT, false));
            } else {
                flameList.add(new Flame(x - 32 * i, y, Flame.FlameDirection.LEFT, true));
            }
        }

        // check right
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
                flameList.add(new Flame(x + 32 * i, y, Flame.FlameDirection.RIGHT, false));
            } else {
                flameList.add(new Flame(x + 32 * i, y, Flame.FlameDirection.RIGHT, true));
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
            if (i != (u - 1)) {
                flameList.add(new Flame(x, y - 32 * i, Flame.FlameDirection.UP, false));
            } else {
                flameList.add(new Flame(x, y - 32 * i, Flame.FlameDirection.UP, true));
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
                flameList.add(new Flame(x, y + 32 * i, Flame.FlameDirection.DOWN, false));
            } else {
                flameList.add(new Flame(x, y + 32 * i, Flame.FlameDirection.DOWN, true));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (time < timeBeforeExplode) {
            img.render(g, x, y);
        } else if (time <= (float)timeBeforeExplode + (float)flameTime) {
            flameList.forEach(flame -> flame.render(g));
            img.setLoop(false);
        } else {
            
            done = true;
        }
    }

    @Override
    public void update() {
        time += Timers.getInstance().getDeltaTime();
        EntityManager entityManager = EntityManager.getInstance();
        if (!isAddFlame && (float)time >= (float)timeBeforeExplode  && (float)time <=(float) timeBeforeExplode + (float)flameTime) {
            explosion();
            isAddFlame = true;
        }
        if( Math.abs(entityManager.bomber.getX() - this.x) > 32 || Math.abs(entityManager.bomber.getY()-this.y) > 32)
            canPass = false;
      
        img.update();
       //if(done)LevelMap.getInstance().setHashAt(y/32,x/32,"grass");
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }



}
