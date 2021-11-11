package maze.object;

import maze.collider.Collider;
import maze.controller.GameController;
import maze.components.Updatable;
import maze.object.display.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class GameObject extends GameController implements Updatable {

    private static final List<GameObject> gameObjects = new LinkedList<>();

    protected double x, y;
    protected final int layer;
    protected boolean isVisible = true;

    protected Text text;

    protected BufferedImage img;
    protected final JPanel panel;
    protected Collider collider;

    public GameObject(JPanel panel, double x, double y, BufferedImage img) {
        this(panel, x, y, img, 0);
    }

    public GameObject(JPanel oanel, double x, double y, BufferedImage img, int layer) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.layer = layer;
        this.panel = oanel;
        int idx = 0;
        for (GameObject gameObject : gameObjects) {
            if(gameObject.layer >= this.layer) {
                break;
            }
            idx++;
        }
        gameObjects.add(idx, this);
    }

    public void remove() {
        GameObject.removeGameObject(this);
    }

    @Override
    public void update() { }

    public static void removeGameObject(GameObject target) {
        gameObjects.remove(target);
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Text getText() {
        return text;
    }

    public int getLayer() {
        return layer;
    }

    public Image getImage() {
        return img;
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    
    public boolean isCollidable() {
        return isVisible && this.collider != null;
    }
}
