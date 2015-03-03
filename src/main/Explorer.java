package main;

import java.util.ArrayList;
import java.util.List;

public class Explorer {


    private final Maze maze;
    private Direction direction;
    private Coords myPosition;
    private List<String> myMoves;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.direction = Direction.NORTH;
        myPosition = maze.startCoords();
        myMoves= new ArrayList();
    }

    public Coords myLocation() {
        return myPosition;
    }

    public Direction faces() {
        return direction;
    }

    public void moveForward() {
        Coords newPosition = myPosition.add(direction.howToMoveThere);
        if (!canMoveForward(newPosition)) {
            throw new IllegalStateException("are you a ghost? no? well you cant walk through walls then");
        }
        myMoves.add("move forward");
        myPosition = newPosition;
    }

    public String nextMoves() {
        Coords newPosition = myPosition.add(direction.howToMoveThere);
        return "turn left, turn right" + (canMoveForward(newPosition) ? ", move forward" : "");

    }

    private boolean canMoveForward(Coords newPosition) {
        return !"X".equals(maze.whatsAt(newPosition.x, newPosition.y));
    }

    public void turnLeft() {
        myMoves.add("turn left");
        direction = direction.turnLeft();
    }

    public void turnRight() {
        myMoves.add("turn right");
        direction = direction.turnRight();
    }

    public String whatDoISee() {
        Coords whatsInFrontOfMe = myPosition.add(direction.howToMoveThere);
        return maze.whatsAt(whatsInFrontOfMe.x, whatsInFrontOfMe.y);
    }

    public String myMoves() {
        return String.join(", ",myMoves);
    }
}
