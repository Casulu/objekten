import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class RobotTest {
    public static void main(String[] args){
        Reader reader = null;
        Maze maze = null;
        RandomRobot robot;
        try {
            reader = new FileReader(new File(args[0]));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        try{
            maze = new Maze(reader);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        robot = new RandomRobot(maze);

        while(!robot.hasReachedGoal()){
            robot.move();
        }
    }
}
