package software.ulpgc.chess.application.domain;

public enum Rank {
    RANK_1(1),
    RANK_2(2),
    RANK_3(3),
    RANK_4(4),
    RANK_5(5),
    RANK_6(6),
    RANK_7(7),
    RANK_8(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rank fromInt(int number) {
        for (Rank rank : Rank.values()) {
            if (rank.value == number) {
                return rank;
            }
        }

        throw new IllegalArgumentException("Invalid rank. Must be between 1 and 8. Received: " + number);
    }
}
