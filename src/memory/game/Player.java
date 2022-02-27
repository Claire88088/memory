/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.game;

/**
 *
 * @author cbouto01
 */
public class Player {
    
    private int number;
    private String name;
    private int score;
    private int nbCardsUp;
    private boolean oneCardChosen;

    private static int nbPlayers = 0;
    
    public Player(String name) {
        this.nbCardsUp = 0;
        this.number = nbPlayers;
        this.name = name;
        this.score = 0;
      
        nbPlayers ++;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public String getScoreToString() {
        return Integer.toString(score);
    }

    public void getPoint() {
        this.score ++;
    }
    
    public void resetScore() {
        this.score = 0;
    } 
    
    public static int getNbPlayers() {
        return nbPlayers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    } 

     public boolean isOneCardChosen() {
        return oneCardChosen;
    }

    public void switchOneCardChosen() {
        if (this.oneCardChosen) {
            this.oneCardChosen = false;
        } else {
            this.oneCardChosen = true;
        }
    }

    public int getNbCardsUp() {
        return nbCardsUp;
    }

    public void setNbCardsUp(int nbCardsUp) {
        this.nbCardsUp = nbCardsUp;
    }
    
    public void addNbCardsUp() {
        this.nbCardsUp ++;
    }
    
    public void resetNbCardsUp() {
        this.nbCardsUp = 0;
    }
}
