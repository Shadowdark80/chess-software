package software.ulpgc.chess.application.domain;

public record User(String id, String username) {

    public User {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
    }

    public static User create(String id, String username) {
        return new User(id, username);
    }
}
