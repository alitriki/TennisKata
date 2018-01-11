package TennisMatchTest;

import TennisMatch.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {


    @Test
    public void playerStartsWithNullScoreTest(){
        Player player = new Player();
        assertEquals("0",player.getGameScore());
        assertEquals(0,player.getSetScore());
        assertFalse(player.isWinner());
        assertFalse(player.isDeuce());
        assertFalse(player.isDeuce());
        assertFalse(player.isTieBreak());
    }

    @Test
    public void winPointTest(){
        Player player = new Player();
        String oldScore;
        String actual = null;
        String expected = null;
        for(int i=0;i<4;i++){
            oldScore = player.getGameScore();
            player.winPoint();
            actual = player.getGameScore();
            switch (oldScore)
            {
                case "0":
                    expected = "15";
                    break;
                case "15":
                    expected = "30";
                    break;
                case "30":
                    expected = "40";
                    break;
                case "40":
                    expected = "WIN";
                    break;
                case "WIN":
                    expected = "error";
                    break;
            }
        }
        assertEquals(expected,actual);

    }

    @Test
    public void winGameTest(){
        Player player = new Player();
        int expected = player.getSetScore() + 1;
        player.winGame();
        assertEquals(expected,player.getSetScore());
        assertEquals("0",player.getGameScore());
        assertFalse(player.isTieBreak());
        assertFalse(player.isAdv());
    }

    @Test
    public void loseGameTest(){
        Player player = new Player();
        player.loseGame();
        assertEquals("0",player.getGameScore());
        assertFalse(player.isTieBreak());
    }

    @Test
    public void winMatchTest(){
        Player player = new Player();
        int expected = player.getSetScore() + 1;
        player.winMatch();
        assertEquals(expected,player.getSetScore());
        assertTrue(player.isWinner());
    }

    @Test
    public void loseMatchTest(){
        Player player = new Player();
        int expected = player.getSetScore();
        player.loseMatch();
        assertEquals(expected,player.getSetScore());
        assertFalse(player.isWinner());
    }
}
