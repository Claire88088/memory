/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.Random;

/**
 *
 * @author cbouto01
 */
public class Grid {
    public static final int DEFAULT_NBPAIRSCARDS = 4;
    public static final int DEFAULT_NBCOLS = 4;
   
    private Card [][] cardsGrid;
    private int nbCols;
    private int nbRows;
    
    
    public Grid(int nbPairsCards)
    {
        // construit et remplit une grille : cartes -> non mélangées
        int cardNumber = 1;
        int nbGiven = 0; // nb de fois où le numéro a déjà été donné (max : 2 fois)
        int nbCards = nbPairsCards*2;
        
        this.nbCols = DEFAULT_NBCOLS;
        // le nb de lignes correspond au (nb de cartes / nb colonnes) + 1 si cela ne tombe pas juste
        // ex : 10 cartes / 4 colonnes => 2 lignes + 1 soit 3 lignes
        this.nbRows = nbCards / this.nbCols + ((nbCards%this.nbCols == 0) ? 0 : 1);
        
        // on construit une grille
        this.cardsGrid = new Card[this.nbRows][this.nbCols]; 
        
        // on remplit la grille avec des paires de cartes
        for (int row = 0; row < this.nbRows; row++){
            for (int col = 0; col < this.nbCols; col++) {
                Position position = new Position(row, col);
                // si on a des cartes à placer
                if (nbCards > 0) {
                    // on créé les cartes par paires
                    if (nbGiven < 2) {
                        // ------------- TODO : les cartes dvt être créées par paires ----------
                        // ------ une paire correspond à 2 cartes qui ont le même numéro = un attribut en commun --------
                        this.cardsGrid[row][col] = new Card(cardNumber, position);
                        nbCards --;
                        nbGiven ++;
                    } else {
                        cardNumber ++;
                        this.cardsGrid[row][col] = new Card(cardNumber, position);
                        nbCards --;
                        nbGiven = 1;
                    }
                } else {
                    this.cardsGrid[row][col] = null;
                } 
            }
        } 
    }
    
    public Grid()
    {
        this(DEFAULT_NBPAIRSCARDS);
    }
    
    //--------- TODO : mélanger les cartes-------------
    
    public Card[][] getCardsGrid() {
        return cardsGrid;
    }

    public void setCardsGrid(Card[][] cardsGrid) {
        this.cardsGrid = cardsGrid;
    }

    public int getNbCols() {
        return nbCols;
    }

    public void setNbCols(int nbCols) {
        this.nbCols = nbCols;
    }

    public int getNbRows() {
        return nbRows;
    }

    public void setNbRows(int nbRows) {
        this.nbRows = nbRows;
    }
    
    public void removeCardOfGrid(Card card) {
        // on récupère la position de la carte
        Position cardPos = card.getPosition();
        
        // on met la valeur null à la place de la position de cette carte dans la grille
        this.cardsGrid[cardPos.getRow()][cardPos.getColumn()] = null;
    }
    
    public String getGridToString() {
        int nbCols = this.getNbCols();
        int nbRows = this.getNbRows();
        String myString = "grille :";
        for (int i = 0; i < nbRows; i++) {
            for (int j = 0; j < nbCols; j++) {
                myString += this.cardsGrid[i][j].getNumber() ;
            }
        }
        
        return myString;
    }
    
  
    
    
    /*
    *   renvoie la carte placée à la position pos sur la grille
    */
    public Card getCard(Position pos) {
        // la position (x,y) correspond à une case de la grille (x,y)
        return this.cardsGrid[pos.getRow()][pos.getColumn()];
    }
    
    public void setCard(Position pos, Card card) {
        this.cardsGrid[pos.getRow()][pos.getColumn()] = card;
    }
    
    /*
    *    échange les contenus des cases aux positions p1 et p2
    */
    protected void swap(Position p1, Position p2)
    {   
        // si il n'y a pas de carte en p1
        if (this.getCard(p1) == null) {
            // on récupère la carte en p2
            Card card2 = this.getCard(p2);
            // on la place dans la position 1 au niveau de la grille et on change la valeur de sa propriété position
            this.cardsGrid[p1.getRow()][p1.getColumn()] = card2;
            card2.setPosition(p1);
            // on enlève la carte de la position 2
            this.cardsGrid[p2.getRow()][p2.getColumn()] = null;
        } else if (this.getCard(p2) == null) {  // si il n'y a pas de carte en p2
            Card card1 = this.getCard(p1);
            this.cardsGrid[p2.getRow()][p2.getColumn()] = card1;
            card1.setPosition(p2);
            this.cardsGrid[p1.getRow()][p1.getColumn()] = null;
        } else {
            // on échange les pions
            Card card1 = this.getCard(p1);
            Card card2 = this.getCard(p2);
            this.cardsGrid[p1.getRow()][p1.getColumn()] = card2;
            card2.setPosition(p1);
            this.cardsGrid[p2.getRow()][p2.getColumn()] = card1;
            card1.setPosition(p2);
        }    
    }
    
    /* déplacement de plusieurs pions en ligne ou en colonne
    *  Attention à mettre à jour la position de la cases vide
    */
    
    /* déplace tous les pions à partir de pos d'une case vers la gauche 
    *   jusqu'à la position de la case vide
    */
  /*  protected void moveLeft(Position pos)
    {   
        int emptyPosCol = this.emptyPos.getColumn();
        int posRow = pos.getRow();
        int posCol = pos.getColumn();
        
        // on parcourt de la pos de la case vide jusqu'à la position de la case cliquée
        for (int col = emptyPosCol; col < posCol; col++) {
            // on déplace les pions
            this.pawnsGrid[posRow][col] = this.pawnsGrid[posRow][col+1];
        }
        // on met à jour la position de la case vide
        this.emptyPos.setColumn(posCol);  
    }
    
    protected void moveRight(Position pos)
    {
        int emptyPosCol = this.emptyPos.getColumn();
        int posRow = pos.getRow();
        int posCol = pos.getColumn();
        
        for (int col = emptyPosCol; col > posCol; col--) {
            // on déplace les pions
            this.pawnsGrid[posRow][col] = this.pawnsGrid[posRow][col-1];
        }
        // on met à jour la position de la case vide
        this.emptyPos.setColumn(posCol);
    }
    
    protected void moveUp(Position pos)
    {
        int emptyPosRow = this.emptyPos.getRow();
        int posCol = pos.getColumn();
        int posRow = pos.getRow();
        
        for (int row = emptyPosRow; row < (posRow); row++) {
            this.pawnsGrid[row][posCol] = this.pawnsGrid[row+1][posCol];
        }
        this.emptyPos.setRow(posRow);
    }
    
    protected void moveDown(Position pos)
    {
        int emptyPosRow = this.emptyPos.getRow();
        int posCol = pos.getColumn();
        int posRow = pos.getRow();
        
        for (int row = emptyPosRow; row > posRow; row--) {
            // on déplace les pions
            this.pawnsGrid[row][posCol] = this.pawnsGrid[row-1][posCol];
        }
        // on met à jour la position de la case vide
        this.emptyPos.setRow(posRow);
    }
   
    
    /*
    *   déplace tous les pions à partir de la position pos d'une case vers la case vide
    *   si pos est sur la même ligne ou colonne que la case vide
    *   sinon il ne se passe rien
    */
  /*  public void move(Position pos)
    {
        int emptyPosRow = emptyPos.getRow();
        int emptyPosCol = emptyPos.getColumn();
        int posRow = pos.getRow();
        int posCol = pos.getColumn();
        
        // todo : utiliser les méthodes isLeftTo()... dans les conditions à la place de mes tests
                
        if (posRow == emptyPosRow) { // case et vide sur la même ligne
            if (emptyPosCol < posCol) {
                // déplacement vers gauche si si la case vide est à gauche de la case à déplacer
                moveLeft(pos);
                this.moves ++;
            } else {
                moveRight(pos); 
                this.moves ++;
            }
        } else if (posCol == emptyPosCol) { // case et vide sur la même colonne
            if (emptyPosRow < posRow) {
                moveUp(pos);
                this.moves ++;
            } else {
                moveDown(pos);
                this.moves ++;
            }
        }
        
        // sinon : il ne se passe rien
    }
    
    
    /*
    *   mélange de la grille
    */
    public void shuffle()
    {
        // algo de Fisher-Yates
        /*
        *   pour mélanger un tableau a de n éléments (indiciés de 0 à n-1) :
        *   pour i allant de n-1 à 1 faire :
        *   j : entier aléatoire entre 0 et i
        *   échanger a[j] et a[i]
        */
        
        // générateur aléatoire
        Random generateurEntier = new Random();
        
        // on parcourt la grille
        for (int row = 0; row < this.nbRows; row++){
            for (int col = 0; col < this.nbCols; col++) {
                int posAleatoireRow = generateurEntier.nextInt(this.nbRows);
                int posAleatoireCol = generateurEntier.nextInt(this.nbCols);
                
                Position aleatoirePos = new Position(posAleatoireRow, posAleatoireCol);
                Position curPos = new Position(row, col);
               
                swap(aleatoirePos, curPos);  
            }
        }    
    }
}
