/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.boxes;

import memory.panes.labels.PlayerNameLabel;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import memory.game.Game;
import memory.game.Player;
import memory.panes.labels.ScoreLabel;
import memory.panes.labels.ScoreLabelLabel;

/**
 *
 * @author clairotte
 */
public class PlayerBox extends HBox {
    PlayerNameLabel playerNameLabel;      
    ScoreLabelLabel scoreLabel;
    ScoreLabel score;

    public PlayerBox(Player player, Game game) {
        if (player == game.getCurrentPlayer()) {   
            this.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5; -fx-background-color: magenta;");
        }
        else {
            this.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
        }
        
        this.playerNameLabel = new PlayerNameLabel(player.getName());       
        this.scoreLabel = new ScoreLabelLabel("Score");
        this.score = new ScoreLabel(player.getScoreToString());
        
        this.getChildren().addAll(this.playerNameLabel, this.scoreLabel, this.score);
    }

    public PlayerNameLabel getPlayerNameLabel() {
        return playerNameLabel;
    }

    public void setPlayerNameLabel(PlayerNameLabel playerNameLabel) {
        this.playerNameLabel = playerNameLabel;
    }

    public ScoreLabelLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(ScoreLabelLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public ScoreLabel getScore() {
        return score;
    }

    public void setScore(ScoreLabel score) {
        this.score = score;
    }  
}
