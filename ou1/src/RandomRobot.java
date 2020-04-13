import java.util.ArrayList;


/**
 * A robot that moves in a given maze in a random fashion when prompted
 */
public class RandomRobot implements Robot {
    //Internal variables
    private Position position;
    private Position previousPosition;
    private Maze maze;

    public RandomRobot(Maze maze){
        this.maze = maze;
        //Start the robot on the mazes start position
        setPosition(maze.getStart());
        //Initialize the robots previous position as the start as well
        previousPosition = position;
    }

    /**
     * Moves in a random movable direction that isn't the previously visited position and store the previous position
     */
    public void move(){
        //List for storing move options
        ArrayList<Position> movablePositions = new ArrayList<>();
        //Array for storing all positions around the position currently moving from
        Position[] compassPositions = new Position[4];
        //Store all positions around current position
        compassPositions[0] = position.getPosToNorth();
        compassPositions[1] = position.getPosToEast();
        compassPositions[2] = position.getPosToSouth();
        compassPositions[3] = position.getPosToWest();

        int randChoice;

        for(int i = 0; i < 4; i++){
            //Add position to list of options if option isn't previous position and if the option is movable
            if(maze.isMovable(compassPositions[i]) && !compassPositions[i].equals(previousPosition)){
                movablePositions.add(compassPositions[i]);
            }
        }

        //If robot found any movable positions
        if(movablePositions.size() > 0){
            //Randomize an index in the range of the indices of the movable options list
            randChoice = (int)((Math.random() * (movablePositions.size())));
            previousPosition = position;
            //Set new position to random choice of movable position
             setPosition(movablePositions.get(randChoice));
        } else{
            Position temp = position;
            setPosition(previousPosition);
            previousPosition = temp;
        }
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position p){
        if(p != null){
            position = p;
        } else{
            throw new NullPointerException();
        }
    }

    public boolean hasReachedGoal(){
        return maze.isGoal(position);
    }
}
