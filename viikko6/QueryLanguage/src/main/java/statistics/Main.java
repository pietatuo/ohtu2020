package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new Not(new HasAtLeast(1, "goals") ),
                             new PlaysIn("NYR")
        );

        Matcher n = new And( new HasFewerThan(1, "goals"),
                            new PlaysIn("NYR")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("XXX");
        for (Player player : stats.matches(n)) {
            System.out.println(player);
        }
        System.out.println(stats.matches(new All()).size());
    }
}
