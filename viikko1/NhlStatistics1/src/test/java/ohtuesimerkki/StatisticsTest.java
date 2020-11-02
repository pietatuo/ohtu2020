package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;

public class StatisticsTest {

    public StatisticsTest() {
    }

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4,12));
            players.add(new Player("Lemieux", "PIT", 45,54));
            players.add(new Player("Kurri", "EDM", 37,53));
            players.add(new Player("Yzerman", "DET", 42,56));
            players.add(new Player("Gretzky", "EDM", 35,89));
            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void testTest() {
        assertEquals(1, 1);
    }

    @Test
    public void setUpWorks() {
        List<Player> players = stats.players();
        assertEquals(players.size(), 5);
    }

    @Test
    public void searchWorks() {
        Player kurri = stats.search("Kurri");
        Player noone = stats.search("John Doe");

        assertEquals(kurri.getName(), "Kurri");
        assertEquals(noone, null);
    }

    @Test
    public void teamSearchWorks() {
        List<Player> team = stats.team("EDM");
        assertEquals(3, team.size());
        String[] names = new String[3];
        int i = 0;
        for (Player p: team) {
            names[i] = p.getName();
            i++;
        }
        assertEquals("Semenko", names[0]);
        assertEquals("Kurri", names[1]);
        assertEquals("Gretzky", names[2]);
    }

    @Test
    public void topScorersWork() {
        List<Player> top = stats.topScorers(3);
        assertEquals(3,top.size());
        String[] names = new String[3];
        int i = 0;
        for (Player p: top) {
            names[i] = p.getName();
            i++;
        }
        assertEquals("Gretzky", names[0]);
        assertEquals("Lemieux", names[1]);
        assertEquals("Yzerman", names[2]);
    }
}