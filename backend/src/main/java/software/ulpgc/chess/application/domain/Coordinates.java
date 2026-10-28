package software.ulpgc.chess.application.domain;

public record Coordinates(File file, Rank rank) {
    public static Coordinates fromString(String notation) {
        if (notation == null || notation.length() != 2) {
            throw new IllegalArgumentException("Invalid notation. Must be exactly 2 characters long (e.g., 'e4').");
        }

        char fileChar = notation.charAt(0);
        int rankInt = Character.getNumericValue(notation.charAt(1));

        File fileEnum = File.fromChar(fileChar);
        Rank rankEnum = Rank.fromInt(rankInt);

        return new Coordinates(fileEnum, rankEnum);
    }

    @Override
    public String toString() {
        return String.valueOf(file.getValue()) + rank.getValue();
    }
}
