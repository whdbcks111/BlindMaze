package maze.controller;

import java.awt.event.KeyEvent;

public class GameController implements BaseController {
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;

    // 키가 눌렸을 때
    @Override
    public void keyPressed(String key) {
        switch (key){
            case "UP" :
                this.keyUp = true;
                break;
            case "DOWN" :
                this.keyDown = true;
                break;
            case "LEFT" :
                this.keyLeft = true;
                break;
            case "RIGHT" :
                this.keyRight = true;
                break;
        }
    }

    // 키가 눌렀다 때졌을 때.
    @Override
    public void keyReleased(String key) {
        switch (key){
            case "UP" :
                this.keyUp = false;
                break;
            case "DOWN" :
                this.keyDown = false;
                break;
            case "LEFT" :
                this.keyLeft = false;
                break;
            case "RIGHT" :
                this.keyRight = false;
                break;
        }
    }

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }

    public void reset() {
        this.keyDown = false;
        this.keyLeft = false;
        this.keyRight = false;
        this.keyUp = false;
    }
}
