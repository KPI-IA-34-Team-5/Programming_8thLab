import java.util.Comparator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public abstract class ResultsOfSportsCompetition {
    protected final String sportType;
    protected final Integer resultA;
    protected final Integer resultB;

    public ResultsOfSportsCompetition(String sportType, Integer resultA, Integer resultB) {
        this.sportType = sportType;
        this.resultA = resultA;
        this.resultB = resultB;
    }

    public String getSportType() {
        return sportType;
    }

    public Integer getResultA() {
        return resultA;
    }

    public Integer getResultB() {
        return resultB;
    }

    // Метод для визначення переможця
    public abstract Character getWinnerOfGame();

    @Override
    public String toString() {
        String winner = getWinnerOfGame().toString();
        return sportType + "\n" + "Team A " + resultA + " : " + resultB + " Team B" + "\n" + winner + " — is winner!";
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
            return '-'; // Нічия
        }
    }

    public static class FootballWinnerComparator implements Comparator{

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
            return '-'; // Нічия
        }
    }
}

class Main {
    public static void main(String[] args) {
        FootballWinner footballWinner1 = new FootballWinner(5, 6);
        FootballWinner footballWinner2 = new FootballWinner(6, 6);
        FootballWinner footballWinner3 = new FootballWinner(3, 1);

        FootballWinner[] footballWinners = { footballWinner1, footballWinner2, footballWinner3 };

        // Static nested class comparator
        Arrays.sort(footballWinners, new FootballWinner.FootballWinnerComparator());

        System.out.println("Sorted by FootballWinnerComparator:");
        for (FootballWinner winner : footballWinners) {
            System.out.println(winner);
        }

        System.out.println("-------------------------------");

        // Anonymous class comparator
        TreeSet<FootballWinner> footballWinnerTreeSet = new TreeSet<>(new Comparator<FootballWinner>() {
            @Override
            public int compare(FootballWinner fw1, FootballWinner fw2) {
                return fw1.getResultA().compareTo(fw2.getResultA());
            }
        });

        footballWinnerTreeSet.add(footballWinner1);
        footballWinnerTreeSet.add(footballWinner2);
        footballWinnerTreeSet.add(footballWinner3);

        System.out.println("Sorted by TreeSet with anonymous class comparator:");
        for (FootballWinner winner : footballWinnerTreeSet) {
            System.out.println(winner);
        }
    }
}

// Static nested class comparator
class FootballWinnerComparator implements Comparator<FootballWinner> {
    @Override
    public int compare(FootballWinner fw1, FootballWinner fw2) {
        return fw1.getResultB().compareTo(fw2.getResultB());
    }
}
