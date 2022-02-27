/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memory.panes.labels;

import javafx.scene.control.Label;

/**
 *
 * @author clairotte
 */
public class ScoreLabel extends Label {
     public ScoreLabel(String score) {
        super(score);
        this.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
    }
}
