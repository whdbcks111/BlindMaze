package maze.components;

public class MazeBlock {

    Type type;
    boolean visited = false;

    public MazeBlock(Type type) {
        this.type = type;
    }

    public enum Type {
        PATH, WALL, GOAL, START, ITEM;
    }
}
