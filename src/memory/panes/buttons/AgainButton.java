/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.buttons;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import memory.game.Game;
import memory.game.Player;
import memory.panes.MemoryPane;

/**
 *
 * @author clairotte
 */
public class AgainButton extends Button {

    public AgainButton(String string, ArrayList<Player> playersList, int nbPairsCards) {
        super(string);
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // 1. on créé une partie avec x joueurs et y paires de cartes
                Game game = new Game(playersList, nbPairsCards);

                // 2. on affiche le panneau de jeu
                MemoryPane memoryPane = new MemoryPane(game);

                // 3. on ajoute le memoryPane au Game pour pouvoir y accéder par la suite
                game.setMemoryPane(memoryPane);
            }
        });
    }
}
