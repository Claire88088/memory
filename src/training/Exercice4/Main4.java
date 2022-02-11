/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.Exercice4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author cbouto01
 */
public class Main4 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Button oeilG = new Button("OeilGauche");
        oeilG.setLayoutX(5);
        oeilG.setLayoutY(10);
                
        Label oeilD = new Label("OeilDroit");
        oeilD.setLayoutX(200);
        oeilD.setLayoutY(10);
        
        // méthodes purement Java :
        BorderStroke bs = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT);
        oeilD.setBorder(new Border(bs));
        oeilD.setPadding(new Insets(10, 20, 30, 50));
        
        // autres méthodes :
        oeilG.setStyle("-fx-border-width:2; -fx-background-color:black; -fx-border-color:black; -fx-padding:50");
        
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
