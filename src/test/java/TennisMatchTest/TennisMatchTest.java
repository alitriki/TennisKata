package TennisMatchTest;

import TennisMatch.Match;
import org.junit.Test;
import static org.junit.Assert.*;

public class TennisMatchTest {

    //Sprint 1
    @Test
    public void playersStartWithGameScore0Test(){
        Match match = new Match();
        final String actual = match.player_one.getGameScore();
        final String expected = "0";
        assertEquals(expected,actual);
        final String actual1 = match.player_one.getGameScore();
        final String expected1 = "0";
        assertEquals(expected1,actual1);
    }

    @Test
    public void playerWinsAllPointsAndTheGameTest(){
        Match match = new Match();
        String oldScore;
        String actual = null;
        String expected = null;
        for(int i=0;i<4;i++){
            oldScore = match.player_one.getGameScore();
            match.player_one.winPoint();
            actual = match.player_one.getGameScore();
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
                default:
                    expected = "error";
                    break;
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    public void deuceTest(){
        Match match = new Match();
        for(int i=0;i<3;i++){
            match.winPoint(match.player_one);
            match.winPoint(match.player_two);
        }
        assertTrue(match.player_one.isDeuce());
        assertTrue(match.player_two.isDeuce());
    }

    @Test
    public void PlayerAdvTest(){
        Match match = new Match();
        for(int i=0;i<3;i++){
            match.winPoint(match.player_one);
            match.winPoint(match.player_two);
        }
        match.winPoint(match.player_one);
        assertTrue(match.player_one.isAdv());
        assertFalse(match.player_two.isAdv());
        assertFalse(match.player_one.isDeuce());
        assertFalse(match.player_two.isDeuce());
    }

    @Test
    public void PlayerDeuceAfterAdvTest(){
        Match match = new Match();
        for(int i=0;i<3;i++){
            match.winPoint(match.player_one);
            match.winPoint(match.player_two);
        }
        match.winPoint(match.player_one);
        match.winPoint(match.player_two);
        assertFalse(match.player_one.isAdv());
        assertFalse(match.player_two.isAdv());
        assertTrue(match.player_one.isDeuce());
        assertTrue(match.player_two.isDeuce());
    }

    @Test
    public void PlayerWinsAfterAdvTest(){
        Match match = new Match();
        final int expected = match.player_one.getSetScore() +1;
        for(int i=0;i<3;i++){
            match.winPoint(match.player_one);
            match.winPoint(match.player_two);
        }
        match.winPoint(match.player_one);
        match.winPoint(match.player_one);
        final int actual = match.player_one.getSetScore();
        assertEquals(expected,actual);
    }



    //Sprint 2
    @Test
    public void playersStartWithSetScore0Test(){
        Match match = new Match();
        final int actual = match.player_one.getSetScore();
        final int expected = 0;
        assertEquals(expected,actual);
        final int actual1 = match.player_one.getSetScore();
        final int expected1 = 0;
        assertEquals(expected1,actual1);
    }

    @Test
    public void playerWinsAllGamesAndMatch(){
        Match match = new Match();
        for(int i=0;i<6;i++){
            match.winGame(match.player_one);
        }
        assertTrue(match.player_one.isWinner());
        assertFalse(match.player_two.isWinner());
    }

    @Test
    public void player_oneWinsMatchScore7_5(){
        Match match = new Match();
        for(int i=0;i<5;i++){
            match.winGame(match.player_one);
            match.winGame(match.player_two);
        }
        match.winGame(match.player_one);
        match.winGame(match.player_one);
        assertTrue(match.player_one.isWinner());
        assertFalse(match.player_two.isWinner());
        assertEquals(7,match.player_one.getSetScore());
        assertEquals(5,match.player_two.getSetScore());
    }

    @Test
    public void player_twoWinsMatchScore5_7(){
        Match match = new Match();
        for(int i=0;i<5;i++){
            match.winGame(match.player_one);
            match.winGame(match.player_two);
        }
        match.winGame(match.player_two);
        match.winGame(match.player_two);
        assertFalse(match.player_one.isWinner());
        assertTrue(match.player_two.isWinner());
        assertEquals(5,match.player_one.getSetScore());
        assertEquals(7,match.player_two.getSetScore());
    }


    @Test
    public void playerWinsMatchTieBreak7_0(){
        Match match = new Match();
        for(int i=0;i<6;i++){
            match.winGame(match.player_one);
            match.winGame(match.player_two);
        }
        for(int j=0;j<7;j++){
            match.winPoint(match.player_one);
        }
        assertTrue(match.player_one.isWinner());
        assertFalse(match.player_two.isWinner());
        assertEquals(7,match.player_one.getSetScore());
        assertEquals(6,match.player_two.getSetScore());
    }

    @Test
    public void playerWinsMatchTieBreak10_8(){
        Match match = new Match();
        for(int i=0;i<6;i++){
            match.winGame(match.player_one);
            match.winGame(match.player_two);
        }
        for(int j=0;j<8;j++){
            match.winPoint(match.player_one);
            match.winPoint(match.player_two);
        }
        match.winPoint(match.player_one);
        match.winPoint(match.player_one);
        assertTrue(match.player_one.isWinner());
        assertFalse(match.player_two.isWinner());
        assertEquals(7,match.player_one.getSetScore());
        assertEquals(6,match.player_two.getSetScore());
    }
}
