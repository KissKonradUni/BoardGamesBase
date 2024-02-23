package app.kosoft.boardgames;

public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
}
