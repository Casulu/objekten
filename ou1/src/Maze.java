import java.io.IOException;
import java.util.ArrayList;
import java.io.Reader;
import java.util.Scanner;


/**
 * The Maze class contains a maze represented by an ArrayList of strings read from a given Reader object specified on
 * creation. The format for the maze is: '*' for walls, ' ' for movable space, 'S' for the starting position, and
 * 'G' for the goal.
 */
public class Maze {
    private ArrayList<String> mazeData;
    private Position startPos;
    private int numColumns = 0;

    /***
     * Builds a maze from represented by a list of strings from the given reader using a scanner.
     * Stores away the start
     * @param reader - Reader to read file with
     * @throws MazeBuildException - If maze fails to build
     */
    public Maze(Reader reader)
            throws MazeBuildException, IOException{
        //Initialize list for maze data
        mazeData = new ArrayList<String>();
        //Variabel declaration
        Scanner test = new Scanner(reader);
        String currLine;

        boolean startFound = false;
        boolean endFound = false;

        //Loop while reader has another line
        while(test.hasNextLine()){
            //If scanner has encountered a reading error, throw it
            if(test.ioException() != null){
                throw test.ioException();
            }
            //Read next line in file
            currLine = test.nextLine();
            //Add line to list
            mazeData.add(currLine);
            //If current line is longer than current highest length, set numColumns to current length
            if(numColumns < currLine.length()){
                numColumns = currLine.length();
            }

            //Loop through all the characters in the line and check for starts, goals and invalid characters
            for (int i = 0; i < currLine.length(); i++){
                char currChar = currLine.charAt(i);
                if (currChar == 'S'){
                    startPos = new Position(i, mazeData.size()-1); // mazeData.size() - 1 is current row
                    if(!startFound){ //If an S was found, set starting position to found coordinates and mark that a start was found
                        startFound = true;
                    }else{
                        throw new MazeBuildException("File contained multiple starts");
                    }
                } else if(currChar == 'G'){ //If an G was found, mark that a goal was found
                    endFound = true;
                } else if(currChar != '*' && currChar != ' '){ //If character is invalid, throw exception
                    throw new MazeBuildException(String.format("File contained invalid character '%c'" +
                                                               " (should only contain characters 'S', 'G', 'space', or '*')", currChar));
                }
            }
        }
        if(!startFound){
            throw new MazeBuildException("File contained no start");
        }
        if(!endFound){
            throw new MazeBuildException("File contained no goal");
        }

    }

    public boolean isMovable(Position p){
        if(p.getX() >= 0 && p.getY() >= 0){ //Check if position is not outside left side or over the top of the maze
            if (p.getY() < getNumRows()){ //Check if position is below last row
                String relRow = mazeData.get(p.getY());
                if(p.getX() < relRow.length()){ //Check if position is outside of the current row
                    return relRow.charAt(p.getX()) != '*';
                }
            }
        }
        return false;
    }

    public boolean isGoal(Position p){
        return mazeData.get(p.getY()).charAt(p.getX()) == 'G';
    }

    public Position getStart(){
        return startPos;
    }

    public int getNumColumns(){
        return numColumns;
    }

    public int getNumRows(){
        return mazeData.size();
    }
}
