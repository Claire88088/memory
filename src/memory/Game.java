/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 *
 * @author cbouto01
 */
public class Game {
    
    private ArrayList<Player> playersList;
    private Player currentPlayer;
    private Player lowerScorePlayer;
     
    private MemoryPane memoryPane; // affichage du jeu
    private Grid gameGrid; // grille "logique" du jeu
   
    private int nbPairsInGame;
    private int nbCardsUp;
    private ArrayList<Card> cardsUpList;
    private int nbPairsCards;
    
    
    
    public Game(ArrayList<Player> playersList, int nbPairsCards) {
        //------DONE----------------------------------------------
        // pour jouer une partie : on créé une partie avec x joueurs et y paires de cartes => INITIALISE au niveau de InfoPane
        
        // si le jeu n'a pas commencé : on affiche la fenêtre qui demande :
        // le nombre de joueurs : entre 2 et 4
        // puis le nom des joueurs
        // le nombre de cartes : entre 8 et 20 (4 paires et 10 paires)
        // FAIT AU NIVEAU DE InfoPane
        
        // début du jeu :
        // on affiche la fenêtre de jeu : MemoryPane
        //this.memoryPane = new MemoryPane(playersList, gameGrid); FAIT AU NIVEAU de InfoPane
        
        //--------------------------------------------------------
        
        this.playersList = playersList;
        
        // le premier joueur à jouer est le premier à avoir été instancié
        this.currentPlayer = playersList.get(0);
        // par défaut le joueur avec le plus petit score est le premier
        this.lowerScorePlayer = playersList.get(0);
                
        this.memoryPane = null; // on assignera la valeur lorsque le MemoryPane sera instancié
        
        // on créé la grille de jeu
        this.gameGrid = new Grid(nbPairsCards);
        // les cartes sont mises en place aléatoirement
        this.gameGrid.shuffle();
        
        this.nbPairsCards = nbPairsCards;
        this.nbPairsInGame = nbPairsCards;                    
        this.nbCardsUp = 0;  // nb de fois où une carte a été retournée (max : 2 fois)
        this.cardsUpList = new ArrayList();
        
        // on remet à zéro le jeu
        this.resetGame();
        
        // on les retourne
        Card[][] cardsGrid = this.gameGrid.getCardsGrid();        
        for (int row = 0; row < this.gameGrid.getNbRows(); row++){
            for (int col = 0; col < this.gameGrid.getNbCols(); col++) {
                Card card = cardsGrid[row][col];
              
                // on vérifie qu'il y a bien une carte
                if (card != null) {
                    card.setOnMouseClicked(event -> {
                        
                        // on vérifie que le joueur peut encore jouer
                        if (this.currentPlayer.isPlaying()) {
                        
                            // on vérifie que la carte n'a pas été déjà été trouvée
                            if (!card.isFind()){ 
                                
                                // on récupère les "numbers" du joueur courant et du joueur suivant pour pouvoir changer le tour des joueurs
                                int currentPlayerNumber = currentPlayer.getNumber();

                                int nextPlayerNumber;
                                int listSize = this.playersList.size();
                                if (currentPlayerNumber == (listSize-1)) {   
                                    nextPlayerNumber = 0;
                                } else {
                                    nextPlayerNumber = currentPlayerNumber + 1;
                                }                          

                                // le joueur peut retourner 2 cartes maximum
                                if (this.nbCardsUp < 2) { // ---------TODO : utiliser cardsUpList.length à la place de nbCardsUp----------------
                                    this.nbCardsUp++;
                                //if (this.firstCardUp = false) {
                                  //  this.firstCardUp = true; 
                                    
                                    card.switchIsUp();
                                    
                                    // on ajoute la carte retournée à la liste
                                    this.cardsUpList.add(card);
                                  
                                } else { // --------TODO : gérer le cas où un seul joueur------------                               
                                    this.currentPlayer.switchIsPlaying();
                                   
                                    //card.switchIsUp();
                                    
                                    Card upCard1 = this.cardsUpList.get(0);
                                    Card upCard2 = this.cardsUpList.get(1);
                                    //Card upCard2 = card;
                                    
                                    // on remet à zéro la liste de cartes retournées
                                    this.cardsUpList.clear();
                                    this.nbCardsUp = 0;
                                    //this.firstCardUp = false;
                                            
                                    // si les 2 cartes retournées sont les mêmes
                                    if (upCard1.getNumber() == upCard2.getNumber()) {                                                              
                                        // le joueur gagne 1 point
                                        this.currentPlayer.getPoint();
                                        ((Label) this.memoryPane.getTabPlayersBox()[currentPlayerNumber].getChildren().get(2)).setText(this.currentPlayer.getScoreToString()); // il faut forcer le type !

                                        // on met à jour le joueur avec le score le plus bas
                                        
                                        
                                        // les cartes ne sont plus "en jeu"
                                        this.nbPairsInGame--;
                                        upCard1.removeFromGame();
                                        upCard2.removeFromGame();
                                        
                                        this.gameGrid.removeCardOfGrid(upCard1);
                                        this.gameGrid.removeCardOfGrid(upCard2);
                                        
                                        // on change l'affichage
                                        this.changeGridPane();                            
                                        
                                    } else {
                                        // les cartes sont remises face cachée  
                                        upCard1.switchIsUp();                              
                                        upCard2.switchIsUp();
                                    }

                                    // on vérifie que le jeu n'est pas fini
                                    if (this.nbPairsInGame != 0) {
                                        
                                         // on doit cliquer sur le bouton pour passer au joueur suivant
                                        ((Label) this.memoryPane.getInfoLabel()).setText("Votre tour est terminé : cliquez pour changer de joueur"); 
                                       
                                    } else {
                                        Player winner = this.playersList.get(0);
                                        int bestScore = 0;
                                        int nbPlayers = this.playersList.size();
                                        
                                        for (int i = 0; i < nbPlayers; i++) {
                                          
                                            Player player = this.playersList.get(i);
                                            System.out.println("score "+player.getScore());
                                            if (player.getScore() > bestScore) {
                                                bestScore = player.getScore();
                                                winner = player;
                                            }
                                        }
                                        
                                        // gagnant = joueur qui a le score le plus grand
                                        ((Label) this.memoryPane.getInfoLabel()).setText("La partie est terminée : " + winner.getName() + " a gagné !");
                                        
                                        this.memoryPane.setBottom(this.memoryPane.getAgainBox());
                                        //---------------- todo :possibilité de rejouer ----------------------------------
                                    }
                                }
                            }  
                        }
                    });
                }          
            }
        }
                
        // si elles sont identiques : il marque 1 point et les cartes sont retirées du jeu
        
        // si elles sont différentes : elles sont remises face cachée
        
        // lorsqu'il n'y a plus de cartes sur le plateau
        // fin de la partie
        
        // gagnant = joueur qui a le score le plus grand
        
        //--------- TO DO : joueur avec le plus petit score--------------
        
    }
    
    
    
    //-------- TODO : calcul des points à la fin du tour du joueur-----------
    
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public Grid getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public Player getLowerScorePlayer() {
        return lowerScorePlayer;
    }

    public void setLowerScorePlayer(Player lowerScorePlayer) {
        this.lowerScorePlayer = lowerScorePlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getNbPairsCards() {
        return nbPairsCards;
    }

    public void setNbPairsCards(int nbPairsCards) {
        this.nbPairsCards = nbPairsCards;
    }

    public MemoryPane getMemoryPane() {
        return memoryPane;
    }

    public void setMemoryPane(MemoryPane memoryPane) {
        this.memoryPane = memoryPane;
    }
    
    public void changeGridPane() {
        GridPane gridPane = (GridPane) this.memoryPane.getMemoryGridPane();
        
// on vide la grille d'affichage
        gridPane.getChildren().clear();
        
        // on la recréé
        // la première ligne a une hauteur imposée et la première colonne a une largeur imposée
        RowConstraints row1 = new RowConstraints(100);
        ColumnConstraints col1 = new ColumnConstraints(50);

        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(25);
        gridPane.setVgap(15);  
        gridPane.getRowConstraints().add(row1);
        gridPane.getColumnConstraints().add(col1);

        for (int gridRow = 0; gridRow < this.gameGrid.getNbRows(); gridRow++){
            for (int gridCol = 0; gridCol < gameGrid.getNbCols(); gridCol++) {
                if (this.gameGrid.getCardsGrid()[gridRow][gridCol] != null) {
                    gridPane.add(this.gameGrid.getCardsGrid()[gridRow][gridCol], gridCol, gridRow); 
                }
            }
        }
    }
    
    public void resetGame() {
        int nbPlayers = this.playersList.size();
                                        
        for (int i = 0; i < nbPlayers; i++) {
            Player player = this.playersList.get(i);
            player.resetScore();
            if (player == this.currentPlayer) {
                player.setIsPlaying(Boolean.TRUE);
            } else {
                player.setIsPlaying(Boolean.FALSE);
            }
        } 
    }
    
    
   
}
