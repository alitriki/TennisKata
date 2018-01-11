package TennisMatch;

import java.util.Arrays;
import java.util.List;

public class Player {

    private static final List<String> points = Arrays.asList("0", "15", "30", "40", "WIN");
    private boolean deuce;
    private boolean adv;
    private boolean tieBreak;
    private boolean winner;
    private int gameScore;
    private int setScore;
    private int tieBreakScore;


    //constructer
    public Player() {
        gameScore = 0;
        setScore=0;
        deuce= false;
        adv = false;
        tieBreak = false;
        winner = false;
    }

    //point score in current game
    public String getGameScore() {
        return points.get(gameScore);
    }

    //index of point score in current game
    public int getGameScoreIndex() {
        return gameScore;
    }

    // number of sets won
    public int getSetScore() {
        return setScore;
    }

    // point score in case of tie break
    public int getTieBreakScore() {
        return tieBreakScore;
    }

    public static List<String> getPoints() {
        return points;
    }

    public boolean isDeuce() {
        return deuce;
    }

    public boolean isAdv() {
        return adv;
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setDeuce(boolean deuce) {
        this.deuce = deuce;
    }

    public void setAdv(boolean adv) {
        this.adv = adv;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    //player wins a point
    public void winPoint(){
        if(tieBreak){
            tieBreakScore ++;
        }else{
            gameScore ++;
        }
    }

    //player wins a game
    public void winGame(){
        adv=false;
        gameScore=0;
        tieBreakScore = 0;
        setScore++;
    }

    //player loses a game
    public void loseGame(){
        gameScore=0;
        tieBreakScore = 0;
    }

    //player wins the entire set
    public void winMatch(){
        winGame();
        winner = true;
    }

    //player loses the entire set
    public void loseMatch(){
        loseGame();
    }


    public String getScore() {
        return "Score{" +
                "gameScore=" + gameScore +
                ", setScore=" + setScore +
                ", tieBreakScore=" + tieBreakScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return deuce == player.deuce &&
                adv == player.adv &&
                tieBreak == player.tieBreak &&
                winner == player.winner &&
                gameScore == player.gameScore &&
                setScore == player.setScore &&
                tieBreakScore == player.tieBreakScore;
    }


}
