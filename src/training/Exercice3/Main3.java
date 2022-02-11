/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.Exercice3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author cbouto01
 */
public class Main3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MyRegion reg = new MyRegion("disque", Color.RED);
        Scene scene = new Scene(reg);
        
        MyRegion reg2 = new MyRegion("carre", Color.GREEN);
        Scene scene2 = new Scene(reg2);
        
        primaryStage.setTitle("Exercice 3");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Stage secondStage = new Stage();
        secondStage.setTitle("Exercice 3.2");
        secondStage.setScene(scene2);
        secondStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
