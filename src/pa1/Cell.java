package pa1;


/**
 * An immutable class that represents a cell in the grid map
 */
// TODO
class Cell{
    private int x;
    private int y;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean equals(Object o){
        if(o instanceof Cell){
            if(this.x == ((Cell) o).getX() && this.y == ((Cell)o).getY()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}

