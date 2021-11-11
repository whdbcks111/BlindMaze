package maze.components;

import maze.object.block.Wall;
import maze.collider.AABBCollider;
import maze.object.block.Goal;
import maze.object.block.Start;
import maze.object.item.*;
import maze.utils.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MazeBuilder {
    private final int horizontal, vertical;

    private final MazeBlock[][] mazeMap;

    public MazeBuilder(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        mazeMap = new MazeBlock[vertical][horizontal];
        for(int i = 0; i < mazeMap.length; i++)
            for(int j = 0; j < mazeMap[i].length; j++)
                mazeMap[i][j] = new MazeBlock(MazeBlock.Type.WALL);
    }

    public void buildMaze(JPanel panel, int distance, Point start) {
        MazeBlock goal = mazeMap[start.getY()][start.getX()];
        int maxLength = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()) {
            Point pos = stack.peek();
            int x = pos.getX(), y = pos.getY();
            LinkedList<Point> movables = new LinkedList<>(Arrays.asList(new Point(x + 2, y), new Point(x - 2, y),
                    new Point(x, y + 2), new Point(x, y - 2)));
            List<Point> excludes = new LinkedList<>();

            if(Math.random() < 0.1 && mazeMap[y][x].type == MazeBlock.Type.PATH && stack.size() > 15)
                mazeMap[y][x].type = MazeBlock.Type.ITEM;

            for (Point p : movables) {
                if((p.getY() < 0 || p.getY() >= mazeMap.length || p.getX() < 0 || p.getX() >= mazeMap[0].length)
                        || mazeMap[p.getY()][p.getX()].visited)
                    excludes.add(p);
            }
            for (Point exclude : excludes) {
                movables.remove(exclude);
            }

            if(movables.size() == 0) {
                if(maxLength < stack.size()) {
                    maxLength = stack.size();
                    goal = mazeMap[y][x];
                }
                stack.pop();
                continue;
            }
            Point go = movables.get((int)(Math.random() * movables.size()));

            if(Math.random() < 0.05 && stack.size() > 10) {
                for (int i = 0; i < 5; i++) {
                    stack.pop();
                }
                continue;
            }

            mazeMap[y][x].type = MazeBlock.Type.PATH;
            mazeMap[(go.getY() + y) / 2][(go.getX() + x) / 2].type = MazeBlock.Type.PATH;
            mazeMap[go.getY()][go.getX()].type = MazeBlock.Type.PATH;
            mazeMap[y][x].visited = true;
            mazeMap[(go.getY() + y) / 2][(go.getX() + x) / 2].visited = true;
            mazeMap[go.getY()][go.getX()].visited = true;

            stack.push(go);
        }

        goal.type = MazeBlock.Type.GOAL;
        mazeMap[start.getY()][start.getX()].type = MazeBlock.Type.START;
        
        for (int i = 0; i < mazeMap.length; i++) {
            for (int j = 0; j < mazeMap[i].length; j++) {
                int x = distance * j + distance / 2, y = distance * i + distance / 2;
                switch (mazeMap[i][j].type) {
                    case WALL:
                        new Wall(panel, x, y);
                        break;
                    case GOAL:
                        new Goal(panel, x, y);
                        break;
                    case START:
                        new Start(panel, x, y);
                        break;
                    case ITEM:
                        switch ((int)(Math.random() * 4)) {
                            case 0: new Carrot(panel, x, y); break;
                            case 1: new SpeedUp(panel, x, y); break;
                            case 2: new Apple(panel, x, y); break;
                            case 3: new SpaceEye(panel, x, y); break;
                        }
                }
            }
        }
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public MazeBlock[][] getMazeMap() {
        return mazeMap;
    }
}
