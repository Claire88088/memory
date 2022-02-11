/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

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
    
    public static void main(String[] args)
    {
        Position p1 = new Position(5, 6);
        Position p2 = new Position(4, 7);
        
        System.out.println("Position p1 : row : "+ p1.getRow() + " et col : " + p1.getColumn());
        System.out.println("Position p2 : row : "+ p2.getRow() + " et col : " + p2.getColumn());
        System.out.println();
        
        System.out.println("est-ce que p1 est à gauche de p2 : " + p1.isLeftTo(p2));
        System.out.println("est-ce que p2 est à gauche de p1 : " + p2.isLeftTo(p1));
        System.out.println();
        
        System.out.println("est-ce que p1 est à droite de p2 : " + p1.isRightTo(p2));
        System.out.println("est-ce que p2 est à droite de p1 : " + p2.isRightTo(p1));
        System.out.println();
        
        System.out.println("est-ce que p1 est au-dessus de p2 : " + p1.isOver(p2));
        System.out.println("est-ce que p2 est au-dessus de p1 : " + p2.isOver(p1));
        System.out.println();
        
        System.out.println("est-ce que p1 est en-dessous de p2 : " + p1.isUnder(p2));
        System.out.println("est-ce que p2 est en-dessous de p1 : " + p2.isUnder(p1));
        System.out.println();
        
        System.out.println("p1 est sur la même position que p2 : " + p1.isEqualTo(p2));
        System.out.println();
        
        System.out.println("Position de p1 en chaine : " + p1.toString());
    }*/
    
}
