package Enitity;

import Config.GameConfig;

public class BoxCollider {
    private double x;
    private double y;
    private final double width;
    private final double height;

    public BoxCollider(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = GameConfig.SIZE_BLOCK;
        this.height = GameConfig.SIZE_BLOCK;
    }

    public BoxCollider(double x, double y, double width, double height) {
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

    public void setLocation(double x, double y) {
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