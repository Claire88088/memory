/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cbouto01
 */
public class Card extends Rectangle {
    
    public static Color[] DEFAULT_COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.BLUEVIOLET};
    public static Color DEFAULT_FILL_COLOR = Color.BURLYWOOD;
    
    //--------- TODO : modifier l'attribut pour qu'il soit lié à une aute carte-------------
    //private IntegerProperty number = new SimpleIntegerProperty();
    private int number;
    private Color color;
    private boolean isUp; // face visible ?
    private boolean isFind;
    private Position position;
    
    public Card(int number, Position position, int width, int height) {
        this.number = number;
        this.position = position;
        this.isUp = false;
        this.isFind = false;

        this.setWidth(width);
        this.setHeight(height);
        this.setFill(DEFAULT_FILL_COLOR);

        switch (number) {
            case 1:   
                this.color = DEFAULT_COLORS[0];
                break;
            case 2:
                this.color = DEFAULT_COLORS[1];
                break;
            case 3:
                this.color = DEFAULT_COLORS[2];
                break;
            default:
                this.color = DEFAULT_COLORS[3];
                break;
        }
      
    }
    
    
    public Card(int number, Position position) {
       this(number, position, 50, 100);
    }
    
    public Card() {
        this(1, null);
    }
 
    /*
    public IntegerProperty getNumber() {
        return number;
    }

    public void setNumber(IntegerProperty number) {
        this.number = number;
    }
    */
    //------- TODO : retourner une carte = afficher sa couleur dans un premier temps puis afficher son icone------------

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isUp() {
        return this.isUp;
    }
    
    public void switchIsUp() {
        if (this.isUp) {
            this.isUp = false;
            this.setFill(DEFAULT_FILL_COLOR);
        } else {
            this.isUp = true;
            this.setFill(this.color);
        }
    }
    
    // ---------TO DO : ne fonctionne pas alors que switchIsUp fonctionne !!!!---------
    public void setIsUp(Boolean bool) {
        if (bool = true) {
            this.isUp = true;
            this.setFill(this.color);
        } else {
            this.isUp = false;
            this.setFill(DEFAULT_FILL_COLOR);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void removeFromGame() {
        this.isFind = true;
    }
    
    public boolean isFind() {
        return isFind;
    }

    public void setIsFind(boolean isFind) {
        this.isFind = isFind;
    }  
    
}
