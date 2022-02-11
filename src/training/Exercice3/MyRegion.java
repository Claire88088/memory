/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.Exercice3;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author cbouto01
 */
public class MyRegion extends Region {
    
    public MyRegion(String shape, Color color) {
        
        // taille préférée 100*100
        this.setPrefSize(100, 100);
        
        BackgroundFill []tb = new BackgroundFill[1] ;
        tb[0] = new BackgroundFill(color,null,null);
        this.setBackground(new Background(tb));
        
        if (shape == "carre") {
            Rectangle carre = new Rectangle();
            carre.setWidth(100);
            carre.setHeight(100);
            this.setShape(carre);
        }
        
        if (shape == "disque") {
            Circle disque = new Circle();
            disque.setRadius(50);
            this.setShape(disque);
        }
 
    }
   
}
