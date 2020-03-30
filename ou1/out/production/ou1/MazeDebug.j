import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.io.Reader;

public class Maze {
    public ArrayList<String> mazeData;
    private Position startPos;
    private int numColumns;

    public Maze(Reader reader)
    throws Exception{
        mazeData = new ArrayList<String>();

        char data;
        boolean startFound = false;
        boolean endFound = false;
        int i = 0;
        int j = 0;
        numColumns = 0;

        data = (char)reader.read();
        while((int)data != '\uffff'){
            String buffer = "";
            j = 0;
            while(data != '\n' && data != '\r' && data != '\uffff'){
                switch (data){
                    case 'S':
                        if(!startFound){
                            startPos = new Position(j, i);
                            startFound = true;
                        } else{
                            throw new Exception("File contained multiple starts");
                        }
                        break;
                    case 'G':
                        endFound = true;
                        break;
                    default:
                        if(data != '*' && data != ' '){
                            throw new Exception("File contained non-valid character '" + data + "'");
                        }
                        break;
                }
                buffer = buffer + data;
                j++;
                data = (char)reader.read();
            }
            if(numColumns < j){
                numColumns = j;
            }
            mazeData.add(buffer);
            i++;
            for(int k = 0; k < System.lineSeparator().length() - 1; k++){
                reader.read();
            }
            data = (char)reader.read();
        }
        if(!startFound){
            throw new Exception("File contained no start");
        }
        if(!endFound){
            throw new Exception("File contained no goal");
        }

    }

    public boolean isMovable(Position p){
        if(p.getX() >= 0 && p.getY() >= 0){
            if(p.getX() < numColumns && p.getY() < getNumRows()){
                String relRow = mazeData.get(p.getY());
                return p.getX() < relRow.length() && relRow.charAt(p.getX()) != '*';
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
