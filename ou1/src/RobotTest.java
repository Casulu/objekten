import java.io.*;

public class RobotTest {
    public static void main(String[] args){
        //Variable declarations
        Reader reader = null;
        Maze maze = null;
        RandomRobot robot;
        //Create new reader and catch error if file wasn't found. Prints error and exits if error is caught
        try {
            reader = new FileReader(new File(args[0]));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        /*Try creating from the created reader from the given file. Catch MazeBuildException if maze-class could
          not build a maze and catch IOException if reading went wrong. Prints error and exits if error is caught*/
        try{
            maze = new Maze(reader);
        } catch (MazeBuildException e){
            System.out.println("Maze could not be built from file");
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e){
            System.out.println("Something went wrong while reading file");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        //Create robot with created maze
        robot = new RandomRobot(maze);

        //Loop while robot is anywhere but the goal
        while(!robot.hasReachedGoal()){
            //Print position and move robot.
            System.out.println(robot.toString());
            robot.move();
        }
    }
}
