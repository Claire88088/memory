/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.Exercice2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import static javafx.scene.control.ContentDisplay.BOTTOM;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javax.xml.transform.Source;

/**
 *
 * @author cbouto01
 */
public class JavaFX extends BorderPane {
    
    private Button[] buttons = new Button[3];
    
    private HBox content;
    private Label northLabel;
    private Label southLabel;

    private Button quitButton = new Button("Quit");
    
    public JavaFX() {
        this.setStyle("-fx-background-color: magenta;");

        this.buildFrameContent();
    }

    private void buildFrameContent(){
        content = this.createButtonsBox();
        northLabel = new Label("I am the north label");
        southLabel = new Label("I am the south label");

        this.setCenter(content);
        this.setTop(northLabel);
        this.setBottom(southLabel); 
        //quitButton.setAlignment(Pos.BOTTOM_RIGHT);
        //this.setAlignment(quitButton, Pos.BOTTOM_RIGHT);
        System.out.println(quitButton.getAlignment());
        this.setRight(buildEastBox());
    }

    private VBox buildEastBox(){
        VBox b = new VBox();
        b.getChildren().add(quitButton);

        this.quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        return b;
    }

    private HBox createButtonsBox(){
        HBox content = new HBox();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("bouton " +(i+1));
            content.getChildren().add(buttons[i]);
            
            // quand on clique sur un des 3 boutons de la zone centrale 
            //-> son texte s'affiche dans el label de la zone nord
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent event) {
                    Button source = (Button) event.getSource();
                    String texte = source.getText();
                    northLabel.setText(texte);
                }
            });
        }
        content.setBorder(new Border(new BorderStroke(Color.BLUE, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
        return content;
    }
    
    
}
