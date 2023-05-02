package Enitity;

import Config.GameConfig;

public class BoxCollider {
    private int x;
    private int y;
    private  int width = 32;
    private int height = 32;

    public BoxCollider(int x, int y ) {
        this.x = x;
        this.y = y;
    }
    public BoxCollider(int x, int y, int width, int height ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCollidedWith(BoxCollider other) {
        if (other == null) {
            return false;
        }

        double left_1 = this.x;
        double right_1 = this.x + this.width;
        double top_1 = this.y;
        double bottom_1 = this.y + this.height;

        double left_2 = other.getX();
        double right_2 = other.getX() + other.getWidth();
        double top_2 = other.getY();
        double bottom_2 = other.getY() + other.getHeight();

        if (bottom_1 <= top_2) {
            return false;
        }
        if (top_1 >= bottom_2) {
            return false;
        }
        if (right_1 <= left_2) {
            return false;
        }
        if (left_1 >= right_2) {
            return false;
        }
        return true;
    }
}