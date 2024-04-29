import java.util.Arrays;
import java.util.Comparator;

public abstract class ResultsOfSportsCompetition {
    protected final String sportType;
    protected final Integer resultA;
    protected final Integer resultB;

    public ResultsOfSportsCompetition(String sportType, Integer resultA, Integer resultB) {
        this.sportType = sportType;
        this.resultA = resultA;
        this.resultB = resultB;
    }

    public Integer getResultA() {
        return resultA;
    }

    public Integer getResultB() {
        return resultB;
    }

    // Abstract method to determine the winner of the game
    public abstract Character getWinnerOfGame();

    @Override
    public String toString() {
        String winner = getWinnerOfGame().toString();
        return sportType + "\n" + "Team A " + resultA + " : " + resultB + " Team B" + "\n" + winner + " — is the winner!";
    }

    // Static nested comparator class for sorting ResultsOfSportsCompetition instances by total goals
    static class TotalGoalsComparator implements Comparator<ResultsOfSportsCompetition> {
        @Override
        public int compare(ResultsOfSportsCompetition r1, ResultsOfSportsCompetition r2) {
            // Compare by total goals scored (resultA + resultB)
            int totalGoals1 = r1.getResultA() + r1.getResultB();
            int totalGoals2 = r2.getResultA() + r2.getResultB();
            return Integer.compare(totalGoals1, totalGoals2);
        }
    }
}

class FootballWinner extends ResultsOfSportsCompetition {
    public FootballWinner(Integer resultA, Integer resultB) {
        super("Football", resultA, resultB);
    }

    @Override
    public Character getWinnerOfGame() {
        if (getResultA() > getResultB()) {
            return 'A';
        } else if (getResultA() < getResultB()) {
            return 'B';
        } else {
            return '-'; // Draw
        }
    }
}

class TennisWinner extends ResultsOfSportsCompetition {
    public TennisWinner(Integer resultA, Integer resultB) {
        super("Tennis", resultA, resultB);
    }

    @Override
    public Character getWinnerOfGame() {
        if (getResultA() > getResultB()) {
            return 'A';
        } else if (getResultA() < getResultB()) {
            return 'B';
        } else {
            return '-'; // Draw
        }
    }
}

class Main {
    public static void main(String[] args) {
        // Football matches
        FootballWinner[] footballMatches = {
                new FootballWinner(3, 1111),
                new FootballWinner(2, 122),
                new FootballWinner(1, 12)
        };

        // Sorting footballMatches array by total goals using TotalGoalsComparator
        Arrays.sort(footballMatches, new ResultsOfSportsCompetition.TotalGoalsComparator());

        // Printing the sorted footballMatches array
        for (FootballWinner match : footballMatches) {
            System.out.println(match);
        }

        // Tennis matches
        TennisWinner[] tennisMatches = {
                new TennisWinner(3, 1111),
                new TennisWinner(12, 122),
                new TennisWinner(1, 12)
        };

        // Sorting tennisMatches array by resultA using an anonymous Comparator
        Arrays.sort(tennisMatches, new Comparator<TennisWinner>() {
            @Override
            public int compare(TennisWinner tw1, TennisWinner tw2) {
                return Integer.compare(tw1.getResultA(), tw2.getResultA());
            }
        });

        // Printing the sorted tennisMatches array
        for (TennisWinner match : tennisMatches) {
            System.out.println(match);
        }
    }
}
