/**
 * Exception thrown when a maze cannot be built from file.
 */
public class MazeBuildException extends Exception {
    public MazeBuildException(String msg){
        super(msg);
    }
}
