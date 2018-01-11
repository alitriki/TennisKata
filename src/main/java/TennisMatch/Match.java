package TennisMatch;

public class Match {
    public Player player_one;
    public Player player_two;

    public Match() {
        player_one = new Player();
        player_two = new Player();
    }

    // a player wins a point
    public void winPoint(Player winner){
        Player loser = null;
        // save the player who scored the point
        int scorer = winner.hashCode()==player_one.hashCode() ? 1 : 2 ;
        // looser gets the player who didn't score
        loser = winner.hashCode()==player_one.hashCode() ? player_two : player_one ;

        // in case of Tie Break
        if (winner.isTieBreak()) {
            int difference = winner.getTieBreakScore() - loser.getTieBreakScore();
            //if the scorer's Tie Break score is more than 6 AND the difference is more than 1 then by scoring he wins the game
            if(winner.getTieBreakScore()>=6 && difference >=1){
                winGame(winner);
            //if the scorer's Tie Break score is less than 6 OR the difference is less than 1 then by scoring he just wins a point
            }else{
                winner.winPoint();
            }
        // in case there is no Tie Break (normal games)
        } else {
            // if the scorer has the advantage then by scoring he wins the game
            if(winner.isAdv()){
                winGame(winner);
                loser.loseGame();
            // if the the deuce rule applies then by scoring the scorer gets the advantage
            }else if(winner.isDeuce()) {
                winner.setDeuce(false);
                loser.setDeuce(false);
                winner.setAdv(true);
            // if the other player has the advantage then by scoring the game is deuce
            }else if(loser.isAdv()){
                winner.setDeuce(true);
                loser.setDeuce(true);
                winner.setAdv(false);
                loser.setAdv(false);
            // the other player's game score is less than 40
            }else if(loser.getGameScoreIndex()<3){
                // if both players have a game score of less than 40 then by scoring the player just wins a point
                if(winner.getGameScoreIndex()<3){
                    winner.winPoint();
                // if the scorer's game score is 40 and there is no deuce (the other player's game score is less than 40) then by scoring the player wins the game
                }else{
                    winner.winGame();
                    loser.loseGame();
                }
            // the other player's game score is 40
            }else if(loser.getGameScoreIndex()==3){
                // if the scorer's game score is less than 30 then by scoring the player just wins a point
                if(winner.getGameScoreIndex()<2){
                    winner.winPoint();
                // if the scorer's game score is 30 then by scoring the game is deuce (the other player's game score is 40)
                }else if(winner.getGameScoreIndex()== 2){
                    winner.winPoint();
                    winner.setDeuce(true);
                    loser.setDeuce(true);
                }
            }
        }
        // update the global variables
        player_one = (scorer==1) ? winner : loser ;
        player_two = (scorer==2) ? winner : loser ;

    }

    // a player wins a game
    public void  winGame(Player winner){
        Player loser = null;
        // save the player who scored the point
        int scorer = winner.hashCode()==player_one.hashCode() ? 1 : 2 ;
        // looser gets the player who didn't score
        loser = winner.hashCode()==player_one.hashCode() ? player_two : player_one ;

        // the other player's set score is less than 5
        if(loser.getSetScore()<5){
            // if the scorer's set score is 5 then by scoring he wins the match (he reaches 6 games and the other player won less than 5)
            if(winner.getSetScore()==5){
                winMatch(winner);
            // if the scorer's set score is less than 5 then by scoring he just wins the game
            }else{
                winner.winGame();
                loser.loseGame();
            }
        // the other player's set score is 5
        }else if(loser.getSetScore()==5){
            // if the scorer's set score is 6 then by scoring he wins the match (he reaches 7 games and the other player won 5)
            if(winner.getSetScore()==6){
                winMatch(winner);
            // if the scorer's set score is less than 6 then by scoring he just wins the game (he can't win by reaching 6 because it would be a Tie Break)
            }else{
                winner.winGame();
                loser.loseGame();
            }
        // the other player's set score is 6
        }else if(loser.getSetScore()==6){
            // if the scorer's set score is 5 then by scoring there's a Tie Break (he reaches 6 games and the other player won 6)
            if(winner.getSetScore()==5){
                winner.winGame();
                loser.loseGame();
                winner.setTieBreak(true);
                loser.setTieBreak(true);
            // if the scorer's set score is 6 then by scoring he wins the match (he reaches 7 games and the other player won 6)
            }else{
                winMatch(winner);
            }
        }
        // update the global variables
        player_one = (scorer==1) ? winner : loser ;
        player_two = (scorer==2) ? winner : loser ;
    }

    // a player wins the match
    private void winMatch(Player winner){
        Player loser = null;
        // save the player who scored the point
        int scorer = winner.hashCode()==player_one.hashCode() ? 1 : 2 ;
        // looser gets the player who didn't score
        loser = winner.hashCode()==player_one.hashCode() ? player_two : player_one ;

        winner.winMatch();
        loser.loseMatch();

        player_one = (scorer==1) ? winner : loser ;
        player_two = (scorer==2) ? winner : loser ;
    }

}
