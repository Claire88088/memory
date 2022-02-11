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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author cbouto01
 */
public class InfoGridPane extends GridPane {
    
    public InfoGridPane() {
        
        InfoGridPane grid = this; // pour pouvoir récupérer la grille dans l'event
         
        this.setPadding(new Insets(20, 20, 20, 20));  
        this.setVgap(10); 
        this.setHgap(5);       
        
        // la première colonne a une largeur imposée
        ColumnConstraints col1 = new ColumnConstraints(200);
        this.getColumnConstraints().add(col1);    
        
        // choix du nombre de joueurs
        Label nbPlayersLabel = new Label("Nombre de joueurs :");
        ChoiceBox nbPlayersChoiceBox = new ChoiceBox(); 
        nbPlayersChoiceBox.getItems().addAll(1, 2, 3, 4, 5); 
        
        // choix du nombre de paires de cartes
        Label nbPairsCardsLabel = new Label("Nombre de paires de cartes :");       
        ChoiceBox nbPairsCardsChoiceBox = new ChoiceBox(); 
        nbPairsCardsChoiceBox.getItems().addAll(4, 5, 6, 7, 8, 9, 10);     
        
        Button choiceButton = new Button("Valider");
        
        this.add(new Text("Pour commencer une partie :"), 0, 0); // col, row
        this.add(new Text("Choisir le nombre de joueurs et de paires de cartes"), 0,1);
        this.add(nbPlayersLabel, 0, 2);
        this.add(nbPlayersChoiceBox, 1, 2); 
        this.add(nbPairsCardsLabel, 0, 3); 
        this.add(nbPairsCardsChoiceBox, 1, 3); 
        this.add(choiceButton, 0, 4); 
        
        // ---------- TODO : vérifier que l'utilisateur a bien choisi des valeurs --------------
        
        // lorsqu'on valide le choix du nombre de joueurs et de cartes : on demande le nom des joueurs
        choiceButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // on supprime l'affichage précédent
                grid.getChildren().clear(); 
                
                int nbPlayers = (int) nbPlayersChoiceBox.getValue();
                int nbPairsCards = (int) nbPairsCardsChoiceBox.getValue();
                
                ArrayList<Player> playersList = new ArrayList();
                
                for (int i = 0; i < nbPlayers; i++) {
                    Label playerNameLabel = new Label("Nom du joueur " + (i+1) + " : ");
                    TextField playerNameField = new TextField();
                
                    Button namesButton = new Button("Valider");
                    
                    namesButton.setOnAction(new EventHandler<ActionEvent>() {
                    
                        @Override
                        public void handle(ActionEvent event) {
                            // on supprime l'affichage précédent
                            Node label = grid.getChildren().get(1);
                            Node text = grid.getChildren().get(2);
                            Node btn = grid.getChildren().get(3);
                            
                            // ---------TODO : voir pour que tous les éléments de la ligne s'effacent -----
                            //Node node = grid.getChildren().get(4); // il y a un autre noeud "invisible" à enlever
                            
                            grid.getChildren().removeAll(label, text, btn/*, node*/);
                            
                            // ----- TODO : on ferme la fenêtre----------------
                            // --------TODO : vérifier que les champs sont remplis--------
                            
                            // on créé un nouveau joueur
                            Player player = new Player(playerNameField.getText());                 
                            playersList.add(player);                            
                            
                            // quand tous les joueurs sont créés on peut commencer le jeu
                            if (Player.getNbPlayers() == nbPlayers) {            
                                // on commence le jeu :            
                                // 1. on créé une partie avec x joueurs et y paires de cartes
                                Game game = new Game(playersList, nbPairsCards);
                               
                                // 2. on affiche le panneau de jeu
                                MemoryPane memoryPane = new MemoryPane(game);
                                
                                // 3. on ajoute le memoryPane au Game pour pouvoir y accéder par la suite
                                game.setMemoryPane(memoryPane);
                            }
                        }
                    });
                    
                    grid.add(new Text("Choisir le nom des joueurs :"), 0, 0);
                    //--- TODO : ajouter une contrainte pour la colonne 1 -------------------
                    grid.add(playerNameLabel, 0, (i+1)); // col, row
                    grid.add(playerNameField, 1, (i+1));
                    grid.add(namesButton, 2, (i+1));
                };
            };
        });
    }
}