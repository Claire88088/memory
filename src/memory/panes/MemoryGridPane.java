/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.panes;

import memory.game.Grid;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author cbouto01
 */
public class MemoryGridPane extends GridPane {
    
    
    public MemoryGridPane(Grid grid) {
        // la première ligne a une hauteur imposée et la première colonne a une largeur imposée
        RowConstraints row1 = new RowConstraints(100);
        ColumnConstraints col1 = new ColumnConstraints(50);
        
        this.setPadding(new Insets(20));
        this.setHgap(25);
        this.setVgap(15);
        
        this.getRowConstraints().add(row1);
        this.getColumnConstraints().add(col1);
       
        for (int row = 0; row < grid.getNbRows(); row++){
            for (int col = 0; col < grid.getNbCols(); col++) {
                if (grid.getCardsGrid()[row][col] != null) {
                    this.add(grid.getCardsGrid()[row][col], col, row); 
                }
            }
        }
    }
    
}
