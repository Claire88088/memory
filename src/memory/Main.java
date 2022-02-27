/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import memory.panes.InfoGridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author cbouto01
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        InfoGridPane infoGridPane = new InfoGridPane();
        Scene infoScene = new Scene(infoGridPane, 1000, 500);
        
        primaryStage.setTitle("Préparation du jeu de Memory");
        primaryStage.setScene(infoScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
