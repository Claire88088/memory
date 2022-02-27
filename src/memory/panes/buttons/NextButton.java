/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import memory.game.Game;
import memory.game.Player;
import memory.panes.boxes.PlayerBox;
import memory.panes.boxes.PlayersBox;

/**
 *
 * @author clairotte
 */
public class NextButton extends Button {
    
    public NextButton(String text, Game game) {
        super(text);
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.resetTurn();
                game.changePlayer();
            }
        });
    }
}
