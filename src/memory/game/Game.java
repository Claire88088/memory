/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import memory.panes.MemoryPane;

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
    private int nbPlayers;
    private ArrayList<Card> cardsUpList;
    private int nbPairsCards; 
    
    private boolean changingCards;
    private ArrayList<Card> cardsChosenList;
    private boolean isChangeBtnShown;
    
    
    
    public Game(ArrayList<Player> playersList, int nbPairsCards) {
        
        this.playersList = playersList;
        
        this.currentPlayer = playersList.get(0); // le premier joueur à jouer est le premier à avoir été instancié
        this.lowerScorePlayer = playersList.get(0);  // par défaut le joueur avec le plus petit score est le premier
                
        this.memoryPane = null; // on assignera la valeur lorsque le MemoryPane sera instancié
        
        // on créé la grille de jeu
        this.gameGrid = new Grid(nbPairsCards);
        this.gameGrid.shuffle(); // les cartes sont mises en place aléatoirement
        
        this.nbPairsCards = nbPairsCards;
        
        // variables du jeu "normal"
        this.nbPairsInGame = nbPairsCards;
        this.nbPlayers = this.playersList.size();
        this.cardsUpList = new ArrayList();
        
        // variables de l'échange de cartes
        this.changingCards = false;
        this.cardsChosenList = new ArrayList();
        
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
                        // si le joueur a choisi d'échanger des cartes 
                        if (this.changingCards) {
                            // si le joueur n'a pas encore choisi de carte
                            if (!this.currentPlayer.isOneCardChosen()) {
                                this.chooseCard(card);
                            } else {
                                this.chooseCard(card);
                                this.changeCards();
                            }
                        } else {
                            // on enlève le choix d'échanger des cartes
                            this.memoryPane.removeChangeBtn();
                            
                            // si le joueur peut jouer (n'a pas retourné 2 cartes)
                            if (this.currentPlayer.getNbCardsUp() < 2) {
                                // si le joueur n'a pas encore retourné de carte
                                if (this.currentPlayer.getNbCardsUp() == 0) {
                                    this.returnCard(card);

                                } else {
                                    if (card.isUp()) { // si la carte a déjà été retournée
                                        this.memoryPane.changeInfoLabel("Cette carte est déjà retournée : choisissez-en une autre !");     
                                    } else {
                                        this.returnCard(card);
                                        this.scoreCalculation();

                                        // si on joue à plusieurs
                                        if (this.playersList.size() > 1) { 
                                            // on doit cliquer sur le bouton pour passer au joueur suivant
                                            this.memoryPane.changeInfoLabel("Votre tour est terminé : cliquez pour changer de joueur"); 
                                        } else {
                                            this.memoryPane.changeInfoLabel("Votre tour est terminé : cliquez pour continuer"); 
                                        }       
                                    }
                                }
                            }
                            
                        }
                    });                 
                }          
            }
        }
    }
    
    public void resetGame() {
        int nbPlayers = this.playersList.size();                          
        for (int i = 0; i < nbPlayers; i++) {
            Player player = this.playersList.get(i);
            player.resetScore();
        } 
    }
    
    public void chooseCard(Card card) {
        card.switchIsChosen();
        this.cardsChosenList.add(card);
                
        if (!this.currentPlayer.isOneCardChosen()) {
            this.memoryPane.changeInfoLabel("Choisissez une seconde carte !");       
        }
        
        this.currentPlayer.switchOneCardChosen();
    }
    
     public void changeCards() {
        Card chosenCard1 = this.cardsChosenList.get(0);
        Card chosenCard2 = this.cardsChosenList.get(1);

        // on échange les positions des 2 cartes
        this.gameGrid.swap(chosenCard1.getPosition(), chosenCard2.getPosition());

        // on remet à zéro
        this.cardsChosenList.clear();
        this.changingCards = false;
        this.memoryPane.removeChangeBtn();
        
        chosenCard1.switchIsChosen();
        chosenCard2.switchIsChosen();
        
        this.memoryPane.changeInfoLabel("Les cartes ont été échangées : c'est le tour de " + this.getCurrentPlayer().getName());  

        // on change l'affichage de la grille
        this.changeGridPane();   
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
        
    public void returnCard(Card card) {
        card.switchIsUp();
        this.cardsUpList.add(card); // on ajoute la carte retournée à la liste
        this.currentPlayer.addNbCardsUp();
    }

    public void scoreCalculation() {
        Card upCard1 = this.cardsUpList.get(0);
        Card upCard2 = this.cardsUpList.get(1);
        
        // si les 2 cartes retournées sont les mêmes
        if (upCard1.getNumber() == upCard2.getNumber()) {                                                              
            // le joueur gagne 1 point
            this.currentPlayer.getPoint();

            // on met à jour le joueur avec le score le plus bas
            this.updateLowerScorePlayer();   
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
    
    public void resetTurn() {
        Card upCard1 = this.cardsUpList.get(0);
        Card upCard2 = this.cardsUpList.get(1);
        
        // on remet à zéro la liste de cartes retournées
        this.cardsUpList.clear();
        this.currentPlayer.resetNbCardsUp();
        
        // si les 2 cartes retournées sont les mêmes
        if (upCard1.getNumber() == upCard2.getNumber()) {
            // les cartes ne sont plus "en jeu"
            this.nbPairsInGame--;
          
            this.gameGrid.removeCardOfGrid(upCard1);
            this.gameGrid.removeCardOfGrid(upCard2);
            
            this.memoryPane.changeScore(this.currentPlayer.getNumber(), this.currentPlayer.getScoreToString());
            // on change l'affichage
            this.changeGridPane();                            

        } else {
            // les cartes sont remises face cachée  
            upCard1.switchIsUp();                              
            upCard2.switchIsUp();
        }
        
        // si la partie n'est pas finie
        if (this.nbPairsInGame != 0) {
            if (this.nbPlayers > 1) { 
                this.changePlayer();
            } else {
                this.memoryPane.changeInfoLabel(this.currentPlayer.getName() + " : vous pouvez continuer");
            }
        } else {
            Player winner = this.winnerCalculation();
            this.memoryPane.changeInfoLabel("La partie est terminée : " + winner.getName() + " a gagné !");
            // ajout du bouton pour rejouer
            this.memoryPane.addAgainBtn();
        }     
    }
        
    public void changePlayer() {
        // on enlève bouton "change" si présent
        this.memoryPane.removeChangeBtn();

        int currentPlayerNumber = this.currentPlayer.getNumber();
        int nextPlayerNumber;

        if (currentPlayerNumber == (nbPlayers-1)) {   
            nextPlayerNumber = 0;
        } else {
            nextPlayerNumber = currentPlayerNumber + 1;
        }               
        
         // le joueur suivant devient le joueur courant
        Player nextPlayer = this.playersList.get(nextPlayerNumber);
        this.setCurrentPlayer(nextPlayer);
        
        // gestion du bouton "Echanger 2 cartes"
        if (this.nbPlayers > 1) {
            if (this.currentPlayer == this.lowerScorePlayer) {
                this.memoryPane.addChangeBtn();
            } else {
                this.memoryPane.removeChangeBtn();
            }
        }
        
        // l'affichage change
        this.memoryPane.changePlayer(currentPlayerNumber, nextPlayerNumber, nextPlayer.getName());
    }
    
    public Player winnerCalculation() {
        Player winner = this.playersList.get(0);
        int bestScore = 0;

        for (int i = 0; i < this.nbPlayers; i++) {
            Player player = this.playersList.get(i);
            if (player.getScore() > bestScore) {
                bestScore = player.getScore();
                winner = player;
            }
        }
        return winner;
    }
    
    public void clicOnChangeCards() {
        this.setChangingCards(true);
        // l'affichage change
        this.memoryPane.changeInfoLabel("Choisissez une première carte !");
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
