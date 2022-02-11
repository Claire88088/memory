/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

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


/**
 *
 * @author cbouto01
 */
public class MemoryPane extends BorderPane {
    
    private HBox infoBox;
    private Label infoLabel;
    private MemoryGridPane memoryGridPane;
    private Game game;
    private HBox[] tabPlayersBox; // tableau contenant les playerBox
    private HBox againBox;
    private HBox changeBox;
            
    public MemoryPane(Game game) {
        
        this.game = game;
        
        this.setPadding(new Insets(20, 20, 20, 20)); 
        
        // création de la box d'informations du jeu
        this.infoBox = new HBox(10);
        this.infoLabel = new Label("C'est le tour de " + this.game.getCurrentPlayer().getName());
        this.infoBox.getChildren().add(this.infoLabel);
        
        // création de l'affichage de la grille de jeu
        this.memoryGridPane = new MemoryGridPane(this.game.getGameGrid());
        
        // création des box des joueurs
        VBox playersBox = this.createPlayersBox(this.game.getPlayersList());
        
        // création des boutons
        HBox btnsBox = this.createBtnsBox(this.game, this.infoLabel, this.tabPlayersBox);
        
        // création du bouton Rejouer
        this.createAgainBox(this.game.getPlayersList(), this.game.getNbPairsCards());
        
        // création du bouton Echanger des cartes
        this.createChangeBox(this.game.getLowerScorePlayer());
       
        this.setTop(this.infoBox);
        this.setCenter(this.memoryGridPane);
        this.setRight(playersBox);
        this.setBottom(btnsBox);
        
        // on affiche le panneau dans la fenêtre
        this.showMemoryPane();
    }
    
    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }
    
    private HBox createBtnsBox(Game game, Label infoLabel,HBox[] tabPlayersBox) {
        
        // création du bouton Joueur suivant
        Button nextBtn = new Button("Joueur suivant");
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // le joueur courant arrête de jouer et l'affichage change
                Player currentPlayer = game.getCurrentPlayer();
                int currentPlayerNumber = currentPlayer.getNumber();
                
                currentPlayer.setIsPlaying(false);
                tabPlayersBox[currentPlayerNumber].setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
                
                int nextPlayerNumber;
                
                int listSize = game.getPlayersList().size();
                if (currentPlayerNumber == (listSize-1)) {   
                    nextPlayerNumber = 0;
                } else {
                    nextPlayerNumber = currentPlayerNumber + 1;
                }               
                
                // le joueur suivant devient le joueur courant et commence à jouer
                Player nextPlayer = game.getPlayersList().get(nextPlayerNumber);
                nextPlayer.setIsPlaying(true);
                tabPlayersBox[nextPlayerNumber].setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5; -fx-background-color: magenta;");
                
                game.setCurrentPlayer(nextPlayer);
                infoLabel.setText("C'est le tour de " + game.getCurrentPlayer().getName());                            
            }
        });
        
        // création du bouton Quitter
        Button quitBtn = new Button("Quitter");
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        // boutons dans une HBox
        HBox btnsBox = new HBox(10);
        btnsBox.getChildren().addAll(nextBtn, quitBtn);
        
        return btnsBox;
    }
    
    private void createAgainBox(ArrayList<Player> playersList, int nbPairsCards) {
        this.againBox = new HBox();
        Button againBtn = new Button("Rejouer");
        againBtn.setOnAction(new EventHandler<ActionEvent>() {
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
        
        this.againBox.getChildren().add(againBtn);
    }
    
    private void createChangeBox(Player lowerScorePlayer) {
        this.changeBox = new HBox();
        Button changeBtn = new Button("Echanger 2 cartes");
        changeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // on clique sur 2 cartes et elles sont échangées
                System.out.println("on échange les cartes");
            }
        });
        
        this.changeBox.getChildren().add(changeBtn);
    }
    
    private VBox createPlayersBox(ArrayList<Player> playersList) {
        VBox playersBox = new VBox(20);
        playersBox.setAlignment(Pos.CENTER);
        
        int nbPlayers = playersList.size() ;
        this.tabPlayersBox = new HBox [nbPlayers];
        
        for (int i = 0; i < nbPlayers; i++) {
            // création d'une box d'un joueur
            HBox playerBox = new HBox();
            
            // on la stocke aussi dans le tableau playersBox pour pouvoir l'utiliser par la suite dans les event
            this.tabPlayersBox[i] = playerBox;
            
            if (playersList.get(i).isPlaying()) {   
                playerBox.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5; -fx-background-color: magenta;");
            }
            else {
                playerBox.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
            }
            
            Label playerName = new Label(playersList.get(i).getName());
            playerName.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
            
            Label scoreLab = new Label("Score");
            scoreLab.setStyle("-fx-padding:5");
            
            Label score = new Label(playersList.get(i).getScoreToString());
            score.setStyle("-fx-border-width:2; -fx-border-color:black; -fx-padding:5");
            
            playerBox.getChildren().addAll(playerName, scoreLab, score);
            playersBox.getChildren().add(playerBox);
        }
        
        return playersBox;
    }
    
    public void showMemoryPane() {
        Stage memoryStage = new Stage();
        Scene scene = new Scene(this, 1000, 700);
        
        memoryStage.setTitle("Jeu de Memory");
        memoryStage.setScene(scene);
        memoryStage.show();
    }

    public HBox[] getTabPlayersBox() {
        return tabPlayersBox;
    }

    public void setTabPlayersBox(HBox[] tabPlayersBox) {
        this.tabPlayersBox = tabPlayersBox;
    }

    public MemoryGridPane getMemoryGridPane() {
        return memoryGridPane;
    }

    public void setMemoryGridPane(MemoryGridPane memoryGridPane) {
        this.memoryGridPane = memoryGridPane;
    }

    public HBox getAgainBox() {
        return againBox;
    }

    public void setAgainBox(HBox againBox) {
        this.againBox = againBox;
    }

    public HBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(HBox infoBox) {
        this.infoBox = infoBox;
    }

    public HBox getChangeBox() {
        return changeBox;
    }

    public void setChangeBox(HBox changeBox) {
        this.changeBox = changeBox;
    }
    
    

}
