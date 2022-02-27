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

/**
 *
 * @author clairotte
 */
public class ChangeButton extends Button {

    public ChangeButton(String string, Game game) {
        super(string);
         this.setOnAction((ActionEvent event) -> {
            game.clicOnChangeCards();
        });
    } 
}
