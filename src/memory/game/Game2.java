/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import memory.panes.MemoryPane;

/**
 *
 * @author cbouto01
 */
public class Game2 {
    
    private ArrayList<Player> playersList;
    private Player currentPlayer;
    private Player lowerScorePlayer;
     
    private MemoryPane memoryPane; // affichage du jeu
    private Grid gameGrid; // grille "logique" du jeu
   
    private int nbPairsInGame;
    private int nbPlayers;
    private int nbCardsUp;
    private ArrayList<Card> cardsUpList;
    private int nbPairsCards; 
    
    private boolean changingCards;
    private int nbCardsChosen;
    private boolean isChangeBtnShown;
    
    
    
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
        
        // variables du jeu "normal"
        this.nbPairsInGame = nbPairsCards;
        this.nbPlayers = this.playersList.size();
        this.nbCardsUp = 0;  // nb de fois où une carte a été retournée (max : 2 fois)
        this.cardsUpList = new ArrayList();
        
        // variables de l'échange de cartes
        this.changingCards = false;
        this.nbCardsChosen = 0;
        this.isChangeBtnShown = false;
        
        // on remet à zéro le jeu
        this.resetGame();
        
        // on retourne les cartes
        Card[][] cardsGrid = this.gameGrid.getCardsGrid();        
        for (int row = 0; row < this.gameGrid.getNbRows(); row++){
            for (int col = 0; col < this.gameGrid.getNbCols(); col++) {
                Card card = cardsGrid[row][col];
              
                // on vérifie qu'il y a bien une carte
                if (card != null) {
                    
                    card.setOnMouseClicked(event -> {
                        // la partie n'est pas finie
                        
                        // on vérifie que le joueur peut encore jouer
                        if (this.currentPlayer.isPlaying()) {
                            System.out.println("lower "+this.lowerScorePlayer.getName());
                            
                            if (this.nbPlayers > 1 && this.currentPlayer == this.lowerScorePlayer) {
                                if (!this.isChangeBtnShown) {
                                    this.memoryPane.addChangeBtn();
                                }
                            }
                        
                            // si le joueur a choisi d'échanger des cartes 
                            if (this.changingCards) {
                                this.changeCards(card);
                            /*    // le joueur ne peut choisir que 2 cartes maximum
                                if (this.nbCardsChosen < 2) {
                                    this.nbCardsChosen++;
                                    card.switchIsChosen();
                                    this.memoryPane.changeInfoLabel("Choisissez une seconde carte !");       
                                    // on ajoute la carte retournée à la liste
                                    this.cardsUpList.add(card);

                                } else {
                                    Card chosenCard1 = this.cardsUpList.get(0);
                                    Card chosenCard2 = this.cardsUpList.get(1);

                                    // on échange les positions des 2 cartes
                                    this.gameGrid.swap(chosenCard1.getPosition(), chosenCard2.getPosition());

                                    // on remet à zéro
                                    this.cardsUpList.clear();
                                    this.nbCardsChosen = 0;
                                    this.changingCards = false;
                                   // this.memoryPane.removeChangeBtn();
                                    chosenCard1.switchIsChosen();
                                    chosenCard2.switchIsChosen();
                                    this.memoryPane.changeInfoLabel("C'est le tour de " + this.getCurrentPlayer().getName());  

                                    // on change l'affichage de la grille
                                    this.changeGridPane();   
                                }*/
                            } else {
                            
                                // on récupère les "numbers" du joueur courant et du joueur suivant pour pouvoir changer le tour des joueurs
                                int currentPlayerNumber = currentPlayer.getNumber();

                                int nextPlayerNumber;
                                int listSize = this.playersList.size();
                                if (currentPlayerNumber == (listSize-1)) {   
                                    nextPlayerNumber = 0;
                                } else {
                                    nextPlayerNumber = currentPlayerNumber + 1;
                                }                          

                                // le joueur ne peut retourner que 2 cartes maximum
                                if (this.nbCardsUp < 2) {
                                    // la carte peut être retournée si elle n'a pas déjà été retournée lors du tour
                                    if (!card.isUp()) {
                                        this.nbCardsUp++;                            
                                        card.switchIsUp();

                                        // on ajoute la carte retournée à la liste
                                        this.cardsUpList.add(card);
                                    } else {
                                        this.memoryPane.changeInfoLabel("Cette carte est déjà retournée : choisissez-en une autre !"); 
                                    }
                                } else { 
                                    // si 1 seul joueur : il a toujours le tour

                                    // --to do : voir pour enlever le bouton next-----
                                    if (this.playersList.size() > 1) {
                                        this.currentPlayer.switchIsPlaying();
                                    }

                                    Card upCard1 = this.cardsUpList.get(0);
                                    Card upCard2 = this.cardsUpList.get(1);

                                    // on remet à zéro la liste de cartes retournées
                                    this.cardsUpList.clear();
                                    this.nbCardsUp = 0;                            

                                    // si les 2 cartes retournées sont les mêmes
                                    if (upCard1.getNumber() == upCard2.getNumber()) {                                                              
                                        // le joueur gagne 1 point
                                        this.currentPlayer.getPoint();
                                        ((Label) this.memoryPane.getTabPlayersBox()[currentPlayerNumber].getChildren().get(2)).setText(this.currentPlayer.getScoreToString()); // il faut forcer le type !

                                        // on met à jour le joueur avec le score le plus bas
                                        this.updateLowerScorePlayer();

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
                                        if (this.playersList.size() > 1) {   
                                            // on doit cliquer sur le bouton pour passer au joueur suivant
                                            this.memoryPane.changeInfoLabel("Votre tour est terminé : cliquez pour changer de joueur"); 
                                         }
                                    } else {
                                        Player winner = this.playersList.get(0);
                                        int bestScore = 0;
                                        int nbPlayers = this.playersList.size();

                                        for (int i = 0; i < nbPlayers; i++) {
                                            Player player = this.playersList.get(i);
                                            if (player.getScore() > bestScore) {
                                                bestScore = player.getScore();
                                                winner = player;
                                            }
                                        }

                                        // gagnant = joueur qui a le score le plus grand
                                        this.memoryPane.changeInfoLabel("La partie est terminée : " + winner.getName() + " a gagné !");

                                        // ajout du bouton pour rejouer
                                        this.memoryPane.addAgainBtn();
                                    }
                                }    
                            }
                        }                     
                    });
                }          
            }
        }
    }
    
    public void changePlayer() {
        // on enlève bouton "change" si présent
        this.memoryPane.removeChangeBtn();
        
        // le joueur courant arrête de jouer et l'affichage change 
        int currentPlayerNumber = this.currentPlayer.getNumber();
        this.currentPlayer.setIsPlaying(false);
        
        int nextPlayerNumber;

        if (currentPlayerNumber == (nbPlayers-1)) {   
            nextPlayerNumber = 0;
        } else {
            nextPlayerNumber = currentPlayerNumber + 1;
        }               
        
         // le joueur suivant devient le joueur courant et commence à jouer
        Player nextPlayer = this.playersList.get(nextPlayerNumber);
        nextPlayer.setIsPlaying(true);      
                
        this.setCurrentPlayer(nextPlayer);
                
        // l'affichage change
        this.memoryPane.changePlayer(currentPlayerNumber, nextPlayerNumber, nextPlayer.getName());
    }
    
    public void clicOnChangeCards() {
        this.setChangingCards(true);
        // l'affichage change
        this.memoryPane.changeInfoLabel("Choisissez une première carte !");
    }
    
    public void changeCards(Card card) {
        // le joueur ne peut choisir que 2 cartes maximum
        if (this.nbCardsChosen < 2) {
            this.nbCardsChosen++;
            card.switchIsChosen();
            this.memoryPane.changeInfoLabel("Choisissez une seconde carte !");       
            // on ajoute la carte retournée à la liste
            this.cardsUpList.add(card);

        } else {
            Card chosenCard1 = this.cardsUpList.get(0);
            Card chosenCard2 = this.cardsUpList.get(1);

            // on échange les positions des 2 cartes
            this.gameGrid.swap(chosenCard1.getPosition(), chosenCard2.getPosition());

            // on remet à zéro
            this.cardsUpList.clear();
            this.nbCardsChosen = 0;
            this.changingCards = false;
           // this.memoryPane.removeChangeBtn();
            chosenCard1.switchIsChosen();
            chosenCard2.switchIsChosen();
            this.memoryPane.changeInfoLabel("C'est le tour de " + this.getCurrentPlayer().getName());  

            // on change l'affichage de la grille
            this.changeGridPane();   
        }
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
    
    public void updateLowerScorePlayer() {
        int nbPlayers = this.playersList.size();
        int lowerScore = this.lowerScorePlayer.getScore();
            
        for (int i = 0; i < nbPlayers; i++) {
            int playerScore = this.playersList.get(i).getScore();
            if (playerScore < lowerScore) {
                this.lowerScorePlayer = this.playersList.get(i);
            } 
        }  
    }

    
    
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
   
    public boolean isChangingCards() {
        return changingCards;
    }

    public void setChangingCards(boolean changingCards) {
        this.changingCards = changingCards;
    }

    public boolean isIsChangeBtnShown() {
        return isChangeBtnShown;
    }

    public void setIsChangeBtnShown(boolean isChangeBtnShown) {
        this.isChangeBtnShown = isChangeBtnShown;
    }
    
}
