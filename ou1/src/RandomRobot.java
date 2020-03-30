import java.util.ArrayList;

public class RandomRobot {
    private Position position;
    private Position previousPosition;
    private Maze maze;
    final int HORIZONTAL_DRAW_DISTANCE = 8;
    final int VERTICAL_DRAW_DISTANCE = 4;

    public RandomRobot(Maze maze){
        this.maze = maze;
        position = maze.getStart();
        previousPosition = position;
    }

    public void move(){
        ArrayList<Position> movablePositions = new ArrayList<Position>();
        Position[] compassPositions = new Position[4];
        compassPositions[0] = position.getPosToNorth();
        compassPositions[1] = position.getPosToEast();
        compassPositions[2] = position.getPosToSouth();
        compassPositions[3] = position.getPosToWest();
        int randChoice;

        for(int i = 0; i < 4; i++){
            if(maze.isMovable(compassPositions[i]) && !compassPositions[i].equals(previousPosition)){
                movablePositions.add(compassPositions[i]);
            }
        }

        if(movablePositions.size() > 0){
            randChoice = (int)((Math.random() * (movablePositions.size())));
            previousPosition = position;
            setPosition(movablePositions.get(randChoice));
        } else{
            setPosition(previousPosition);
        }
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position p){
        position = p;
    }

    public boolean hasReachedGoal(){
        return maze.isGoal(position);
    }
}
