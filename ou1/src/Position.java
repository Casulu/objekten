public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

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

    public boolean equals(Object o){
        if(o.getClass() == this.getClass()){
            return this.x == ((Position) o).getX() && this.y == ((Position) o).getY();
        }
        return false;
    }

    public int hashCode(){
        return (this.x + this.y) % 97;
    }
}
