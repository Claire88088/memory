/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author cbouto01
 */
public class Card extends Label {
   
    private int number;
    private boolean isUp;
    private Position position;
    private boolean isChosen;
    private ImageView icon;   
    private final ImageView backIcon;
    
    public Card(int number, Position position) {
        this.number = number;
        this.position = position;
        this.isUp = false;
        this.isChosen = false;
        
        this.backIcon = createImageIcon("../icons/icons8-cerclé-m-48.png");
        this.setGraphic(this.backIcon);
        
        this.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");

        switch (number) {
            case 1:   
                this.icon = createImageIcon("../icons/icons8-dinosaure-48.png");
                break;
            case 2:
                this.icon = createImageIcon("../icons/icons8-girafe-48.png");
                break;
            case 3:
                this.icon = createImageIcon("../icons/icons8-grenouille-48.png");
                break;
            case 4:
                this.icon = createImageIcon("../icons/icons8-hippocampe-48.png");
                break;
            case 5:
                this.icon = createImageIcon("../icons/icons8-kangourou-48.png");
                break;
            case 6:
                this.icon = createImageIcon("../icons/icons8-lapin-48.png");
                break;
            case 7:
                this.icon = createImageIcon("../icons/icons8-loup-48.png");
                break;
            case 8:
                this.icon = createImageIcon("../icons/icons8-mouton-48.png");
                break;
            case 9:
                this.icon = createImageIcon("../icons/icons8-ours-48.png");
                break;
            case 10:
                this.icon = createImageIcon("../icons/icons8-poisson-48.png");
                break; 
            default:
                this.icon = createImageIcon("../icons/icons8-hippocampe-48.png");
                break;
        }   
        
    }

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
            this.setGraphic(this.backIcon);
        } else {
            this.isUp = true;
            this.setGraphic(this.icon);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void switchIsChosen() {
        if (this.isChosen) {
            this.isChosen = false;
            this.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
        } else {
            this.isChosen = true;
            this.setStyle("-fx-border-width:2; -fx-border-color:red; -fx-padding:5");
        }
    }
    
    private ImageView createImageIcon(String path) {   
        Image image = new Image(getClass().getResource(path).toExternalForm());
        return new ImageView(image);  
    }

}
