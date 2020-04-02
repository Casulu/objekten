import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RobotTest {
    public static void main(String[] args){
        //Variable declarations
        Reader reader = null;
        Maze maze = null;
        RandomRobot robot;
        try{
            //Create new reader and catch error if file wasn't found. Prints error message if error is caught
            reader = new FileReader(args[0]);
            /*Try creating from the created reader from the given file. Catch MazeBuildException if maze-class could
            not build a maze and catch IOException if reading went wrong. Prints error message if error is caught*/
            maze = new Maze(reader);
            //Create robot with created maze
            robot = new RandomRobot(maze);

            //Loop while robot is anywhere but the goal
            while(!robot.hasReachedGoal()){
                //Print position and move robot.
                System.out.println(robot.toString());
                robot.move();
            }
            System.out.println(robot.toString());
            System.out.println("Goal reached!");

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (MazeBuildException e){
            System.out.println("Maze could not be built from file: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Something went wrong while reading file: " + e.getMessage());
        }

    }
}
