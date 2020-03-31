/**
 * Class that stores to int as coordinates. Contains methods for given adjacent positions (non-diagonally)
 */
public class Position {
    //Internal variables
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Methods below for getting coordinates and adjacent positions
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPosToSouth(){
        return new Position(this.x, this.y+1);
    }

    public Position getPosToNorth(){
        return new Position(this.x, this.y-1);
    }

    public Position getPosToWest(){
        return new Position(this.x-1, this.y);
    }

    public Position getPosToEast(){
        return new Position(this.x+1, this.y);
    }

    @Override
    /**
     * Equals if both ints in the position are the same
     */
    public boolean equals(Object o){
        if(o.getClass() == this.getClass()){
            return this.x == ((Position) o).getX() && this.y == ((Position) o).getY();
        }
        return false;
    }

    @Override
    /**
     * Returns basic hash code
     */
    public int hashCode(){
        return (this.x % this.y);
    }

    @Override
    /**
     * Prints both ints separated by comma
     */
    public String toString(){
        return String.format("%d, %d", x, y);
    }
}
