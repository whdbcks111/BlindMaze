package maze.collider;

import maze.object.GameObject;

public class AABBCollider implements Collider {
    private final GameObject owner;
    private double width, height, offsetX, offsetY;

    public AABBCollider(GameObject owner, double width, double height, int offsetX, int offsetY) {
        this.owner = owner;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public boolean checkCollision(Collider other) {
        if(other instanceof AABBCollider) {
            return !(this.getEndX() < ((AABBCollider) other).getX()
                    || ((AABBCollider) other).getEndX() < this.getX()
                    || this.getEndY() < ((AABBCollider) other).getY()
                    || ((AABBCollider) other).getEndY() < this.getY());
        }
        return false;
    }

    public double getEndX() {
        return this.getX() + this.getWidth();
    }

    public double getEndY() {
        return this.getY() + this.getHeight();
    }

    public double getX() {
        return this.owner.getX() - this.getWidth() / 2 + this.offsetX;
    }

    public double getY() {
        return this.owner.getY() - this.getHeight() / 2 + this.offsetY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
