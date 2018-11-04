package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void pelaajaHakuPalauttaaPelaajan() {
        Player found = stats.search("Kurri");

        assertEquals(found.getName(), "Kurri");
        assertEquals(found.getTeam(), "EDM");
        assertEquals(found.getGoals(), 37);
        assertEquals(found.getAssists(), 53);
    }
    
    @Test
    public void pelaajaHakuPalauttaaNullJosEiLoydy() {
        Player found = stats.search("Jooseppi");
        
        assertNull(found);
    }
    
    @Test
    public void tiimiHakuPalauttaaTiiminPelaajat() {
        List<Player> players = stats.team("EDM");
        
        assertEquals(players.size(), 3);
        players.forEach((player) -> {
            assertEquals(player.getTeam(), "EDM");
        });
    }
    
    @Test
    public void topScorersPalauttaaPisteporssinParhaat() {
        List<Player> topScorers = stats.topScorers(2);
        
        assertEquals(topScorers.size(), 3);
        assertEquals(topScorers.get(0).getName(), "Gretzky");
        assertEquals(topScorers.get(1).getName(), "Lemieux");
        assertEquals(topScorers.get(2).getName(), "Yzerman");
    }
}
