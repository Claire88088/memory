/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.boxes;

import java.util.ArrayList;
import javafx.scene.layout.HBox;
import memory.game.Player;
import memory.panes.buttons.AgainButton;

/**
 *
 * @author clairotte
 */
public class AgainBox extends HBox {
    AgainButton againBtn;
    
    public AgainBox(ArrayList<Player> playersList, int nbPairsCards) {
        super();
        this.againBtn = new AgainButton("Rejouer", playersList, nbPairsCards);
        
        this.getChildren().add(againBtn);
    }
}
