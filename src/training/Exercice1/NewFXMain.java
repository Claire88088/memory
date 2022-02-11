/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.Exercice1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author cbouto01
 */
public class NewFXMain extends Application {
    
      
    @Override
    public void start(Stage primaryStage) {
        // 1. créer 4 labels contenant chacun un texte
        /*Label label1 = new Label("Mon Label 1");
        Label label2 = new Label("Mon Label 2");
        Label label3 = new Label("Mon Label 3");
        Label label4 = new Label("Mon Label 4");
        
        // 2.
        label1.setLayoutX(10);
        label1.setLayoutY(10);
        
        label2.setLayoutX(20);
        label2.setLayoutY(30);
        
        label3.setLayoutX(30);
        label3.setLayoutY(50);
        
        label4.setLayoutX(40);
        label4.setLayoutY(70);
        */
        
        // 3.
        Button oeilG = new Button("OeilGauche");
        oeilG.setLayoutX(5);
        oeilG.setLayoutY(10);
                
        Label oeilD = new Label("OeilDroit");
        oeilD.setLayoutX(200);
        oeilD.setLayoutY(10);
        
        Circle nez = new Circle();
        nez.setCenterX(150);
        nez.setCenterY(100);
        nez.setRadius(40);
        nez.setFill(Color.RED);
        
        Line bouche = new Line();
        bouche.setStartX(100);
        bouche.setStartY(165);
        bouche.setEndX(200);
        bouche.setEndY(165);
        
        Pane root = new Pane();
        //root.getChildren().addAll(label1, label2, label3, label4);
        root.getChildren().addAll(oeilG, oeilD, nez, bouche);
      
        Scene scene = new Scene(root, 300, 250);
        //Scene scene = new Scene(root);
        // si pas de dimension : prend la taille du contenu

        primaryStage.setTitle("Toto");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         // 4.  
        oeilG.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                bouche.setStartY(bouche.getStartY()+5);
                bouche.setEndY(bouche.getEndY()+5);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
