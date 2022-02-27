/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.panes;

import memory.game.Game;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import memory.panes.boxes.InfoBox;
import memory.game.Player;
import memory.panes.boxes.BtnsBox;
import memory.panes.boxes.PlayerBox;
import memory.panes.boxes.PlayersBox;
import memory.panes.buttons.AgainButton;
import memory.panes.buttons.ChangeButton;
import memory.panes.buttons.NextButton;
import memory.panes.buttons.QuitButton;


/**
 *
 * @author cbouto01
 */
public class MemoryPane extends BorderPane {
    private Game game;
    
    private InfoBox infoBox; 
    private MemoryGridPane memoryGridPane; // affichage de la grille de jeu
    private PlayersBox playersBox;
    private PlayerBox[] tabPlayersBox;
    private BtnsBox btnsBox;
    
    private NextButton nextBtn;
    private QuitButton quitBtn;
    private AgainButton againBtn;
    private ChangeButton changeBtn;
    private boolean changeBtnShown;
            
    public MemoryPane(Game game) { 
        this.setPadding(new Insets(20, 20, 20, 20)); 
        
        this.game = game;
        
        // création des boutons
        if (this.game.getPlayersList().size() > 1) {
            this.nextBtn = new NextButton("Joueur suivant", game);
        } else {
            this.nextBtn = new NextButton("Continuer", game);
        }
        this.quitBtn = new QuitButton("Quitter");
        this.againBtn = new AgainButton("Rejouer", this.game.getPlayersList(), this.game.getNbPairsCards());
        this.changeBtn = new ChangeButton("Echanger 2 cartes", this.game);
        this.changeBtnShown = false;
        
        // création des "boxes"
        this.infoBox = new InfoBox(this.game);  
        this.memoryGridPane = new MemoryGridPane(this.game.getGameGrid());
        
        int nbPlayers = this.game.getPlayersList().size();
        this.tabPlayersBox = new PlayerBox[nbPlayers];
        this.playersBox = new PlayersBox();
        this.addPlayerBox(nbPlayers);
        
        this.btnsBox = new BtnsBox(); 
        this.btnsBox.getChildren().addAll(this.nextBtn, this.quitBtn);  
        
        this.setTop(this.infoBox);
        this.setCenter(this.memoryGridPane);
        this.setRight(this.playersBox);
        this.setBottom(this.btnsBox);
                
        if (game.getPlayersList().size() > 1) {
            this.addChangeBtn();
        } 
               
        // on affiche le panneau dans la fenêtre
        this.showMemoryPane();
        
    }

    public void showMemoryPane() {
        Stage memoryStage = new Stage();
        Scene scene = new Scene(this, 1000, 700);
        
        memoryStage.setTitle("Jeu de Memory");
        memoryStage.setScene(scene);
        memoryStage.show();
    }
    
    public void addAgainBtn() {
        this.btnsBox.getChildren().clear();
        this.btnsBox.getChildren().add(this.againBtn);
    }
    
    public void addChangeBtn() {
       this.btnsBox.getChildren().clear();
       this.btnsBox.getChildren().addAll(this.nextBtn, this.quitBtn, this.changeBtn);
    }
    
    public void removeChangeBtn() {
       this.btnsBox.getChildren().clear();
       this.btnsBox.getChildren().addAll(this.nextBtn, this.quitBtn);
    }
    
    public void addPlayerBox(int nbPlayers) {
        for (int i = 0; i < nbPlayers; i++) {
            // création d'une box d'un joueur
            Player player = this.game.getPlayersList().get(i);
            PlayerBox playerBox = new PlayerBox(player, game);
            this.playersBox.getChildren().add(playerBox);
            
            // on la stocke aussi dans le tableau playersBox pour pouvoir l'utiliser par la suite dans les event
            this.tabPlayersBox[i] = playerBox;
        }    
    }
    
    public void changePlayer(int currentPlayerNumber, int nextPlayerNumber, String nextPlayerName) {
        this.tabPlayersBox[currentPlayerNumber].setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
        this.tabPlayersBox[nextPlayerNumber].setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5; -fx-background-color: magenta;");
        this.infoBox.getInfoLabel().setText("C'est le tour de " + nextPlayerName);
    }
    
    public void changeInfoLabel(String string) {
        this.infoBox.getInfoLabel().setText(string);
    }
    
    public void changeScore(int currentPlayerNumber, String newScore) {
        ((Label) this.tabPlayersBox[currentPlayerNumber].getChildren().get(2)).setText(newScore); 
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public InfoBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(InfoBox infoBox) {
        this.infoBox = infoBox;
    }

    public MemoryGridPane getMemoryGridPane() {
        return memoryGridPane;
    }

    public void setMemoryGridPane(MemoryGridPane memoryGridPane) {
        this.memoryGridPane = memoryGridPane;
    }

    public PlayersBox getPlayersBox() {
        return playersBox;
    }

    public void setPlayersBox(PlayersBox playersBox) {
        this.playersBox = playersBox;
    }

    public PlayerBox[] getTabPlayersBox() {
        return tabPlayersBox;
    }

    public void setTabPlayersBox(PlayerBox[] tabPlayersBox) {
        this.tabPlayersBox = tabPlayersBox;
    }

    public BtnsBox getBtnsBox() {
        return btnsBox;
    }

    public void setBtnsBox(BtnsBox btnsBox) {
        this.btnsBox = btnsBox;
    }
}
