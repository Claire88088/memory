/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.boxes;

import memory.game.Game;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author clairotte
 */
public class InfoBox extends HBox {
    Label infoLabel;
    
    public InfoBox(Game game) {
        super(10);
        this.infoLabel = new Label("C'est le tour de " + game.getCurrentPlayer().getName());
        this.getChildren().add(this.infoLabel);
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }
}
