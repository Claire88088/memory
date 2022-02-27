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
  //  private boolean isPlaying; // ---TODO : utile ??-----------
    private boolean oneCardUp; // a déjà retourné 1 carte
    private boolean oneCardChosen;

    private static int nbPlayers = 0;
    
    public Player(String name) {
       /* if (nbPlayers == 0) {
            this.isPlaying = true;
        }
        else {
            this.isPlaying = false;
        }*/
        this.number = nbPlayers;
        this.name = name;
        this.score = 0;
        this.oneCardUp = false;
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
/*    
    public boolean isPlaying() {
        return this.isPlaying;
    }
    
    public void switchIsPlaying() {
        this.isPlaying = !this.isPlaying;
    }
    
    public void setIsPlaying(Boolean bool) {
        this.isPlaying = bool;
    }
*/
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    } 

    public boolean isOneCardUp() {
        return oneCardUp;
    }

    public void switchOneCardUp() {
        if (this.oneCardUp) {
            this.oneCardUp = false;
        } else {
            this.oneCardUp = true;
        }
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
}
