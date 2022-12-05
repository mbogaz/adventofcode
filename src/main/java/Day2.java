import util.InputFileUtil;

import java.io.IOException;
import java.util.Objects;

public class Day2 {
    public static void main(String[] args) throws IOException {
        String inputStr = InputFileUtil.readFileAsString("inputs/day2");
        String[] strategies = inputStr.split("\n");

        int totalScore = 0;
        for (String strategy : strategies) {
            Type myType = Type.getByAka2(strategy.charAt(2));
            Type opponentType = Type.getByAka(strategy.charAt(0));
            totalScore += Objects.requireNonNull(myType).calculateScore(opponentType);
        }

        System.out.println("My total score is : " + totalScore);

        System.out.println("PART 2");
        totalScore = 0;
        for (String strategy : strategies) {
            Type opponentType = Type.getByAka(strategy.charAt(0));
            Character status = strategy.charAt(2);
            totalScore += Type.calculateScoreByStatus(status, opponentType);
        }
        System.out.println("My total score is : " + totalScore);
    }
}

enum Type {
    ROCK(1, 'A', 'X'),
    PAPER(2, 'B', 'Y'),
    SCISSORS(3, 'C', 'Z');

    public final int extraScore;
    private final Character aka;
    private final Character aka2;

    Type(int extraScore, Character aka, Character aka2) {
        this.extraScore = extraScore;
        this.aka = aka;
        this.aka2 = aka2;
    }

    public static Type getByAka(Character c) {
        for (Type type : Type.values()) {
            if (type.aka == c) {
                return type;
            }
        }
        return null;
    }

    public static Type getByAka2(Character c) {
        for (Type type : Type.values()) {
            if (type.aka2 == c) {
                return type;
            }
        }
        return null;
    }

    public Type getLower() {
        return switch (this) {
            case ROCK -> SCISSORS;
            case SCISSORS -> PAPER;
            case PAPER -> ROCK;
        };
    }

    public Type getSuperior() {
        return switch (this) {
            case SCISSORS -> ROCK;
            case PAPER -> SCISSORS;
            case ROCK -> PAPER;
        };
    }

    public int calculateScore(Type opponentType) {
        int score = this.extraScore;
        if (this == opponentType) {
            return score + 3;
        }
        if (opponentType.getSuperior() == this) {
            return score + 6;
        }
        return score;
    }

    public static int calculateScoreByStatus(Character status, Type opponentType) {
        if (status == 'X') {
            return opponentType.getLower().extraScore;
        } else if (status == 'Y') {
            return opponentType.extraScore + 3;
        } else if (status == 'Z') {
            return opponentType.getSuperior().extraScore + 6;
        }
        return 0;
    }
}
