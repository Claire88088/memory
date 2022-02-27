/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

/**
 *
 * @author cbouto01
 */
public class Position {
    private int row;
    private int col;
    
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() 
    {
        return this.row;
    }
    
    public void setRow(int row)
    {
        this.row = row;
    }
    
    public int getColumn() 
    {
        return this.col;
    }
    
    public void setColumn(int col)
    {
        this.col = col;
    }
    /*
    public boolean isLeftTo(Position p)
    {
        // renvoie vrai si l'instance courante est située à gauche de la position p
        return (this.col < p.getColumn());
    }
    
    public boolean isRightTo(Position p)
    {
        return (this.col > p.getColumn());
    }
    
    public boolean isOver(Position p)
    {
        return (this.row < p.getRow());
    }
    
    public boolean isUnder(Position p)
    {
        return (this.row > p.getRow()); 
    }
    
    public boolean isEqualTo(Position p)
    {
        return (this.row == p.getRow() && this.col == p.getColumn()); 
    }
    */
    public String toString() 
    {  
        return "(" + this.row + "," + this.col + ")";
    }
    /*
    @Override 
    public boolean equals(Object p){
        if (p == null) 
            return false ; 
        if (p.getClass() != Position.class)
            return false ; 
        return (this.row == ((Position) p).row && this.col == ((Position) p).col);
    }
    */
 
}
