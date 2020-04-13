import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RobotTest {
    public static void main(String[] args){
        //Variable declarations
        Reader reader;
        Maze maze;
        RandomRobot robot;
        try{
            //Create new reader and catch error if file wasn't found. Prints error message if error is caught
            reader = new FileReader(args[0]);
            /*Try creating from the created reader from the given file. Catch MazeBuildException if maze-class could
            not build a maze and catch IOException if reading went wrong. Prints error message if error is caught*/
            maze = new Maze(reader);
            //Create robot with created maze
            robot = new RandomRobot(maze);

            //Print starting position
            System.out.println(robot.getPosition().toString());
            //Loop while robot is anywhere but the goal
            int steps = 0;
            while(!robot.hasReachedGoal()){
                //Move robot and print position
                robot.move();
                steps++; //Count steps taken
                System.out.println(robot.getPosition().toString()); //Print position

            }
            System.out.println(String.format("Goal reached in %d steps!", steps));

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (MazeBuildException e){
            System.out.println("Maze could not be built from file: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Something went wrong while reading file: " + e.getMessage());
        }

    }
}
