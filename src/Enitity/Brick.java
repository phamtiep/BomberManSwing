package Enitity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graphics.Sprite;
import Map.LevelMap;

public class Brick extends Entity {

    private static boolean initialized = false;
    private static BufferedImage brick;
    private static BufferedImage[] brickExplodes;
    private boolean destroyed = false;
    private int aniTick, aniIndex, aniSpeed = 15;
    private boolean isDone = false;

    public Brick(int x, int y) {
        super(x, y);
        brickExplodes = new BufferedImage[4];
        initBrick();
        loadAnimationExplodes();
    }

    /**
     * @return the destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @param destroyed the destroyed to set
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    private void loadAnimationExplodes() {
        for (int i = 0; i < 4; i++) {
            brickExplodes[i] = sprite.getSprite(i, 3);
        }
    }

    private void initBrick() {
        sprite = new Sprite("/anhgame.png", 1);
        brick = sprite.getSprite(0, 3);
    }

    public void updateAnimation() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 4) {
                aniIndex = 0;
                isDone = true;
            }
        }

    }

    public void ExploderBrick(Graphics g) {
        updateAnimation();
        try
        {g.drawImage(brickExplodes[aniIndex], x, y, null);}
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics g) {
        if (!isDone) {
            if (!isDestroyed())
                g.drawImage(brick, x, y, null);

            else {
                ExploderBrick(g);

            }
        }
        else {
            LevelMap.getInstance().setHashAt(y/32, x/32,"grass");
        }
    }

}
