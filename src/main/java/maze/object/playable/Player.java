package maze.object.playable;

import maze.collider.AABBCollider;
import maze.controller.GameController;
import maze.main.Main;
import maze.components.Movable;
import maze.object.GameObject;
import maze.object.block.Goal;
import maze.object.block.Wall;
import maze.object.item.Item;
import maze.utils.ImageManager;
import maze.view.GameView;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements Movable {

    private static final double SCORE_INTERVAL = 0.5;
    private static final double SCORE_DECREASE = 350;
    private static final double DEFAULT_MOVE_SPEED = 200;
    private static final double ADDITIONAL_SCORE_DECREASE = 100;
    private double scoreTimer = SCORE_INTERVAL;
    private double speedUpParam = 1;
    private double remainCarrotTime = 0, remainSpeedUpTime = 0, remainSpaceTime = 0;
    private int score = 100000;
    private double moveSpeed = DEFAULT_MOVE_SPEED;
    private final GameObject light;
    private final GameController controller;
    private final GameView gameView;

    public Player(GameView panel, GameController controller, int x, int y) {
        super(panel, x, y, ImageManager.getImage("player"), 2);
        this.collider = new AABBCollider(this, ImageManager.getImage("player").getWidth() * 0.9,
                ImageManager.getImage("player").getHeight() * 0.9, 0, 0);
        this.controller = controller;
        this.light = new GameObject(panel, x, y, ImageManager.getImage("player"), 4);
        this.gameView = panel;
    }

    @Override
    public void move() {

        if(this.controller.isKeyUp()){
            if(this.getY() > this.getImage().getHeight(panel) / 2.0) {
                double originY = this.getY();
                this.setY(originY - moveSpeed * Main.deltaTime);
                if(checkCollisionWithWall()) this.setY(originY);
            }
        }

        if(this.controller.isKeyDown()){
            double originY = this.getY();
            if(this.getY() < panel.getHeight() - this.getImage().getHeight(panel) / 2.0)
                this.setY(this.getY() + moveSpeed * Main.deltaTime);
            if(checkCollisionWithWall()) this.setY(originY);
        }

        if(this.controller.isKeyLeft()){
            double originX = this.getX();
            if(this.getX() > this.getImage().getWidth(panel) / 2.0)
                this.setX(this.getX() - moveSpeed * Main.deltaTime);
            if(checkCollisionWithWall()) this.setX(originX);
        }

        if(this.controller.isKeyRight()){
            double originX = this.getX();
            if(this.getX() < panel.getWidth() - this.getImage().getWidth(panel) / 2.0)
                this.setX(this.getX() + moveSpeed * Main.deltaTime);
            if(checkCollisionWithWall()) this.setX(originX);
        }

        light.setX(this.getX());
        light.setY(this.getY());
    }

    public boolean checkCollisionWithWall() {
        if(this.collider != null)
            for (GameObject gameObject : GameObject.getGameObjects()) {
                if(gameObject instanceof Wall && gameObject.isCollidable()
                        && this.collider.checkCollision(gameObject.getCollider())) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public void update() {
        super.update();
        move();

        scoreTimer -= Main.deltaTime;
        if(scoreTimer <= 0) {
            scoreTimer += SCORE_INTERVAL;
            score -= SCORE_DECREASE + (int)(Math.random() * (ADDITIONAL_SCORE_DECREASE + 1));
        }

        if(score <= 0) {
            this.gameView.gameOver();
        }

        if(remainCarrotTime > 0) {
            remainCarrotTime -= Main.deltaTime;
            if(light.getImage() != ImageManager.getImage("carrotLight")) light.setImage(ImageManager.getImage("carrotLight"));
            if(remainCarrotTime <= 0) {
                light.setImage(ImageManager.getImage("light"));
            }
        }

        if(remainSpaceTime > 0) {
            remainSpaceTime -= Main.deltaTime;
            if(light.getImage() != ImageManager.getImage("spaceLight")) light.setImage(ImageManager.getImage("spaceLight"));
            if(remainSpaceTime <= 0) {
                light.setImage(ImageManager.getImage("light"));
            }
        }

        if(remainSpeedUpTime > 0) {
            remainSpeedUpTime -= Main.deltaTime;
            moveSpeed = DEFAULT_MOVE_SPEED * speedUpParam;
            if(getImage() != ImageManager.getImage("speedPlayer")) setImage(ImageManager.getImage("speedPlayer"));
            if(remainSpeedUpTime <= 0) {
                moveSpeed = DEFAULT_MOVE_SPEED;
                setImage(ImageManager.getImage("player"));
            }
        }

        if(this.collider != null)
            for (GameObject gameObject : GameObject.getGameObjects()) {
                if(gameObject instanceof Goal && gameObject.isCollidable()
                        && this.collider.checkCollision(gameObject.getCollider())) {
                    this.gameView.gameClear(score);
                }
                if(gameObject instanceof Item && gameObject.isCollidable()
                        && this.collider.checkCollision(gameObject.getCollider())) {
                    ((Item) gameObject).onTrigger(this);
                }
            }
    }

    public double getRemainSpaceTime() {
        return remainSpaceTime;
    }

    public void setRemainSpaceTime(double remainSpaceTime) {
        this.remainSpaceTime = remainSpaceTime;
    }

    public double getRemainSpeedUpTime() {
        return remainSpeedUpTime;
    }

    public void setRemainSpeedUpTime(double up, double remainSpeedUpTime) {
        this.remainSpeedUpTime = remainSpeedUpTime;
        this.speedUpParam = up;
    }

    public double getRemainCarrotTime() {
        return remainCarrotTime;
    }

    public void setRemainCarrotTime(double remainCarrotTime) {
        this.remainCarrotTime = remainCarrotTime;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
