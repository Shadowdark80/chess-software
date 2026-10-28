package software.ulpgc.chess.application.domain;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    File(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static File fromChar(char character) {
        char lowerChar = Character.toLowerCase(character);

        for (File file : File.values()) {
            if (file.value == lowerChar) {
                return file;
            }
        }

        throw new IllegalArgumentException("Invalid file. Must be between 'a' and 'h'. Received: " + character);
    }
}
