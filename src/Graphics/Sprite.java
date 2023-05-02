package Graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Sprite {
    public BufferedImage[][] image;
    String path; // duong dan toi anh
    private SpriteSheet sheet;
    int width;
    int height;
    int count;

    public Sprite(String path, int count) {
        sheet = new SpriteSheet(path);
        width = 32;
        height = 32;
        load();
        this.count = count;

    }

    public Sprite(String path, int width, int height, int count) {
        sheet = new SpriteSheet(path,width,height);
        this.count = count;
        this.width = width;
        this.height = height;
        load();
        
    }

    // ham load subimg vao mot ma tran voi kich thuoc la row / 32 va cols / 32;
    private void load() {
        try {
            int row = sheet.getImg().getHeight() / height;
            int cols = sheet.getImg().getWidth() / width;
            image = new BufferedImage[row][cols];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < cols; j++) {
                    image[i][j] = sheet.getSprite(i, j);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public BufferedImage getSprite(int x, int y) {
        return image[x][y];
    }

}
